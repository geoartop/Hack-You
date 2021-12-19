package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Πρότυπο για την επεξεργασία buttons
 */
public final class ButtonSetter {

    private static final Sound se = new Sound();
    /**
     * Αν θα αναπαράγεται ήχος από το παιχνίδι ή όχι
     */
    private static boolean playSound = true;

    public static void setButton(JButton button, int x, int y, int width, int height, String font, int size, Object o, int style) {
        button.setBounds(x, y, width, height);
        button.setFocusable(false);
        button.setHorizontalTextPosition(JButton.CENTER);
        button.setForeground(Color.black);
        button.setFont(new Font(font, style, size));
        button.addActionListener((ActionListener) o);
    }

    public static boolean getPlaySound() {
        return playSound;
    }

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