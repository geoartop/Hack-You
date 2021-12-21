package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Πρότυπο για την επεξεργασία buttons
 *
 * @author Team Hack-You
 *
 */
public final class ButtonSetter {

    private static final Sound se = new Sound();
    /**
     * Αν θα αναπαράγεται ήχος από το παιχνίδι ή όχι
     */
    private static boolean playSound = true;

    /**
     * <p>setButton.</p>
     *
     * @param button a {@link javax.swing.JButton} object
     * @param x position x
     * @param y position x
     * @param width width
     * @param height height
     * @param font a {@link Font} object
     * @param o an {@link java.awt.event.ActionListener} object
     */
    public static void setButton(JButton button, int x, int y, int width, int height,Font font,Object o) {
        button.setBounds(x, y, width, height);
        button.setFocusable(false);
        button.setHorizontalTextPosition(JButton.CENTER);
        button.setForeground(Color.black);
        button.setFont(font);
        button.addActionListener((ActionListener) o);
    }

    /**
     * <p>Getter for the field <code>playSound</code>.</p>
     *
     * @return a boolean
     */
    public static boolean getPlaySound() {
        return playSound;
    }

    /**
     * <p>Setter for the field <code>playSound</code>.</p>
     *
     * @param playSound a boolean
     */
    public static void setPlaySound(boolean playSound) {
        ButtonSetter.playSound = playSound;
    }

    /**
     * Αναπαραγωγή sound effect κλικ κουμπιού
     */
    public static void playSE() {
        if (!playSound)
            return;
        se.setFile(1);
        se.play();
    }
}
