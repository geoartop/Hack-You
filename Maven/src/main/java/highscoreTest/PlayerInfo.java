package highscoreTest;

/**
 * Ορισμός δεδομένων που θα γράφονται σε κάθε γραμμή του HighScore.txt
 */
public final class PlayerInfo {
    private final String name;
    private final int score;

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
