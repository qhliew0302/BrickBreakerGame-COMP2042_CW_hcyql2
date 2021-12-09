package brickdestroy;

import java.awt.*;

public class BrickFactory {

    private static final int CLAY = 1;
    private static final int STEEL = 2;
    private static final int CEMENT = 3;

    public Brick makeBrick(Point point, Dimension size, int brickType){
        if(brickType < 1 || brickType > 3){
            return null;
        }
        if(brickType == CLAY){
            return new ClayBrick(point,size);
        }
        else if (brickType == STEEL){
            return new SteelBrick(point, size);
        }
        else if (brickType == CEMENT){
            return new CementBrick(point, size);
        }
        return null;
    }
}
