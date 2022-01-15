package highscoreTest;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * <p>PlayerInfoTest class.</p>
 *
 * @author Team Hack-You
 * @version 1.0
 */
class PlayerInfoTest {

    PlayerInfo playerInfo;

    @BeforeEach
    void setUp() {
        playerInfo = new PlayerInfo("Mpampis", 99);
    }

    @AfterEach
    void tearDown() {
        playerInfo = null;
    }

    @Test
    @DisplayName("Comparison should work")
    void check() {
        PlayerInfo p2 = new PlayerInfo("Mpampis", 11);
        Assertions.assertEquals(PlayerInfo.greater, playerInfo.didGreater(p2));
    }

    @Test
    void testEquals() {
        PlayerInfo p2 = new PlayerInfo("Mpampis", 99);
        Assertions.assertEquals(p2, playerInfo);
    }
}