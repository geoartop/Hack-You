package game;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.security.SecureRandom;

import static org.junit.jupiter.api.Assertions.assertSame;

class EntityTest {

    Entity entity;
    Player player;

    @BeforeEach
    void setUp() {
        entity = new Entity();
    }

    @Test
    void getImage() {
        entity.getImages();
        assertSame(Entity.death[6], Entity.death[5]);

    }
}