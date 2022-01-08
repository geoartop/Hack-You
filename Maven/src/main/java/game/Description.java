package game;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * <p>Περιγραφή παιχνιδιού και "πλοκής".</p>
 *
 * @author Team Hack-You
 * @version 1.0
 * @see UtilityFrame
 */
public class Description extends UtilityFrame {

    /**
     * <p>Constructor for Description.</p>
     *
     * @param menu a {@link game.Menu} object
     */
    public Description(Menu menu) {
        super("Description", 800, 600);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                menu.setDescriptionStatus(true);
                Description.super.closeFrame();
            }
        });

        super.load("src/main/resources/Mythos.txt", textPane);

        textPane.setCaretPosition(0);
        frame.getContentPane().add(scrollPane);
        frame.add(backgroundLabel);
    }

}
