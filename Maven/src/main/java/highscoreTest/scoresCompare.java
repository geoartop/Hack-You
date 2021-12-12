package highscoreTest;

import java.util.Comparator;

public class scoresCompare implements Comparator<PlayerInfo> {

    /**
     * Σύγκριση των score
     * @param p1 : 1o score
     * @param p2 : 2o score
     * @return : η διαφορά των 2 scores
     */
    @Override
    public int compare(PlayerInfo p1, PlayerInfo p2) {
        return p2.getScore() - p1.getScore();
    }
}
