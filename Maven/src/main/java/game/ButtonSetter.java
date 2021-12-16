package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Πρότυπο για την επεξεργασία buttons
 */
public class ButtonSetter {

    public static Sound se = new Sound();
    public static boolean playSound = true;

    public static void setButton(JButton button, int x, int y, int width, int height, String font, int size, Object o, int style) {
        button.setBounds(x, y, width, height);
        button.setFocusable(false);
        button.setHorizontalTextPosition(JButton.CENTER);
        button.setForeground(Color.black);
        button.setFont(new Font(font, style, size));
        button.addActionListener((ActionListener) o);
    }

    public static void setPlaySound(boolean playSound) {
        ButtonSetter.playSound = playSound;
    }

    public static void playSE() {
        if (!playSound)
            return;
        se.setFile(1);
        se.play();
    }
}