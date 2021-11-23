import javax.swing.*;
import java.awt.*;

public class ButtonSetter {

      public static void setButton(JButton button, int x,int y,int width,int height,String font,int size) {
        button.setBounds(x, y, width, height);
        button.setFocusable(false);
        button.setHorizontalAlignment(JButton.CENTER);
        button.setFont(new Font(font, Font.ITALIC, size));
    }
}
