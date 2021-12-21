package highscoreTest;

import game.GraphicPane;
import game.UtilityFrame;
import game.WinFrame;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Παράθυρο στο οποίο εμφανίζονται τα highscores
 *
 * @author Team Hack-You
 */
public final class HighScoreFrame extends UtilityFrame {

    /**
     * Τα graphicPane που θα απεικονίζουν τις πληροφορίες κάθε καταχωρημένου παίκτη
     */
    private final GraphicPane[] graphicPanes
            = new GraphicPane[HighScore.getPlayerInfoSize()];
    private final HighScore highScore;

    /**
     * <p>Constructor for HighScoreFrame.</p>
     *
     * @param winFrame a {@link game.WinFrame} object
     */
    public HighScoreFrame(WinFrame winFrame) {
        super("HighScore Table", 800, 800);
        this.highScore = winFrame.getHighScore();
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                winFrame.setSeeHighScoresStatus(true);
                frame.dispose();
            }
        });

        setGraphicPanes();

        GraphicPane leadGraphicPane = new GraphicPane("Πίνακας Ηighscore", 800, 100, Color.black,new Font("Times new Roman", Font.BOLD + Font.ITALIC,35));
        leadGraphicPane.setBounds(0, 0, 800, 200);
        frame.add(leadGraphicPane);

        for (GraphicPane graphicPane : graphicPanes)
            frame.add(graphicPane);

        frame.add(backgroundLabel);
    }

    private void setGraphicPanes() {
        for (int i = 0; i < graphicPanes.length; i++) {
            graphicPanes[i] = new GraphicPane(
                    String.format(
                            "%2d) %s : %d", i + 1,
                            highScore.getPlayerInfoName(i),
                            highScore.getPlayerInfoScore(i)
                    ),
                    800, 50, (i + 1 == 1 ? new Color(134, 1, 1, 196)
                    : i + 1 == 2 ? new Color(128, 141, 141)
                            : (i + 1 == 3 ? new Color(112, 96, 61)
                            : Color.black)),new Font("Times new Roman", Font.BOLD,27));
            graphicPanes[i].setBounds(0, (i + 3) * 50, 800, 70);
        }
    }

}
