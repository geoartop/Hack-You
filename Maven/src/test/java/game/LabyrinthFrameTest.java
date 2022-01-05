package game;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LabyrinthFrameTest {

    LabyrinthFrame labyrinthFrame;

    @BeforeEach
    void setUp() {
        LabyrinthFrame.setLabyrinth();
        labyrinthFrame = new LabyrinthFrame();
    }

    @Test
    void getHasStarted() {
        Assertions.assertFalse(labyrinthFrame.getHasStarted());
    }

}