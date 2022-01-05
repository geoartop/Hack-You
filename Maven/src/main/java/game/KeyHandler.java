package game;

import javax.swing.SwingUtilities;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * <p>Διαχείριση λειτουργιών των κουμπιών
 * WASD, Arrows, PAUSE, ESCAPE στο παιχνίδι</p>
 *
 * @author Team Hack-You
 * @version 1.0
 */
public class KeyHandler implements KeyListener {

    private boolean upPressed, downPressed, leftPressed, rightPressed;
    private final GamePanel gp;
    private boolean escPressed = false;
    private boolean quizTrig = false;

    /**
     * <p>stopMovement.</p>
     */
    public void stopMovement() {
        upPressed = false;
        downPressed = false;
        rightPressed = false;
        leftPressed = false;
    }

    /**
     * <p>keyIsPressed.</p>
     *
     * @return a boolean
     */
    public boolean keyIsPressed() {
        return upPressed || downPressed || rightPressed || leftPressed;
    }

    /**
     * <p>Getter for the field <code>upPressed</code>.</p>
     *
     * @return a boolean
     */
    public boolean getUpPressed() {
        return upPressed;
    }

    /**
     * <p>Getter for the field <code>downPressed</code>.</p>
     *
     * @return a boolean
     */
    public boolean getDownPressed() {
        return downPressed;
    }

    /**
     * <p>Getter for the field <code>leftPressed</code>.</p>
     *
     * @return a boolean
     */
    public boolean getLeftPressed() {
        return leftPressed;
    }

    /**
     * <p>Setter for the field <code>escPressed</code>.</p>
     *
     * @param escPressed a boolean
     */
    public void setEscPressed(boolean escPressed) {
        this.escPressed = escPressed;
    }

    /**
     * <p>Setter for the field <code>quizTrig</code>.</p>
     *
     * @param quizTrig a boolean
     */
    public void setQuizTrig(boolean quizTrig) {
        this.quizTrig = quizTrig;
    }

    /** {@inheritDoc} */
    @Override
    public void keyTyped(KeyEvent e) {

    }

    /**
     * <p>Constructor for KeyHandler.</p>
     *
     * @param gp a {@link game.GamePanel} object
     */
    public KeyHandler(GamePanel gp) {
        this.gp = gp;
    }

    /** {@inheritDoc} */
    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        setKeys(true, code);

        // Για να μην επιτρέπεται η συνέχιση του παιχνιδιού μέχρι να κλείσει το παράθυρο options/quiz
        if ((code == KeyEvent.VK_SPACE || code == KeyEvent.VK_P)
                && gp.labyrinthFrame.getHasStarted()
                && !Options.getIsActive() && !quizTrig) {
            if (gp.getGameState() == GamePanel.playState) {
                Menu.stopMusic();
                gp.labyrinthFrame.stopBar();
                gp.setGameState(GamePanel.pauseState);
            } else {
                if (ButtonSetter.getPlaySound())
                    Menu.continuePlaying();
                gp.setGameState(GamePanel.playState);
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
            if (gp.getGameState() == GamePanel.pauseState) {
                SwingUtilities.invokeLater(() -> new Options(gp));
                return;
            }
            gp.setGameState(GamePanel.pauseState);
            Menu.stopMusic();
            gp.labyrinthFrame.stopBar();
            SwingUtilities.invokeLater(() -> new Options(gp));
        }
    }

    /**
     * <p>setKey Status.</p>
     *
     * @param status a boolean
     * @param code   an int
     */
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

    /** {@inheritDoc} */
    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        setKeys(false, code);
    }
}
