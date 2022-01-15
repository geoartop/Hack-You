package game;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * <p>UtilityFrameTest class.</p>
 *
 * @author Team Hack-You
 * @version 1.0
 */
class UtilityFrameTest {

    Menu menu;
    UtilityFrame[] utilityFrames;

    @BeforeEach
    void setUp() {
        Menu.setup();
        Guide.setupImages();
        menu = new Menu();
        utilityFrames = new UtilityFrame[]{new Credits(menu), new Description(menu), new Guide(menu)};
    }

    @Test
    @DisplayName("Should be false after closeWindows")
    void checkOpen() {
        for (UtilityFrame utilityFrame : utilityFrames) {
            if (utilityFrame == null) {
                continue;
            }
            Assertions.assertTrue(utilityFrame.getIsOpen());
        }
    }

    @Test
    @DisplayName("All windows should close")
    void check() {
        utilityFrames[2] = null;
        UtilityFrame.check(utilityFrames);
        for (UtilityFrame utilityFrame : utilityFrames) {
            if (utilityFrame == null) {
                continue;
            }
            Assertions.assertFalse(utilityFrame.getIsOpen());
        }
    }

    @AfterEach
    void tearDown() {
        utilityFrames = null;
    }


}