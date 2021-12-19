package highscoreTest;

import game.GraphicPane;
import game.UtilityFrame;
import game.WinFrame;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Παράθυρο στο οποίο εμφανίζονται τα highscores
 */
public final class HighScoreFrame extends UtilityFrame {

    private final GraphicPane[] graphicPanes = new GraphicPane[HighScore.getPlayerInfoSize()];
    private final HighScore highScore;

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

        GraphicPane leadGraphicPane = new GraphicPane("Πίνακας Ηighscore", 800, 100, Color.black, 35, 1);
        leadGraphicPane.setBounds(0, 0, 800, 200);
        frame.add(leadGraphicPane);

        for (GraphicPane graphicPane : graphicPanes)
            frame.add(graphicPane);

        frame.add(backgroundLabel);
    }

    private void setGraphicPanes() {
        for (int i = 0; i < graphicPanes.length; i++) {
            graphicPanes[i] = new GraphicPane(
                    String.format("%2d) %s : %d", i + 1,
                            highScore.getPlayerInfoName(i),
                            highScore.getPlayerInfoScore(i)),
                    800, 50, Color.black, 25, 1);
            graphicPanes[i].setBounds(0, (i + 3) * 50,800,70 );
        }
    }

}
