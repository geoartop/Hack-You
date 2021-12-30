package highscoreTest;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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