package game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertSame;

class EntityTest {

    @BeforeEach
    void setUp() {
        Entity.getImages();
    }

    @Test
    void getImage() {
        assertSame(Entity.death[6], Entity.death[5]);
    }
}