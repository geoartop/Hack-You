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
     * Διαστάσεις παραθύρου
     */
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    /**
     * <p>Κατασκευαστής που καλείται όταν το guide ανοίγει από το παράθυρο options.</p>
     *
     * @param options a {@link game.Options} object
     *                <p>Το παράθυρο options από το οποίο κλήθηκε ο guide</p>
     */
    public Guide(Options options) {
        super("Guide", WIDTH, HEIGHT);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                options.setShowGuideStatus(true);
                frame.dispose();
            }
        });
        setup();

    }

    /**
     * <p>setup.</p>
     */
    private void setup() {
        super.load("src/main/resources/Guide.txt", textArea);
        textArea.setCaretPosition(0);
        frame.getContentPane().add(scrollPane);
        frame.add(backgroundLabel);
    }

    /**
     * <p>Κατασκευαστής που καλείται όταν το guide ανοίγει από το παράθυρο menu.</p>
     *
     * @param menu a {@link game.Menu} object
     *             <p>Το παράθυρο menu από το οποίο κλήθηκε ο guide</p>
     */
    public Guide(Menu menu) {
        super("Guide", WIDTH, HEIGHT);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                menu.setHow2playStatus(true);
                frame.dispose();
            }
        });
        setup();

    }

}
