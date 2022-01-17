package game;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * <p>SoundTest class.</p>
 *
 * @author Team Hack-You
 * @version 1.0
 */
class SoundSettingsTest {

    int start_volume = (SoundSettings.min + SoundSettings.max) / 2;

    @Test
    @DisplayName("When slider value changes the volume should change")
    void stateChanged() {
        SoundSettings.setSliderValue((int) (Math.random() * 10));
        Assertions.assertNotEquals(SoundSettings.getSliderValue(), start_volume);
    }

    @Test
    @DisplayName("When sound is off slider should be disabled")
    void sliderDisabledTest() {
        Menu menu = new Menu();
        ButtonSetter.setPlaySound(false);
        new SoundSettings(menu);
        Assertions.assertFalse(SoundSettings.getSliderStatus());
    }

}