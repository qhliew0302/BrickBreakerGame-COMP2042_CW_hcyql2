package brickdestroy.model;

import java.awt.*;

/**
 * A level class that is responsible to make levels of games,
 * to switch to next level and to check whether there is any more levels left.
 */
public class Level {

    private static Wall wall;
    private Brick[][] levels;
    private int level;


    /**
     * A level constructor thar initialises the level class variables.
     *
     * @param drawArea the area to draw the levels
     * @param brickCount the number of bricks
     * @param lineCount the number of lines of bricks
     * @param brickDimensionRatio the dimension ratio of brick
     * @param wall the wall object
     */
    public Level(Rectangle drawArea, int brickCount, int lineCount, double brickDimensionRatio, Wall wall){
        levels = wall.makeLevels(drawArea,brickCount,lineCount,brickDimensionRatio);
        level = 0;
        this.wall = wall;

    }

    /**
     * Makes a level that has only one type of brick.
     *
     * @param drawArea the area to draw the levels
     * @param brickCnt the number of bricks
     * @param brickSizeRatio the size ratio of the brick
     * @param type the type of brick
     * @return the brick arrangement
     */
    public static Brick[] makeSingleTypeLevel(Rectangle drawArea, int brickCnt, int lineCnt, double brickSizeRatio, int type){
        /*
          if brickCount is not divisible by line count,brickCount is adjusted to the biggest
          multiple of lineCount smaller than brickCount
         */

        BrickFactory brickFactory = new BrickFactory();
        brickCnt -= brickCnt % lineCnt;

        int brickOnLine = brickCnt / lineCnt;

        double brickLen = drawArea.getWidth() / brickOnLine;
        double brickHgt = brickLen / brickSizeRatio;

        brickCnt += lineCnt / 2;

        Brick[] tmp  = new Brick[brickCnt];

        Dimension brickSize = new Dimension((int) brickLen,(int) brickHgt);
        Point p = new Point();

        int i;
        for(i = 0; i < tmp.length; i++){
            int line = i / brickOnLine;
            if(line == lineCnt)
                break;
            double x = (i % brickOnLine) * brickLen;
            x =(line % 2 == 0) ? x : (x - (brickLen / 2));
            double y = (line) * brickHgt;
            p.setLocation(x,y);
            tmp[i] = brickFactory.makeBrick(p,brickSize,type);
        }

        for(double y = brickHgt;i < tmp.length;i++, y += 2*brickHgt){
            double x = (brickOnLine * brickLen) - (brickLen / 2);
            p.setLocation(x,y);
            tmp[i] = brickFactory.makeBrick(p,brickSize,type);
        }
        return tmp;

    }

    /**
     * Makes a level that has two types of bricks.
     *
     * @param drawArea the area to draw the levels
     * @param brickCnt the number of bricks
     * @param lineCnt the number of lines of bricks
     * @param brickSizeRatio the size ratio of brick
     * @param typeA first type of brick
     * @param typeB second type of brick
     * @return brick arrangement
     */
    public static Brick[] makeChessboardLevel(Rectangle drawArea, int brickCnt, int lineCnt, double brickSizeRatio, int typeA, int typeB){
        /*
          if brickCount is not divisible by line count,brickCount is adjusted to the biggest
          multiple of lineCount smaller than brickCount
         */
        BrickFactory brickFactory = new BrickFactory();
        brickCnt -= brickCnt % lineCnt;

        int brickOnLine = brickCnt / lineCnt;

        int centerLeft = brickOnLine / 2 - 1;
        int centerRight = brickOnLine / 2 + 1;

        double brickLen = drawArea.getWidth() / brickOnLine;
        double brickHgt = brickLen / brickSizeRatio;

        brickCnt += lineCnt / 2;

        Brick[] tmp  = new Brick[brickCnt];

        Dimension brickSize = new Dimension((int) brickLen,(int) brickHgt);
        Point p = new Point();

        int i;
        for(i = 0; i < tmp.length; i++){
            int line = i / brickOnLine;
            if(line == lineCnt)
                break;
            int posX = i % brickOnLine;
            double x = posX * brickLen;
            x =(line % 2 == 0) ? x : (x - (brickLen / 2));
            double y = (line) * brickHgt;
            p.setLocation(x,y);

            boolean b = ((line % 2 == 0 && i % 2 == 0) || (line % 2 != 0 && posX > centerLeft && posX <= centerRight));
            tmp[i] = b ?  brickFactory.makeBrick(p,brickSize,typeA) : brickFactory.makeBrick(p,brickSize,typeB);
        }

        for(double y = brickHgt;i < tmp.length;i++, y += 2*brickHgt){
            double x = (brickOnLine * brickLen) - (brickLen / 2);
            p.setLocation(x,y);
            tmp[i] = brickFactory.makeBrick(p,brickSize,typeA);
        }
        return tmp;
    }


    /**
     * Switches to next level.
     */
    public void nextLevel(){
        wall.setBricks(levels[level++]);
        wall.setBrickCount(wall.getBricks().length);
    }

    /**
     * Checks whether there is any more levels.
     *
     * @return false if there is no more level and vice versa
     */
    public boolean hasLevel(){
        return level < levels.length;
    }

    /**
     * Gets the wall object.
     *
     * @return wall object
     */
    public static Wall getWall(){
        return wall;
    }

}
