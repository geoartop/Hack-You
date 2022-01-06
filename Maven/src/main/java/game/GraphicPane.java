package game;

import javax.swing.JComponent;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

/**
 * <p>Component για την απεικόνιση text στο κέντρο ενός παραθύρου</p>
 *
 * @author Team Hack-You
 * @version 1.0
 */
public final class GraphicPane extends JComponent {

    /**
     * Serial number of persistent  data.
     * Required, because JComponent implements serializable.
     */
    private static final long serialVersionUID = 543L;

    private final String text;
    private final int screenWidth;
    private final int y;
    private final Color color;
    private final Font font;

    /**
     * <p>Constructor for GraphicPane.</p>
     *
     * @param text        a {@link java.lang.String} object
     * @param screenWidth an int
     * @param y           an int
     * @param color       a {@link java.awt.Color} object
     * @param font        a {@link java.awt.Font} object
     */
    public GraphicPane(String text, int screenWidth, int y, Color color, Font font) {
        super();
        this.text = text;
        this.screenWidth = screenWidth;
        this.y = y;
        this.color = color;
        this.font = font;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(color);
        g2.setFont(font);
        int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = screenWidth / 2 - length / 2;
        g2.drawString(text, x, y);
        g2.dispose();
    }
}
