package game;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Περιγραφή οδηγιών
 *
 * @author Team Hack-You
 */
public class Guide extends UtilityFrame{

    /**
     * Κατασκευαστής που καλείται όταν το guide ανοίγει από το παράθυρο options
     * @param options : Το παράθυρο options από το οποίο κλήθηκε ο guide
     */
    public Guide(Options options) {
        super("Guide",800,600);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                options.showGuide.setEnabled(true);
                frame.dispose();
            }
        });
        frame.add(backgroundLabel);

    }

    /**
     * Κατασκευαστής που καλείται όταν το guide ανοίγει από το παράθυρο menu
     * @param menu : Το παράθυρο menu από το οποίο κλήθηκε ο guide
     */
    public Guide(Menu menu) {
        super("Guide",800,600);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                menu.how2play.setEnabled(true);
                frame.dispose();
            }
        });
        frame.add(backgroundLabel);
    }

}