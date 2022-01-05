package game;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AssetSetterTest {

    GamePanel gamePanel;

    @BeforeEach
    void setUp() {
        gamePanel = new GamePanel(new LabyrinthFrame());
    }

    @Test
    void addElement() {
        assertNotEquals(0, gamePanel.obj.size());
    }

    @AfterEach
    void tearDown() {
        gamePanel = null;
    }
}