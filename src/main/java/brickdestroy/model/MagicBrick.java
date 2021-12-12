package brickdestroy.model;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.Random;

/**
 * A magic brick class that inherits from the abstract brick class.
 */
public class MagicBrick extends Brick{

    private static final String NAME = "Magic Brick";
    private static final Color DEF_INNER = new Color(255, 255, 34).darker();
    private static final Color DEF_BORDER = DEF_INNER.darker().darker();
    private static final int MAGIC_STRENGTH = 1;
    private Random random = new Random();


    /**
     * A magic brick constructor that initialises the magic brick variables.
     * @param point position of the magic brick
     * @param size size of the magic brick
     */
    public MagicBrick(Point point, Dimension size){
        super(NAME,point,size,DEF_BORDER,DEF_INNER,MAGIC_STRENGTH);
    }


    /**
     * Gets the brick shape of magic brick.
     *
     * @return magic brick shape
     */
    @Override
    public Shape getBrick() {
        return getBrickFace();
    }

    /**
     * If the brick is broken, no impact is set,
     * else make an impact and change the speed of the ball.
     *
     * @param point position of the brick
     * @param dir direction of impact of brick
     * @return
     */
    @Override
    public boolean setImpact(Point2D point, int dir){
        if(super.isBroken())
            return false;
        else {
            impact();
            switch(getRandom()) {
                case 1:
                    slowDown();
                    break;
                case 2:
                    speedUp();
                    break;
            }
        }
        return super.isBroken();
    }

    /**
     * Decreases the ball speed by 1.
     */
    public void slowDown(){
        if((Level.getWall().getBall().getSpeedX() > -4) && (Level.getWall().getBall().getSpeedX() != 1))
            Level.getWall().setBallXSpeed(Level.getWall().getBall().getSpeedX() - 1);
        if(Level.getWall().getBall().getSpeedY() > -4 && (Level.getWall().getBall().getSpeedY() != 1))
            Level.getWall().setBallYSpeed(Level.getWall().getBall().getSpeedY() - 1);
    }

    /**
     * Increases the ball speed by 1.
     */
    public void speedUp(){
        if((Level.getWall().getBall().getSpeedX() < 4) && (Level.getWall().getBall().getSpeedX() != -1))
            Level.getWall().setBallXSpeed(Level.getWall().getBall().getSpeedX() + 1);
        if(Level.getWall().getBall().getSpeedY() < 4 && (Level.getWall().getBall().getSpeedY() != -1))
            Level.getWall().setBallYSpeed(Level.getWall().getBall().getSpeedY() + 1);
    }

    /**
     * Get a random integer between 1 and 2.
     *
     * @return random integer (1 or 2)
     */
    public int getRandom(){
        int max = 2;
        int min = 1;
        return random.nextInt(max - min) + min;
    }
}
