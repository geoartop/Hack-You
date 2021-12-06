package game;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Κλάση που περιγράφει το παιχνίδι και τη "πλοκή"
 *
 * @author Team Hack-You
 */
public class Description extends UtilityFrame{

    Menu menu;

    public Description(Menu menu) {
        super();
        this.menu = menu;
        super.frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                menu.description.setEnabled(true);
                Description.super.closeFrame();
            }
        });
    }

}