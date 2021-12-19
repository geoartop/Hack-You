package game;

import javax.swing.JComponent;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

/**
 * Component για την απεικόνιση text στο κέντρο ενός παραθύρου
 */
public final class GraphicPane extends JComponent {

    private final String text;
    private final int screenWidth;
    private final int y;
    private final Color color;
    private final int size;
    private final int style;

    public GraphicPane(String text, int screenWidth, int y, Color color, int size, int style) {
        super();
        this.text = text;
        this.screenWidth = screenWidth;
        this.y = y;
        this.color = color;
        this.size = size;
        this.style = style;
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(color);
        //g2.setFont(g2.getFont().deriveFont(Font.ITALIC, 20F));
        g2.setFont(new Font("Times new Roman", style, size));
        int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = screenWidth / 2 - length / 2;
        g2.drawString(text, x, y);
        g2.dispose();
    }
}