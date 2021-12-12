package brickdestroy.model;

import java.awt.*;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;

/**
 * A cement brick class that inherits abstract brick class.
 */
public class CementBrick extends Brick {


    private static final String NAME = "Cement Brick";
    private static final Color DEF_INNER = new Color(147, 147, 147);
    private static final Color DEF_BORDER = new Color(217, 199, 175);
    private static final int CEMENT_STRENGTH = 2;

    public static final int DEF_CRACK_DEPTH = 1;
    public static final int DEF_STEPS = 35;

    private Crack crack;
    private Shape brickFace;


    /**
     * A cement brick constructor that initialises the cement brick variables.
     *
     * @param point position of cement brick
     * @param size size of cement brick
     */
    public CementBrick(Point point, Dimension size){
        super(NAME,point,size,DEF_BORDER,DEF_INNER,CEMENT_STRENGTH);
        crack = new Crack(this,DEF_CRACK_DEPTH,DEF_STEPS);
        brickFace = super.getBrickFace();
    }

    /**
     * if the brick is broken, no impact is set
     * else make an impact and if the brick is not broken, then make a crack
     *
     * @param point position of the brick
     * @param dir direction of impact of brick
     * @return cement brick status
     */

    @Override
    public boolean setImpact(Point2D point, int dir) {
        if(super.isBroken())
            return false;
        super.impact();
        if(!super.isBroken()){
            crack.makeCrack(point,dir);
            updateBrick();
            return false;
        }
        return true;
    }


    /**
     * Gets the cement brick shape.
     *
     * @return cement brick shape
     */
    @Override
    public Shape getBrick() {
        return brickFace;
    }

    /**
     * Updates brick if the brick is not broken and draw a crack on it.
     */
    private void updateBrick(){
        if(!super.isBroken()){
            GeneralPath gp = crack.draw();
            gp.append(super.getBrickFace(),false);
            brickFace = gp;
        }
    }

    /**
     * Repairs the cement brick by removing the crack.
     */
    public void repair(){
        super.repair();
        crack.reset();
        brickFace = super.getBrickFace();
    }
}
