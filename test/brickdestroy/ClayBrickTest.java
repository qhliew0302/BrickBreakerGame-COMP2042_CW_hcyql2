package brickdestroy;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class ClayBrickTest {
    Point position = new Point(0, 0);
    Dimension dimension = new Dimension(40, 10);
    ClayBrick claybrick = new ClayBrick(new Point(position), new Dimension(dimension));
    Rectangle expectedBrick = new Rectangle(new Point(position), new Dimension(dimension));

    @org.junit.jupiter.api.Test
    void makeBrickFace() {
        assertEquals(expectedBrick, claybrick.makeBrickFace(new Point(position), new Dimension(dimension)));
    }

    @org.junit.jupiter.api.Test
    void getBrick() {
        assertEquals(expectedBrick, claybrick.getBrick());
    }
}