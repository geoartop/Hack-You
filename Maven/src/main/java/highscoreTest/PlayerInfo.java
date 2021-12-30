package highscoreTest;

import java.io.Serializable;
import java.util.Objects;

/**
 * <p>Ορισμός δεδομένων που θα γράφονται σε κάθε γραμμή του HighScore.txt</p>
 *
 * @author Team Hack-You
 * @version 1.0
 */
public final class PlayerInfo implements Serializable {

    /**
     * Serial number of persistent  data.
     * Required, because we will store PlayerInfo objects to txt (file)
     */
    private static final long serialVersionUID = 2L;

    private final String name;
    private final int score;

    /**
     * <p>Constructor for PlayerInfo.</p>
     *
     * @param name  a {@link java.lang.String} object
     * @param score a int
     */
    public PlayerInfo(String name, int score) {
        this.name = name;
        this.score = score;
    }

    /**
     * <p>Έλεγχος αν ο παίκτης έκανε προσωπικό ρεκόρ</p>
     *
     * @param obj an {@link java.lang.Object}
     * @return a boolean
     */
    public boolean didGreater(Object obj) {
        if (obj == null) {
            return false;
        }

        if (obj.getClass() != this.getClass()) {
            return false;
        }

        final PlayerInfo other = (PlayerInfo) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }

        return this.score > other.getScore();
    }

    /**
     * {@inheritDoc}
     * <p>Βελτιστοποιούμε την μέθοδο equals ώστε να εντοπίζεται σωστά
     * αν 2 αντικείμενα τύπου PlayerInfo είναι ίσα</p>
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (obj.getClass() != this.getClass()) {
            return false;
        }

        final PlayerInfo other = (PlayerInfo) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }

        return this.score == other.score;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return name + " : " + score;
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
