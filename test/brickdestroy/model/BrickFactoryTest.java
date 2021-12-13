package brickdestroy.model;

import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class BrickFactoryTest {

    Point position = new Point(0, 0);
    Dimension dimension = new Dimension(40, 10);
    ClayBrick brick = new ClayBrick(new Point(position), new Dimension(dimension));
    BrickFactory brickFactory = new BrickFactory();

    @Test
    void makeBrick() {
        Brick clayBrick = brickFactory.makeBrick(position, dimension, 1);
        assertEquals(brick.getBrickFace(), clayBrick.getBrickFace());
    }
}