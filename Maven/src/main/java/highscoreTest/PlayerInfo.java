package highscoreTest;

/**
 * Ορισμός δεδομένων που θα γράφονται σε κάθε γραμμή του HighScore.txt
 *
 * @author Team Hack-You
 */
public final class PlayerInfo {
    private final String name;
    private final int score;

    /**
     * <p>Constructor for PlayerInfo.</p>
     *
     * @param name a {@link java.lang.String} object
     * @param score a int
     */
    public PlayerInfo(String name, int score) {
        this.name = name;
        this.score = score;
    }

    /**
     * <p>Getter for the field <code>name</code>.</p>
     *
     * @return a {@link java.lang.String} object
     */
    public String getName() {
        return name;
    }

    /**
     * <p>Getter for the field <code>score</code>.</p>
     *
     * @return a int
     */
    public int getScore() {
        return score;
    }
}
