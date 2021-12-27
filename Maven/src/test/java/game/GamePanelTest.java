package game;

import org.junit.jupiter.api.*;

class GamePanelTest {

    GamePanel gamePanel;

    @BeforeEach
    void setUp() {
        gamePanel = new GamePanel(new LabyrinthFrame());
    }

    @AfterEach
    void tearDown() {
        gamePanel = null;
    }

    @Test
    void setupGame() {
        Assertions.assertTrue(gamePanel.obj.size() != 0);
    }

    @Test
    @DisplayName("PLayer should not move")
    void playerStabilize() {
        Assertions.assertFalse(gamePanel.keyH.keyIsPressed());
    }
}