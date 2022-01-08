package game;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
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
public class Guide extends UtilityFrame {

    /**
     * Διαστάσεις παραθύρου
     */
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    private static boolean hasLoaded = false;

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
        if (!hasLoaded) {
            try {
                BufferedImage q = ImageIO.read(Objects.requireNonNull
                        (getClass().getResourceAsStream(("/icons/qmark.png"))));
                qmark = new ImageIcon(FrameSetter.scaleImage(q, IMG_WIDTH, IMG_HEIGHT));
                BufferedImage c = ImageIO.read(Objects.requireNonNull
                        (getClass().getResourceAsStream(("/guidecoin.png"))));
                coin = new ImageIcon(FrameSetter.scaleImage(c, IMG_WIDTH, IMG_HEIGHT));
                BufferedImage e = ImageIO.read(Objects.requireNonNull
                        (getClass().getResourceAsStream(("/icons/exit.png"))));
                exit = new ImageIcon(FrameSetter.scaleImage(e, IMG_WIDTH, IMG_HEIGHT));
                BufferedImage s = ImageIO.read(Objects.requireNonNull
                        (getClass().getResourceAsStream(("/spikes/guidespikes.png"))));
                spikes = new ImageIcon(FrameSetter.scaleImage(s, IMG_WIDTH, IMG_HEIGHT));
            } catch (IOException e) {
                e.printStackTrace();
            }
            hasLoaded = true;
        }

        load("src/main/resources/Guide.txt", textPane);
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
        StyledDocument doc = textPane.getStyledDocument();
        int counter = 0;
        try {
            Scanner q = new Scanner(new File(pathname), "UTF-8");
            while (q.hasNextLine()) {
                counter++;
                if (counter == 16) {
                    textPane.insertIcon(qmark);
                } else if (counter == 18) {
                    textPane.insertIcon(spikes);
                } else if (counter == 20) {
                    textPane.insertIcon(coin);
                } else if (counter == 22) {
                    textPane.insertIcon(exit);
                }
                doc.insertString(doc.getLength(), q.nextLine() + "\n", null);
            }

        } catch (FileNotFoundException | BadLocationException e) {
            e.printStackTrace();
        }

    }

}
