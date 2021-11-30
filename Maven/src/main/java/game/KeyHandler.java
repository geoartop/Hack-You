package game;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Κλάση η οποία διαχειρίζεται τις λειτουργίες των κουμπιών του υπολογιστή
 * WASD, Arrows, PAUSE, ESCAPE
 */
public class KeyHandler implements KeyListener {

    public boolean upPressed, downPressed, leftPressed, rightPressed;
    public GamePanel gp;

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

        if (code == KeyEvent.VK_SPACE && LabyrinthFrame.hasStarted) {
            if (gp.gameState == gp.playState) {
                LabyrinthFrame.stopBar();
                gp.gameState = gp.pauseState;
            } else {
                gp.gameState = gp.playState;
                LabyrinthFrame.updateBar(0);
            }
        }
        if (code == KeyEvent.VK_ESCAPE) {
            if (gp.gameState == gp.pauseState) {
                SwingUtilities.invokeLater(() -> new Options(gp));
                return;
            }
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

        System.out.printf("You pressed %c \n", e.getKeyChar());
    }
}
