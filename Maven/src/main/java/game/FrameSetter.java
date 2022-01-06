package game;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

/**
 * <p>Πρότυπο για την επεξεργασία frames.</p>
 *
 * @author Team Hack-You
 * @version 1.0
 */
public final class FrameSetter {

    /**
     * <p>Μέθοδος εξατομίκευσης frames</p>
     *
     * @param frame  a {@link javax.swing.JFrame} object
     * @param title  a {@link java.lang.String} object
     * @param width  an int
     * @param height an int
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
     * <p>Μέθοδος που τοποθετεί την background εικόνα scaled σε ένα label</p>
     *
     * @param label  a {@link javax.swing.JLabel} object
     * @param width  an int
     * @param height an int
     */
    public static void scaleBackground(JLabel label, int width, int height) {
        Image img = Main.background.getImage();
        Image temp = img.getScaledInstance(width - 15, height, Image.SCALE_SMOOTH);
        ImageIcon back = new ImageIcon(temp);
        label.setIcon(back);
        label.setBounds(0, 0, width, height);
    }

    /**
     * <p>Scaling εικόνας με δεδομένο width ,height</p>
     *
     * @param original η εικόνα στην οποία θα γίνει το scaling τύπου {@link java.awt.image.BufferedImage}
     * @param width    an int
     * @param height   an int
     * @return η original scaled εικόνα τύπου {@link java.awt.image.BufferedImage}
     */
    public static BufferedImage scaleImage(BufferedImage original, int width, int height) {
        BufferedImage scaledImage = new BufferedImage(width, height, original.getType());
        Graphics2D g2 = scaledImage.createGraphics();
        g2.drawImage(original, 0, 0, width, height, null);
        g2.dispose();
        return scaledImage;
    }

    /**
     * <p>Μέθοδος που τοποθετεί μια εικόνα scaled σε ένα label.</p>
     *
     * @param label     a {@link javax.swing.JLabel} object
     * @param x         an int
     * @param y         an int
     * @param width     an int
     * @param height    an int
     * @param imageIcon a {@link javax.swing.ImageIcon} object
     */
    public static void scaleImgToLabel(JLabel label, int x, int y, int width, int height, ImageIcon imageIcon) {
        Image img = imageIcon.getImage();
        Image temp = img.getScaledInstance(width - 15, height, Image.SCALE_SMOOTH);
        ImageIcon back = new ImageIcon(temp);
        label.setIcon(back);
        label.setBounds(x, y, width, height);
    }

}
