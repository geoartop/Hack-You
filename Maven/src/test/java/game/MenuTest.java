package game;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * <p>MenuTest class.</p>
 *
 * @author Team Hack-You
 * @version 1.0
 */
class MenuTest {

    static Menu menu;

    @BeforeAll
    static void setUp() {
        Menu.setup();
        menu = new Menu();
    }

    @Test
    @DisplayName("Music should be playing")
    void playMusic() {
        Assertions.assertTrue(Menu.musicIsPlaying());
    }

    @Test
    @DisplayName("Should be known when music starts/stops")
    void stopMusic() {
        Assertions.assertFalse(Menu.checkMusic());
        Menu.stopMusic();
        Assertions.assertFalse(Menu.musicIsPlaying());
        Assertions.assertTrue(Menu.checkMusic());
    }

    @AfterAll
    static void tearDown() {
        menu = null;
    }
}