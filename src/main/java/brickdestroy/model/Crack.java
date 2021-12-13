package brickdestroy.model;

import java.awt.*;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
import java.util.Random;

/**
 * A crack class that is responsible to make a crack on the cement brick.
 */
public class Crack{

    private static final int CRACK_SECTIONS = 3;
    private static final double JUMP_PROBABILITY = 0.7;

    public static final int LEFT = 10;
    public static final int RIGHT = 20;
    public static final int UP = 30;
    public static final int DOWN = 40;
    public static final int VERTICAL = 100;
    public static final int HORIZONTAL = 200;



    private GeneralPath crack;

    private int crackDepth;
    private int steps;
    private Brick brick;
    private Random random;


    /**
     * A crack constructor that initialises the variables in crack class.
     *
     * @param brick a brick object
     * @param crackDepth the depth of crack
     * @param steps steps of crack
     */
    public Crack(Brick brick,int crackDepth, int steps){
        this.brick = brick;
        crack = new GeneralPath();
        this.crackDepth = crackDepth;
        this.steps = steps;
        random = new Random();
    }

    /**
     * Draws crack on brick.
     *
     * @return crack path
     */
    public GeneralPath draw(){
        return crack;
    }

    /**
     * Resets the crack by clearing the crack on the brick.
     */
    public void reset(){
        crack.reset();
    }

    /**
     * Makes a crack on the brick by determining the position of the crack on the brick.
     *
     * @param point point of impact
     * @param direction direction of crack
     */
    protected void makeCrack(Point2D point, int direction){
        Rectangle bounds = brick.getBrickFace().getBounds();
        Point impact = new Point((int)point.getX(),(int)point.getY());
        Point start = new Point();
        Point end = new Point();


        switch(direction){
            case LEFT:
                start.setLocation(bounds.x + bounds.width, bounds.y);
                end.setLocation(bounds.x + bounds.width, bounds.y + bounds.height);
                Point tmp = makeRandomPoint(start,end,VERTICAL);
                makeCrack(impact,tmp);

                break;
            case RIGHT:
                start.setLocation(bounds.getLocation());
                end.setLocation(bounds.x, bounds.y + bounds.height);
                tmp = makeRandomPoint(start,end,VERTICAL);
                makeCrack(impact,tmp);

                break;
            case UP:
                start.setLocation(bounds.x, bounds.y + bounds.height);
                end.setLocation(bounds.x + bounds.width, bounds.y + bounds.height);
                tmp = makeRandomPoint(start,end,HORIZONTAL);
                makeCrack(impact,tmp);
                break;
            case DOWN:
                start.setLocation(bounds.getLocation());
                end.setLocation(bounds.x + bounds.width, bounds.y);
                tmp = makeRandomPoint(start,end,HORIZONTAL);
                makeCrack(impact,tmp);

                break;

        }
    }

    /**
     * Draws the crack the brick.
     *
     * @param start starting point of the crack
     * @param end ending point of the crack
     */
    protected void makeCrack(Point start, Point end){

        GeneralPath path = new GeneralPath();


        path.moveTo(start.x,start.y);

        double w = (end.x - start.x) / (double)steps;
        double h = (end.y - start.y) / (double)steps;

        int bound = crackDepth;
        int jump  = bound * 5;

        double x,y;

        for(int i = 1; i < steps;i++){

            x = (i * w) + start.x;
            y = (i * h) + start.y + randomInBounds(bound);

            if(inMiddle(i,CRACK_SECTIONS,steps))
                y += jumps(jump,JUMP_PROBABILITY);

            path.lineTo(x,y);

        }

        path.lineTo(end.x,end.y);
        crack.append(path,true);
    }

    /**
     * Gets a random value to change the y-position of crack on the brick.
     *
     * @param bound depth of crack
     * @return a random value to change the y-position of crack on the brick
     */
    private int randomInBounds(int bound){
        int n = (bound * 2) + 1;
        return random.nextInt(n) - bound;
    }

    /**
     * Checks the crack, whether it is in the middle.
     *
     * @param i counter value
     * @param steps steps of crack
     * @param divisions divisions of crack
     * @return in Middle status
     */
    private boolean inMiddle(int i,int steps,int divisions){
        int low = (steps / divisions);
        int up = low * (divisions - 1);

        return  (i > low) && (i < up);
    }

    /**
     * Checks the random number, whether it is greater than 0.7.
     *
     * @param bound depth of crack
     * @param probability a probability of 0.7
     * @return randomInBounds value or 0
     */
    private int jumps(int bound,double probability){

        if(random.nextDouble() > probability)
            return randomInBounds(bound);
        return  0;

    }

    /**
     * Makes random ending point of crack.
     *
     * @param from starting point
     * @param to ending point
     * @param direction direction of the point
     * @return ending point of crack
     */
    private Point makeRandomPoint(Point from,Point to, int direction){

        Point out = new Point();
        int pos;

        switch(direction){
            case HORIZONTAL:
                pos = random.nextInt(to.x - from.x) + from.x;
                out.setLocation(pos,to.y);
                break;
            case VERTICAL:
                pos = random.nextInt(to.y - from.y) + from.y;
                out.setLocation(to.x,pos);
                break;
        }
        return out;
    }

}
