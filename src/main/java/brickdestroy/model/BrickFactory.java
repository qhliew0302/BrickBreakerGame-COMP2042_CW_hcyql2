package brickdestroy.model;

import java.awt.*;

public class BrickFactory {

    private static final int CLAY = 1;
    private static final int STEEL = 2;
    private static final int CEMENT = 3;
    private static final int MAGIC = 4;

    public Brick makeBrick(Point point, Dimension size, int brickType){
        if(brickType < 1 || brickType > 4){
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
        else if (brickType == MAGIC){
            return new MagicBrick(point, size);
        }
        return null;
    }
}
