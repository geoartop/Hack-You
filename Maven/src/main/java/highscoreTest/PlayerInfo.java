package highscoreTest;

/**
 * Ορισμός δεδομένων που θα γράφονται σε κάθε γραμμή του HighScore.txt
 */
public class PlayerInfo {
    private String name;
    private int score;

    public PlayerInfo(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }
}
