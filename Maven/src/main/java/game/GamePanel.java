package game;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

/**
 * Panel όπου γίνεται η αναπαράσταση του παιχνιδιού
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

    final int originalTileSize = 16;
    final int scale = 3;

    final int tileSize = originalTileSize * scale;
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenCol;
    final int screenHeight = tileSize * maxScreenRow;

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
    final Player player = new Player(this, keyH);
    final CollisionCheck collisionCheck = new CollisionCheck(this);
    final LinkedList<SuperObject> obj = new LinkedList<>();
    private final AssetSetter aSetter = new AssetSetter(this);

    final LabyrinthFrame labyrinthFrame;
    //Μεταβλητές για την κατάσταση παιχνιδιού
    int gameState;
    final int playState = 1;
    final int pauseState = 2;
    final int endState = 3;

    /**
     * <p>Constructor for GamePanel.</p>
     *
     * @param labyrinthFrame a {@link game.LabyrinthFrame} object
     */
    public GamePanel(LabyrinthFrame labyrinthFrame) {
        this.labyrinthFrame = labyrinthFrame;
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
        startGameThread();
    }

    /**
     * Μέθοδος προετοιμασίας αντικειμένων παιχνιδιού
     */
    public void setupGame() {
        aSetter.setObject();
    }

    /**
     * Μέθοδος εκκίνησης παιχνιδιού
     */
    private void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
        //Για να μην μπορεί να κουνηθεί ο παίκτης πριν πατηθεί το κουμπί start
        gameState = pauseState;
    }


    /**
     * {@inheritDoc}
     *
     * <p>Υλοποιείται game loop εξατομικευμένο ώστε να τρέχει το παιχνίδι με 60 fps </p>
     */
    @Override
    public void run() {

        int FPS = 60;
        double drawInterval = (double) 1000000000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while (gameThread != null) {
            //UPDATE && DRAW
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;

            lastTime = currentTime;
            if (delta >= 1) {
                update();
                //Τερματισμός παιχνιδιού σε περίπτωση νίκης TODO(span+g.artop) προσθήκη win animation
                if (gameState == endState) {
                    Menu.stopMusic();
                    //Για να μην κολλήσει η λειτουργία της μπάρας
                    labyrinthFrame.closeFrame(true);
                    return;
                    //Ενέργεια που εκτελείται όταν χάνει ο παίκτης
                } else if (labyrinthFrame.getHasLost()) {
                    for (int times = 0; times < Entity.death.length - 1; times++) {
                        if (times == 0)
                            Menu.stopMusic();
                        //Για να απεικονιστεί φανερά ο "θάνατος" του παίκτη
                        sleep(0.75);
                        update();
                        repaint();
                    }
                    sleep(1);
                    labyrinthFrame.closeFrame(false);
                    return;
                }
                repaint();
                delta--;

            }
        }
    }

    private void sleep(double seconds) {
        try {
            Thread.sleep((long) (1000L * seconds));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Μέθοδος ανανέωσης γραφικών χαρακτήρα
     */
    public void update() {
        if (gameState == playState) {
            player.update();
        } else {
            //Για να μην κολλάει η κίνηση του παίκτη όταν το παιχνίδι βρίσκεται σε κατάσταση παύσης
            player.stabilizePlayer();
        }
    }


    /** {@inheritDoc} */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        tileM.draw(g2);

        //Απεικόνιση αντικειμένων παιχνιδιού
        for (SuperObject superObject : obj) {
            if (superObject != null)
                superObject.draw(g2, this);
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


}
