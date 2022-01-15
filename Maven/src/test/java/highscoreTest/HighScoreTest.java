package highscoreTest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * <p>HighScoreTest class.</p>
 *
 * @author Team Hack-You
 * @version 1.0
 * @since A[maze]ing
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
    @DisplayName("Registers should be assigned to LinkedList")
    void functionalityCheck() {
        Assertions.assertNotEquals(0, HighScore.getPlayerInfoSize());
        Assertions.assertTrue(highScore.isRegistered("Mpampis", 11));
        Assertions.assertEquals(10, HighScore.getPlayerInfoSize());
    }

    @Test
    @DisplayName("Sort should work")
    void sort() {
        Assertions.assertEquals(PlayerInfo.greater, highScore.getPlayerInfoElement(0).didGreater(highScore.getPlayerInfoElement(1)));
    }

    @Test
    @DisplayName("File must not be writable")
    void fileStatus() {
        Assertions.assertFalse(Files.isWritable(Paths.get("./HighScore.txt")));
    }

    @Test
    @DisplayName("File Should be created")
    void fileExists() throws IOException {
        File file = new File("./HighScore.txt");
        Assertions.assertFalse(file.createNewFile());
        file.setWritable(false);
    }

    @AfterAll
    static void tearDown() throws IOException {
        File file = new File("./HighScore.txt");
        //making the file as read/read-only using setWritable(status) method
        file.setWritable(true);
        //Clear file contents
        new FileOutputStream("./HighScore.txt").close();
        file.setWritable(false);
    }

}
