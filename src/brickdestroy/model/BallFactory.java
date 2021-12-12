package brickdestroy.model;

import java.awt.geom.Point2D;

public class BallFactory {
    public Ball makeBall(String ballType, Point2D center){
        if(ballType == null){
            return null;
        }
        if(ballType.equalsIgnoreCase("rubberBall")){
            return new RubberBall(center);
        }
        return null;
    }
}
