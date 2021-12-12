package brickdestroy.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


import java.awt.*;
import java.awt.geom.Point2D;

class MagicBrickTest {

    private Point2D center = new Point2D.Double(200, 200);
    Point position = new Point(0, 0);
    Dimension dimension = new Dimension(40, 10);
    MagicBrick magicBrick = new MagicBrick(new Point(position), new Dimension(dimension));
    Rectangle expectedBrick = new Rectangle(new Point(position), new Dimension(dimension));

    @Test
    void getBrick() {
        assertEquals(expectedBrick, magicBrick.getBrick());
    }


    @Test
    void getRandom() {
        int random = magicBrick.getRandom();
        try{
            assertEquals(1, random);
        }catch(Exception e){
            assertEquals(2, random);
        }
    }
}