package game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertSame;

/**
 * <p>EntityTest class.</p>
 *
 * @author Team Hack-You
 * @version 1.0
 */
class EntityTest {

    @BeforeEach
    void setUp() {
        Entity.getImages();
    }

    @Test
    @DisplayName("The last 2 images of Entity.death must be the same")
    void getImage() {
        assertSame(Entity.death[6], Entity.death[5]);
    }
}