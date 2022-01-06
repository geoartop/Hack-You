package game;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class SuperObjectTest {

    static SuperObject superObject;

    @BeforeAll
    static void setUp() {
        superObject = new OBJ_Exit();
    }

    @Test
    void playSE() {
        superObject.playSE();
        Assertions.assertTrue(SuperObject.se.isPlaying());
    }

    @AfterAll
    static void tearDown() {
        superObject = null;
    }

}