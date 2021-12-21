package game;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Πρότυπο για την επεξεργασία frames
 *
 * @author Team Hack-You
 */
public final class FrameSetter {

    /**
     * Μέθοδος εξατομίκευσης frames
     *
     * @param frame a {@link javax.swing.JFrame} object
     * @param title a {@link java.lang.String} object
     * @param width a int
     * @param height a int
     */
    public static void setFrame(JFrame frame, String title, int width, int height) {
        frame.setTitle(title); //setTitle of frame
        frame.setResizable(false);
        frame.setSize(width, height);
        frame.setVisible(true);
        frame.setLayout(null);
        frame.setIconImage(Main.icon.getImage());
        //Για να εμφανίζεται στο κέντρο της οθόνης του χρήστη
        frame.setLocationRelativeTo(null);
    }

    /**
     * Μέθοδος που τοποθετεί την background εικόνα scaled σε ένα label
     *
     * @param label a {@link javax.swing.JLabel} object
     * @param width a int
     * @param height a int
     */
    public static void scaleBackground(JLabel label, int width, int height) {
        Image img = Main.background.getImage();
        Image temp = img.getScaledInstance(width - 15, height, Image.SCALE_SMOOTH);
        ImageIcon back = new ImageIcon(temp);
        label.setIcon(back);
        label.setBounds(0, 0, width, height);
    }

    /**
     * Scaling εικόνας με δεδομένο width ,height
     *
     * @param original η εικόνα στην οποία θα γίνει το scaling
     * @param width πλάτος
     * @param height μήκος
     * @return η original scaled
     */
    public static BufferedImage scaleImage(BufferedImage original, int width, int height) {
        BufferedImage scaledImage = new BufferedImage(width, height, original.getType());
        Graphics2D g2 = scaledImage.createGraphics();
        g2.drawImage(original, 0, 0, width, height, null);
        g2.dispose();
        return scaledImage;
    }

    /**
     * Μέθοδος που τοποθετεί μια εικόνα scaled σε ένα label
     *
     * @param label a {@link javax.swing.JLabel} object
     * @param x a int
     * @param y a int
     * @param width a int
     * @param height a int
     * @param imageIcon a {@link javax.swing.ImageIcon} object
     */
    public static void scaleImgToLabel(JLabel label,int x , int y,int width, int height, ImageIcon imageIcon) {
        Image img = imageIcon.getImage();
        Image temp = img.getScaledInstance(width - 15, height, Image.SCALE_SMOOTH);
        ImageIcon back = new ImageIcon(temp);
        label.setIcon(back);
        label.setBounds(x, y, width, height);
    }

}
