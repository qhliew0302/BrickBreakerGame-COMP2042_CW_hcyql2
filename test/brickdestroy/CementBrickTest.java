package brickdestroy;

import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class CementBrickTest {

    Point position = new Point(0, 0);
    Dimension dimension = new Dimension(40, 10);
    CementBrick cementbrick = new CementBrick(new Point(position), new Dimension(dimension));
    Rectangle expectedBrick = new Rectangle(new Point(position), new Dimension(dimension));

    @Test
    void makeBrickFace() {
        assertEquals(expectedBrick, cementbrick.makeBrickFace(new Point(position), new Dimension(dimension)));
    }

    @Test
    void setImpact() {
        assertEquals(cementbrick.isBroken(),cementbrick.setImpact(position, 10));
    }

    @Test
    void getBrick() {
        assertEquals(expectedBrick, cementbrick.getBrick());
    }

    @Test
    void repair() {
        cementbrick.repair();
        assertFalse(cementbrick.isBroken());
    }
}