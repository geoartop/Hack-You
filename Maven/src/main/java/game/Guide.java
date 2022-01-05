package game;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * <p>Περιγραφή οδηγιών</p>
 *
 * @author Team Hack-You
 * @version 1.0
 * @see UtilityFrame
 */
public class Guide extends UtilityFrame {

    /**
     * <p>Κατασκευαστής που καλείται όταν το guide ανοίγει από το παράθυρο options.</p>
     *
     * @param options : Το παράθυρο options από το οποίο κλήθηκε ο guide
     */
    public Guide(Options options) {
        super("Guide", 800, 600);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                options.setShowGuideStatus(true);
                frame.dispose();
            }
        });
        frame.add(backgroundLabel);

    }

    /**
     * <p>Κατασκευαστής που καλείται όταν το guide ανοίγει από το παράθυρο menu.</p>
     *
     * @param menu : Το παράθυρο menu από το οποίο κλήθηκε ο guide
     */
    public Guide(Menu menu) {
        super("Guide", 800, 600);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                menu.setHow2playStatus(true);
                frame.dispose();
            }
        });
        frame.add(backgroundLabel);
    }

}
