package game;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * <p>UtilityFrameTest class.</p>
 *
 * @author Hack-You
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
            Assertions.assertTrue(utilityFrame.getIsOpen());
        }
    }

    @AfterEach
    void tearDown() {
        utilityFrames = null;
    }


}