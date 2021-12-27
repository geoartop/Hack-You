package game;

import org.junit.jupiter.api.*;

class SuperObjectTest {

    static SuperObject superObject;

    @BeforeAll
    static void setUp() {
        superObject = new OBJ_Exit();
    }

    @Test
    void playSE() {
        superObject.playSE();
        Assertions.assertTrue(superObject.se.isPlaying());

    }

    @AfterAll
    static void tearDown() {
        superObject = null;
    }

}