package highscoreTest;

import java.util.Comparator;

public class scoresCompare implements Comparator<PlayerInfo> {

    @Override
    public int compare(PlayerInfo p1, PlayerInfo p2) {
        return p2.getScore() - p1.getScore();
    }
}
