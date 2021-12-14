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
        setKeys(true, code);

        // Για να μην επιτρέπεται η συνέχιση του παιχνιδιού μέχρι να κλείσει το παράθυρο options/quiz
        if (code == KeyEvent.VK_SPACE && gp.labyrinthFrame.hasStarted && !Options.isActive && !quizTrig) {
            if (gp.gameState == gp.playState) {
                Menu.stopMusic();
                gp.labyrinthFrame.stopBar();
                gp.gameState = gp.pauseState;
            } else {
                if(ButtonSetter.playSound)
                    Menu.continuePlaying();
                gp.gameState = gp.playState;
                gp.labyrinthFrame.updateBar(0);
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
                SwingUtilities.invokeLater(() -> new Options(gp));
                return;
            }
            gp.gameState = gp.pauseState;
            Menu.stopMusic();
            gp.labyrinthFrame.stopBar();
            SwingUtilities.invokeLater(() -> new Options(gp));
        }
    }

    private void setKeys(boolean status, int code) {
        if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP)
            upPressed = status;
        if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN)
            downPressed = status;
        if (code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT)
            leftPressed = status;
        if (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT)
            rightPressed = status;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        setKeys(false, code);
    }
}