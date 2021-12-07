package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Κλάση που περιγράφει το παιχνίδι και τη "πλοκή"
 *
 * @author Team Hack-You
 */

//TODO(Mallikoko): Φτιάξε καλύτερα την εμφάνιση του κειμένου
public class Description extends UtilityFrame {

    Menu menu;
    private ArrayList<String> description = new ArrayList<>();
    JTextArea textArea = new JTextArea();

    public Description(Menu menu) {
        super();
        this.menu = menu;
        super.frame.setTitle("Description");
        super.frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                menu.description.setEnabled(true);
                Description.super.closeFrame();
            }
        });
        frame.setSize(800,800);

        textArea.setBounds(0, 0, 800, 800);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setOpaque(false);
        textArea.setForeground(Color.black);
        textArea.setFont(new Font("Calibri", Font.LAYOUT_LEFT_TO_RIGHT, 15));
        textArea.setEditable(false);

        try {
            load();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        frame.add(textArea);
        frame.add(backgroundLabel);
    }

    private void load() throws FileNotFoundException {
        Scanner q = new Scanner(new File("src/main/resources/Mythos.txt"));
        while (q.hasNextLine())
            description.add(q.nextLine());
        textArea.setText(String.valueOf(description));
    }

}