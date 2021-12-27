package game;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class MenuTest {

    static Menu menu;

    @BeforeAll
    static void setUp() {
        menu = new Menu();
    }

    @Test
    void playMusic() {
        Menu.playMusic();
        Assertions.assertTrue(Menu.musicIsPlaying());
    }

    @Test
    void stopMusic() {
        Menu.stopMusic();
        Assertions.assertFalse(Menu.musicIsPlaying());
    }

    @AfterAll
    static void tearDown() {
        menu = null;
    }
}