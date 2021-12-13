package brickdestroy.model;

import java.awt.*;
import java.awt.Point;
import java.awt.geom.Point2D;

/**
 * An abstract brick class that is responsible to
 * make brick face, to check the brick broken status, to repair the brick,
 * to find and set impact and to set the color of the brick.
 */
abstract public class Brick  {

    public static final int UP_IMPACT = 100;
    public static final int DOWN_IMPACT = 200;
    public static final int LEFT_IMPACT = 300;
    public static final int RIGHT_IMPACT = 400;


    private String name;
    private Shape brickFace;

    private Color border;
    private Color inner;

    private int fullStrength;
    private int strength;

    private boolean broken;


    /**
     * A Brick constructor that initialises the brick variables.
     *
     * @param name name of brick
     * @param pos position of brick
     * @param size size of brick
     * @param border border color of the brick
     * @param inner inner color of the brick
     * @param strength strength of the brick
     */
    public Brick(String name, Point pos,Dimension size,Color border,Color inner,int strength){
        broken = false;
        this.name = name;
        setBrickFace(makeBrickFace(pos,size));
        this.border = border;
        this.inner = inner;
        this.fullStrength = this.strength = strength;

    }

    /**
     * Gets the brick shape.
     *
     * @return brick shape
     */
    public Shape getBrickFace() {
        return brickFace;
    }

    /**
     * Sets the brick shape.
     *
     * @param brickFace brick shape
     */
    public void setBrickFace(Shape brickFace) {
        this.brickFace = brickFace;
    }

    /**
     * Makes a rectangular brick.
     *
     * @param pos position of the brick
     * @param size size of the brick (width and height)
     * @return
     */
    public Shape makeBrickFace(Point pos,Dimension size){
        return new Rectangle(pos,size);
    }

    /**
     * Sets the impact only if the brick is not broken.
     *
     * @param point position of the brick
     * @param dir direction of impact of brick
     * @return brick broken status
     */
    public boolean setImpact(Point2D point , int dir){
        if(broken)
            return false;
        impact();
        return  broken;
    }

    /**
     * Gets the brick shape.
     *
     * @return brick shape
     */
    public abstract Shape getBrick();


    /**
     * Gets the border color of the brick.
     *
     * @return border color of the brick
     */
    public Color getBorderColor(){
        return  border;
    }

    /**
     * Gets the inner color of the brick.
     *
     * @return inner color of the brick.
     */
    public Color getInnerColor(){
        return inner;
    }

    /**
     * Finds the direction of impact on the brick.
     *
     * @param b ball
     * @return direction of impact on the brick
     */
    public final int findImpact(Ball b){
        if(broken)
            return 0;
        int out  = 0;
        if(getBrickFace().contains(b.getRight()))
            out = LEFT_IMPACT;
        else if(getBrickFace().contains(b.getLeft()))
            out = RIGHT_IMPACT;
        else if(getBrickFace().contains(b.getUp()))
            out = DOWN_IMPACT;
        else if(getBrickFace().contains(b.getDown()))
            out = UP_IMPACT;
        return out;
    }

    /**
     * Checks the status of the brick.
     *
     * @return brick broken status
     */
    public final boolean isBroken(){
        return broken;
    }

    /**
     * Sets the brick broken status to false and resets the brick strength to full.
     */
    public void repair() {
        broken = false;
        strength = fullStrength;
    }

    /**
     * After impact, strength of the brick decreases by 1,
     * if strength decreases to 0, then the brick is broken.
     */
    public void impact(){
        strength--;
        broken = (strength == 0);
    }


}





