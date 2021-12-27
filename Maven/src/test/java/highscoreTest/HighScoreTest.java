package highscoreTest;

import org.junit.jupiter.api.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * <p>highscoreTest.HighScoreTest class.</p>
 *
 * @author panagiotis
 * @version 1.0
 */
public class HighScoreTest {

    static HighScore highScore;

    @BeforeAll
    static void start() {
        highScore = new HighScore("Mpampis", 11);
    }

    @Test
    void checkHigh() {
        Assertions.assertNotEquals(0, HighScore.getPlayerInfoSize());
        Assertions.assertTrue(highScore.isRegistered("Mpampis", 11));
        new HighScore("Mpampis", 11);
        for (int i = 0; i < 10; i++) {
            new HighScore(String.format("Mpampis%d", i), i);
        }
        Assertions.assertEquals(10, HighScore.getPlayerInfoSize());
    }

    @Test
    @DisplayName("Sort should work")
    void checkSort() {
        Assertions.assertTrue(highScore.isRegistered("Mpampis", 11));
        Assertions.assertFalse(highScore.isRegistered("Mpampis0", 0));
    }

    @Test
    void fileStatus() {
        Assertions.assertFalse(Files.isWritable(Paths.get("src/main/resources/HighScore.txt")));
    }

    @Test
    @DisplayName("No playerInfo should show up twice")
    void existsOnce() {
        for (int i = 0; i < HighScore.getPlayerInfoSize(); i++) {
            for (int j = i + 1; j < HighScore.getPlayerInfoSize(); j++) {
                Assertions.assertNotEquals(highScore.getPlayerInfoElement(i), highScore.getPlayerInfoElement(j));
            }
        }
    }

    @AfterAll
    public static void clearFile() throws IOException {
        File file = new File("src/main/resources/HighScore.txt");
        //making the file as read/read-only using setWritable(status) method
        file.setWritable(true);
        new FileOutputStream("src/main/resources/HighScore.txt").close();
        file.setWritable(false);
    }

}
