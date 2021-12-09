package brickdestroy;

import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;

class SteelBrickTest {

    Point position = new Point(0, 0);
    Dimension dimension = new Dimension(40, 10);
    SteelBrick steelbrick = new SteelBrick(new Point(position), new Dimension(dimension));
    Rectangle expectedBrick = new Rectangle(new Point(position), new Dimension(dimension));
    private static final double STEEL_PROBABILITY = 0.4;


    @Test
    void makeBrickFace() {
        assertEquals(expectedBrick, steelbrick.makeBrickFace(new Point(position), new Dimension(dimension)));
    }

    @Test
    void getBrick() {
        assertEquals(expectedBrick, steelbrick.getBrick());
    }

    @Test
    void setImpact() {
        steelbrick.setImpact(position, 10);
        if (steelbrick.isBroken())
            assertFalse(steelbrick.setImpact(position, 10));
    }

    @Test
    void impact() {
        if (steelbrick.getRnd().nextDouble() < STEEL_PROBABILITY){
            steelbrick.impact();
            assertTrue(steelbrick.isBroken());
        }
        else{
            assertFalse(steelbrick.isBroken());
        }
    }
}