package game;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ButtonSetter {

    public static void setButton(JButton button, int x,int y,int width,int height,String font,int size,Object o,int style) {
        button.setBounds(x, y, width, height);
        button.setFocusable(false);
        button.setHorizontalTextPosition(JButton.CENTER);
        button.setForeground(Color.black);
        button.setFont(new Font(font, style, size));
        button.addActionListener((ActionListener) o);
    }
}