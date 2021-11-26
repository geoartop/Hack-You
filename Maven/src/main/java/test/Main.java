package test;
import javax.swing.*;
import java.io.FileNotFoundException;

public class Main {

    protected static final ImageIcon icon = new ImageIcon("src/main/resources/maze-icon.png");
    protected static final ImageIcon background = new ImageIcon("src/main/resources/background-alt4 - Copy.jpg");

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Avatar2::new);
        /*SwingUtilities.invokeLater(() -> {
            try {
                new Quiz();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });*/
    }
}
