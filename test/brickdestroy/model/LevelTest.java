package brickdestroy.model;

import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class LevelTest {

    Rectangle drawArea = new Rectangle(0,0,40,60)    ;
    Wall wall = new Wall(new Rectangle(0,0,20,40), new Point(0,0));
    Level level = new Level(drawArea, 20, 3,6/3,wall);

    @Test
    void nextLevel() {
        level.nextLevel();
        assertEquals(19,wall.getBrickCount());
    }

    @Test
    void hasLevel() {
        assertTrue(level.hasLevel());
    }

    @Test
    void getWall() {
        assertEquals(wall, level.getWall());
    }
}