package game;

import org.junit.jupiter.api.*;

class UtilityFrameTest {

    Menu menu;
    UtilityFrame[] utilityFrames;

    @BeforeEach
    void setUp() {
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

    @Test
    @DisplayName("Should close all windows")
    void checkForDisposal() {

    }


    @AfterEach
    void tearDown() {
        utilityFrames = null;
    }


}