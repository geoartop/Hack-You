package highscoreTest;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * <p>HighScoreTest class.</p>
 * ΤΑ ΤΕΣΤ ΔΕΝ ΓΙΝΟΝΤΑΙ ΜΕ ΤΗ ΣΕΙΡΑ FUCKING GENIUS!
 *
 * @author Hack-You
 * @version 1.0
 */
public class HighScoreTest {

    static HighScore highScore;

    @BeforeAll
    static void start() {
        highScore = new HighScore("Mpampis", 11);
        highScore = new HighScore("Mpampis", 12);
        for (int i = 0; i < 10; i++) {
            highScore = new HighScore(String.format("Mpampis%d", i), (int) (Math.random() * 10));
        }
    }

    @Test
    void functionalityCheck() {
        Assertions.assertNotEquals(0, HighScore.getPlayerInfoSize());
        Assertions.assertTrue(highScore.isRegistered("Mpampis", 11));
        Assertions.assertEquals(10, HighScore.getPlayerInfoSize());
    }

    @Test
    @DisplayName("Sort should work")
    void sort() {
        Assertions.assertTrue(highScore.isRegistered("Mpampis", 11));
        //Assertions.assertFalse(highScore.isRegistered("Mpampis0", 0));
    }

    @Test
    @DisplayName("File must not be writable")
    void fileStatus() {
        Assertions.assertFalse(Files.isWritable(Paths.get("src/main/resources/HighScore.txt")));
    }

    @Test
    @DisplayName("Result of check should be 2")
    void didGreaterTest() {
        Assertions.assertEquals(PlayerInfo.greater, highScore.getPlayerInfoElement(0).didGreater(highScore.getPlayerInfoElement(1)));
    }

    @AfterAll
    public static void tearDown() throws IOException {
        File file = new File("src/main/resources/HighScore.txt");
        //making the file as read/read-only using setWritable(status) method
        file.setWritable(true);
        new FileOutputStream("src/main/resources/HighScore.txt").close();
        file.setWritable(false);
    }

}
