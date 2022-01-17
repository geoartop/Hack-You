package game;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Scanner;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;

/**
 * <p>Περιγραφή οδηγιών.</p>
 *
 * @author Team Hack-You
 * @version 1.0
 * @see UtilityFrame
 */
public final class Guide extends UtilityFrame {

    /**
     * Διαστάσεις παραθύρου
     */
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    /**
     * Εικόνες αρχείου
     */
    private static ImageIcon qmark;
    private static ImageIcon coin;
    private static ImageIcon exit;
    private static ImageIcon spikes;
    private static final int IMG_WIDTH = 50;
    private static final int IMG_HEIGHT = 50;

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
        load("/Guide.txt", textPane);
        textPane.setCaretPosition(0);
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

    /**
     * {@inheritDoc}
     */
    @Override
    protected void load(String pathname, JTextPane textPane) {
        InputStream is = getClass().getResourceAsStream(pathname);
        StyledDocument doc = textPane.getStyledDocument();
        int counter = 0;
        try {
            Scanner q = new Scanner(Objects.requireNonNull(is), "UTF-8");
            while (q.hasNextLine()) {
                counter++;
                if (counter == 17) {
                    textPane.insertIcon(qmark);
                } else if (counter == 19) {
                    textPane.insertIcon(spikes);
                } else if (counter == 21) {
                    textPane.insertIcon(coin);
                } else if (counter == 23) {
                    textPane.insertIcon(exit);
                }
                doc.insertString(doc.getLength(), q.nextLine() + "\n", null);
            }
            q.close();

        } catch (BadLocationException e) {
            e.printStackTrace();
        }

    }

    /**
     * <p>setupImages.</p>
     */
    static void setupImages() {
        try {
            BufferedImage q = ImageIO.read(Objects.requireNonNull
                    (Guide.class.getResourceAsStream(("/icons/qmark.png"))));
            qmark = new ImageIcon(FrameSetter.scaleImage(q, IMG_WIDTH, IMG_HEIGHT));
            BufferedImage c = ImageIO.read(Objects.requireNonNull
                    (Guide.class.getResourceAsStream(("/goldCoin/goldCoin5.png"))));
            coin = new ImageIcon(FrameSetter.scaleImage(c, IMG_WIDTH, IMG_HEIGHT));
            BufferedImage e = ImageIO.read(Objects.requireNonNull
                    (Guide.class.getResourceAsStream(("/icons/exit.png"))));
            exit = new ImageIcon(FrameSetter.scaleImage(e, IMG_WIDTH, IMG_HEIGHT));
            BufferedImage s = ImageIO.read(Objects.requireNonNull
                    (Guide.class.getResourceAsStream(("/spikes/spike4.png"))));
            spikes = new ImageIcon(FrameSetter.scaleImage(s, IMG_WIDTH, IMG_HEIGHT));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
