package brickdestroy.model;

import java.awt.*;
import java.awt.Point;


/**
 * A clay brick class that inherits from the abstract brick class.
 */
public class ClayBrick extends Brick {

    private static final String NAME = "Clay Brick";
    private static final Color DEF_INNER = new Color(178, 34, 34).darker();
    private static final Color DEF_BORDER = Color.GRAY;
    private static final int CLAY_STRENGTH = 1;

    /**
     * A clay brick constructor that initialises the clay brick variables.
     * @param point position of the clay brick
     * @param size size of the brick
     */
    public ClayBrick(Point point, Dimension size){
        super(NAME,point,size,DEF_BORDER,DEF_INNER,CLAY_STRENGTH);
    }


    /**
     * Gets the brick shape.
     *
     * @return clay brick shape
     */
    @Override
    public Shape getBrick() {
        return super.getBrickFace();
    }


}
