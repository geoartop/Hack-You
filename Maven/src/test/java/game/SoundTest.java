package game;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * <p>SoundTest class.</p>
 *
 * @author Team Hack-You
 * @version 1.0
 */
class SoundTest {

    static Sound sound;
    int start_volume = (Options.min + Options.max) / 2;

    @BeforeEach
    void setUp() {
        sound = new Sound();
    }

    @Test
    @DisplayName("When slider value changes the volume should change")
    void stateChanged() {
        Options.setSliderValue((int) (Math.random() * 10));
        Assertions.assertNotEquals(Options.getSliderValue(), start_volume);
    }

    @AfterEach
    void tearDown() {
        sound = null;
    }
}