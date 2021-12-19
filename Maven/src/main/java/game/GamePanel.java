package game;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

/**
 * Panel όπου γίνεται η αναπαράσταση του παιχνιδιού
 */
public class GamePanel extends JPanel implements Runnable {

    transient final int originalTileSize = 16;
    final int scale = 3;

    public final int tileSize = originalTileSize * scale;
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenCol;
    final int screenHeight = tileSize * maxScreenRow;

    //Καθορισμός των διαστάσεων του κόσμου του λαβυρίνθου ανάλογα με την επιλεγμένη δυσκολία
    public final int maxWorldCol = 28
            + (Levels.difficulty.equals("Medium") ? 6
            : (Levels.difficulty.equals("Hard") ? 12 : 0));
    public final int maxWorldRow = maxWorldCol;
    public final int WorldWidth = tileSize * maxWorldCol;
    public final int WorldHeight = tileSize * maxWorldRow;

    TileManager tileM = new TileManager(this);

    KeyHandler keyH = new KeyHandler(this);
    Thread gameThread;
    Player player = new Player(this, keyH);
    public CollisionCheck collisionCheck = new CollisionCheck(this);
    public LinkedList<SuperObject> obj = new LinkedList<>();
    public AssetSetter aSetter = new AssetSetter(this);

    public LabyrinthFrame labyrinthFrame;
    //Μεταβλητές για την κατάσταση παιχνιδιού
    public int gameState;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int endState = 3;

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
     * Game loop εξατομικευμένο ώστε να τρέχει το παιχνίδι με 60 fps
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
                    //Ενέργεια που εκτελείται όταν χάνει ο παίκτης TODO(all) add death sound
                } else if (labyrinthFrame.hasLost) {
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