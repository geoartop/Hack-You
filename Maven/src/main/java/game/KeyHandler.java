package game;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Κλάση η οποία διαχειρίζεται τις λειτουργίες των κουμπιών του υπολογιστή
 * WASD, Arrows, PAUSE, ESCAPE
 */
public class KeyHandler implements KeyListener {

    protected boolean upPressed, downPressed, leftPressed, rightPressed;
    private GamePanel gp;
    protected static boolean escPressed = false;
    protected static boolean quizTrig = false;

    @Override
    public void keyTyped(KeyEvent e) {

    }

    public KeyHandler(GamePanel gp) {
        this.gp = gp;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP)
            upPressed = true;
        if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN)
            downPressed = true;
        if (code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT)
            leftPressed = true;
        if (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT)
            rightPressed = true;

        // Για να μην επιτρέπεται η συνέχιση του παιχνιδιού μέχρι να κλείσει το παράθυρο options/quiz
        if (code == KeyEvent.VK_SPACE && LabyrinthFrame.hasStarted && !Options.isActive && !quizTrig) {
            if (gp.gameState == gp.playState) {
                LabyrinthFrame.stopBar();
                gp.gameState = gp.pauseState;
            } else {
                gp.gameState = gp.playState;
                LabyrinthFrame.updateBar(0);
            }
        }
        if (code == KeyEvent.VK_ESCAPE && !quizTrig) {
            //Για να μπορεί ο χρήστης να ανοίξει μόνο ένα παράθυρο options χωρίς να διακόπτεται η ομαλή ροή του προγράμματος
            if (!escPressed) {
                escPressed = true;
            } else {
                return;
            }
            if (gp.gameState == gp.pauseState) {
                //Για να μην κολλήσει η κίνηση του παίκτη
                //gp.player.stabilizePlayer();
                SwingUtilities.invokeLater(() -> new Options(gp));
                return;
            }
            //Για να μην κολλήσει η κίνηση του παίκτη
            //gp.player.stabilizePlayer();
            gp.gameState = gp.pauseState;
            LabyrinthFrame.stopBar();
            SwingUtilities.invokeLater(() -> new Options(gp));
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP)
            upPressed = false;
        if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN)
            downPressed = false;
        if (code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT)
            leftPressed = false;
        if (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT)
            rightPressed = false;


    }
}