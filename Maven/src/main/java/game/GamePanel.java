package game;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.SecureRandom;
import java.util.LinkedList;

/**
 * <p>Panel όπου γίνεται η αναπαράσταση του παιχνιδιού</p>
 *
 * @author Team Hack-You
 * @version 1.0
 */
public final class GamePanel extends JPanel implements Runnable {

    /**
     * Serial number of persistent  data.
     * Required, because JPanel implements serializable.
     */
    private static final long serialVersionUID = 1L;

    private static final int originalTileSize = 16;
    private static final int scale = 3;

    static final int tileSize = originalTileSize * scale;
    static final int maxScreenCol = 16;
    static final int maxScreenRow = 12;
    static final int screenWidth = tileSize * maxScreenCol;
    static final int screenHeight = tileSize * maxScreenRow;

    //Καθορισμός των διαστάσεων του κόσμου του λαβυρίνθου ανάλογα με την επιλεγμένη δυσκολία
    final int maxWorldCol = 28
            + (Levels.getDifficulty().equals("Medium") ? 6
            : (Levels.getDifficulty().equals("Hard") ? 14 : 0));
    final int maxWorldRow = maxWorldCol;
    final int WorldWidth = tileSize * maxWorldCol;
    final int WorldHeight = tileSize * maxWorldRow;

    final TileManager tileM = new TileManager(this);

    final KeyHandler keyH = new KeyHandler(this);
    private Thread gameThread;
    private final Player player = new Player(this, keyH);
    final CollisionCheck collisionCheck = new CollisionCheck(this);
    final LinkedList<SuperObject> obj = new LinkedList<>();
    private final AssetSetter aSetter = new AssetSetter(this);

    final LabyrinthFrame labyrinthFrame;
    //Μεταβλητές για την κατάσταση παιχνιδιού
    private int gameState;
    static final int playState = 1;
    static final int pauseState = 2;
    static final int endState = 3;
    //Για την αναπαραγωγή ήχου
    private static final Sound se = new Sound();


    /**
     * <p>writeObject.</p>
     *
     * @param stream a {@link ObjectOutputStream} object
     * @throws IOException if any
     */
    private void writeObject(ObjectOutputStream stream)
            throws IOException {
        stream.defaultWriteObject();
    }

    /**
     * <p>readObject.</p>
     *
     * @param stream a {@link ObjectInputStream} object
     * @throws IOException            if any
     * @throws ClassNotFoundException if any
     */
    private void readObject(ObjectInputStream stream)
            throws IOException, ClassNotFoundException {
        stream.defaultReadObject();
    }

    /**
     * <p>Getter for the field <code>gameState</code>.</p>
     *
     * @return a int
     */
    public int getGameState() {
        return gameState;
    }

    /**
     * <p>Setter for the field <code>gameState</code>.</p>
     *
     * @param gameState a int
     */
    public void setGameState(int gameState) {
        this.gameState = gameState;
    }

    /**
     * <p>Constructor for GamePanel.</p>
     *
     * @param labyrinthFrame a {@link game.LabyrinthFrame} object
     */
    public GamePanel(LabyrinthFrame labyrinthFrame) {
        this.labyrinthFrame = labyrinthFrame;
        setPreferredSize(new Dimension(screenWidth, screenHeight));
        setBackground(Color.black);
        setDoubleBuffered(true);
        addKeyListener(keyH);
        setFocusable(true);
        startGameThread();
    }

    /**
     * <p>Προετοιμασία αντικειμένων παιχνιδιού.</p>
     */
    public void setupGame() {
        aSetter.setObject();
    }

    /**
     * <p>Εκκίνηση παιχνιδιού</p>
     */
    private void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
        //Για να μην μπορεί να κουνηθεί ο παίκτης πριν πατηθεί το κουμπί start
        gameState = pauseState;
    }

    /**
     * <p>Εξασφάλιση thread safety και εκκαθάριση πόρων.</p>
     */
    void terminate() {
        gameThread = null;
    }

    /**
     * {@inheritDoc}
     *
     * <p>Game loop εξατομικευμένο ώστε να τρέχει το παιχνίδι με 60 fps (frames per second). </p>
     */
    @Override
    public void run() {

        int FPS = 60;
        double drawInterval = (double) 1000000000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while (gameThread != null) {
            //UPDATE and DRAW
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;

            lastTime = currentTime;
            if (delta >= 1) {
                update();
                //Τερματισμός παιχνιδιού σε περίπτωση νίκης
                if (gameState == endState) {
                    Menu.stopMusic();
                    //Για να μην κολλήσει η λειτουργία της μπάρας
                    labyrinthFrame.closeFrame(true);
                    //Ενέργεια που εκτελείται όταν χάνει ο παίκτης
                } else if (labyrinthFrame.getHasLost()) {
                    for (int times = 0; times < Entity.death.length - 1; times++) {
                        if (times == 0) {
                            Menu.stopMusic();
                        }
                        //Για να απεικονιστεί φανερά ο "θάνατος" του παίκτη
                        sleep(0.75);
                        update();
                        repaint();
                    }
                    if (ButtonSetter.getPlaySound()) {
                        playSE();
                    }
                    sleep(1);
                    labyrinthFrame.closeFrame(false);
                }
                repaint();
                delta--;

            }
        }
    }

    /**
     * <p>playSE.</p>
     */
    private void playSE() {
        se.setFile(5);
        se.play();
    }

    /**
     * <p>sleep for certain <code>seconds</code></p>
     *
     * @param seconds an int
     */
    private void sleep(double seconds) {
        try {
            Thread.sleep((long) (1000L * seconds));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * <p>Ανανέωσης γραφικών (κινήσεων) χαρακτήρα</p>
     */
    public void update() {
        if (gameState == playState) {
            player.update();
        } else {
            //Για να μην κολλάει η κίνηση του παίκτη όταν το παιχνίδι βρίσκεται σε κατάσταση παύσης
            player.stabilizePlayer();
        }
    }


    /**
     * {@inheritDoc}
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        tileM.draw(g2);

        //Απεικόνιση αντικειμένων παιχνιδιού
        for (SuperObject superObject : obj) {
            if (superObject != null) {
                superObject.draw(g2, this);
            }
        }

        player.draw(g2);
        //Για να ζωγραφιστεί στην οθόνη τη λέξη ΠΑΥΣΗ σε περίπτωση pause
        if (gameState == pauseState) {
            g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80F));
            String text = "ΠΑΥΣΗ";
            int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            int x = screenWidth / 2 - length / 2;
            int y = screenHeight / 2;
            g2.drawString(text, x, y);
        }
        g2.dispose();
    }

    /**
     * <p>getPlayerWorldx.</p>
     *
     * @return a int
     */
    public int getPlayerWorldx() {
        return player.getWorldx();
    }

    /**
     * <p>getPlayerWorldy.</p>
     *
     * @return a int
     */
    public int getPlayerWorldy() {
        return player.getWorldy();
    }

    /**
     * <p>getPlayerScreenX.</p>
     *
     * @return a int
     */
    public int getPlayerScreenX() {
        return player.getScreenX();
    }

    /**
     * <p>getPlayerScreenY.</p>
     *
     * @return a int
     */
    public int getPlayerScreenY() {
        return player.getScreenY();
    }

    /**
     * <p>playerStabilize.</p>
     */
    public void playerStabilize() {
        player.stabilizePlayer();
    }


}
