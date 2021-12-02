package game;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    final int originalTileSize = 16;
    final int scale = 3;

    public final int tileSize = originalTileSize * scale;
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenCol;
    final int screenHeight = tileSize * maxScreenRow;

    TileManager tileM = new TileManager(this);
    private final int FPS = 60;

    KeyHandler keyH = new KeyHandler(this);
    Thread gameThread;
    Player player = new Player(this, keyH);
    public CollisionCheck collisionCheck = new CollisionCheck(this);

    //Κατάσταση παιχνιδιού
    public int gameState;
    public final int playState = 1;
    public final int pauseState = 2;

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
        startGameThread();
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
        //Για να μην μπορεί να κουνηθεί ο παίκτης πριν πατηθεί το κουμπί start
        gameState = pauseState;
    }

    /**
     * Game loop customized ώστε να τρέχει το παιχνίδι με 60 fps
     */
    @Override
    public void run() {

        double drawInterval = 1000000000 / FPS;
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
                repaint();
                delta--;
            }
        }
    }

    public void update() {
        if (gameState == playState)
            player.update();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        tileM.draw(g2);
        player.draw(g2);

        //Για να ζωγραφίσει στην οθόνη τη λέξη ΠΑΥΣΗ σε περίπτωση pause
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
