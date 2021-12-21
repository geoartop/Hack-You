package highscoreTest;

import java.util.Comparator;

/**
 * <p>scoresCompare class.</p>
 *
 * @author Team Hack-You
 */
public class scoresCompare implements Comparator<PlayerInfo> {

    /**
     * {@inheritDoc}
     *
     * <p>Σύγκριση των score</p>
     */
    @Override
    public int compare(PlayerInfo p1, PlayerInfo p2) {
        return p2.getScore() - p1.getScore();
    }
}
