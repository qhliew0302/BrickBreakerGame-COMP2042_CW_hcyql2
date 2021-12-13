package brickdestroy.model;

import java.awt.geom.Point2D;

/**
 * A ball factory that return a ball object based on the ball type.
 */
public class BallFactory {

    /**
     * Returns a ball object based on the ball type.
     *
     * @param ballType type of ball
     * @param center center of ball
     * @return ball object
     */
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
