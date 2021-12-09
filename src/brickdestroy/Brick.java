package brickdestroy;

import java.awt.*;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.Random;

/**
 * Created by filippo on 04/09/16.
 * Refactored by Qian Hui on 2/12/2021.
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


    public Brick(String name, Point pos,Dimension size,Color border,Color inner,int strength){
        broken = false;
        this.name = name;
        setBrickFace(makeBrickFace(pos,size));
        this.border = border;
        this.inner = inner;
        this.fullStrength = this.strength = strength;

    }

    public Shape getBrickFace() {
        return brickFace;
    }

    public void setBrickFace(Shape brickFace) {
        this.brickFace = brickFace;
    }

    // make a rectangular brick
    public Shape makeBrickFace(Point pos,Dimension size){
        return new Rectangle(pos,size);
    }

    // impact will be only set if the brick is not broken
    public  boolean setImpact(Point2D point , int dir){
        if(broken)
            return false;
        impact();
        return  broken;
    }

    public abstract Shape getBrick();



    public Color getBorderColor(){
        return  border;
    }

    public Color getInnerColor(){
        return inner;
    }


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

    public final boolean isBroken(){
        return broken;
    }

    // repair the brick by resetting the brick to not broken status, and resetting the brick's strength
    public void repair() {
        broken = false;
        strength = fullStrength;
    }

    // after impact, strength decrease by 1, if strength decreases to 0, then broken is true
    public void impact(){
        strength--;
        broken = (strength == 0);
    }


}





