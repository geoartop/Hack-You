package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;

/**
 * Περιγραφή παιχνιδιού και "πλοκής"
 *
 * @author Team Hack-You
 */
public class Description extends UtilityFrame {

    public Description(Menu menu) {
        super("Description", 800, 600);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                menu.description.setEnabled(true);
                Description.super.closeFrame();
            }
        });

        try {
            super.load("src/main/resources/Mythos.txt", textArea);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        textArea.setCaretPosition(0);
        frame.getContentPane().add(scrollPane);
        frame.add(backgroundLabel);
    }


}