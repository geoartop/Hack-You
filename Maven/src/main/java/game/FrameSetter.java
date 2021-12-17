package game;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Πρότυπο για την επεξεργασία frames
 */
public class FrameSetter {

    /**
     * Μέθοδος εξατομίκευσης frames
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
     */
    public static void scaleBackground(JLabel label, int width, int height) {
        Image img = Main.background.getImage();
        Image temp = img.getScaledInstance(width - 15, height, Image.SCALE_SMOOTH);
        ImageIcon back = new ImageIcon(temp);
        label.setIcon(back);
        label.setBounds(0, 0, width, height);
    }

    public static BufferedImage scaleImage(BufferedImage original, int width, int height) {
        BufferedImage scaledImage = new BufferedImage(width, height, original.getType());
        Graphics2D g2 = scaledImage.createGraphics();
        g2.drawImage(original, 0, 0, width, height, null);
        g2.dispose();
        return scaledImage;
    }

    public static void scaleImgToLabel(JLabel label,int x , int y,int width, int height, ImageIcon imageIcon) {
        Image img = imageIcon.getImage();
        Image temp = img.getScaledInstance(width - 15, height, Image.SCALE_SMOOTH);
        ImageIcon back = new ImageIcon(temp);
        label.setIcon(back);
        label.setBounds(x, y, width, height);
    }

}
