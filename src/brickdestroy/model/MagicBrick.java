package brickdestroy.model;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.Random;

public class MagicBrick extends Brick{

    private static final String NAME = "Magic Brick";
    private static final Color DEF_INNER = new Color(255, 255, 34).darker();
    private static final Color DEF_BORDER = DEF_INNER.darker().darker();
    private static final int MAGIC_STRENGTH = 1;
    private Random random = new Random();


    public MagicBrick(Point point, Dimension size){
        super(NAME,point,size,DEF_BORDER,DEF_INNER,MAGIC_STRENGTH);
    }

    @Override
    public Shape getBrick() {
        return getBrickFace();
    }

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

    public void slowDown(){
        Level.getWall().setBallXSpeed(Level.getWall().getBall().getSpeedX() - 1);
        Level.getWall().setBallYSpeed(Level.getWall().getBall().getSpeedX() - 1);
    }

    public void speedUp(){
        Level.getWall().setBallXSpeed(Level.getWall().getBall().getSpeedX() + 1);
        Level.getWall().setBallYSpeed(Level.getWall().getBall().getSpeedX() + 1);
    }

    public int getRandom(){
        int max = 2;
        int min = 1;
        return random.nextInt(max - min) + min;
    }
}
