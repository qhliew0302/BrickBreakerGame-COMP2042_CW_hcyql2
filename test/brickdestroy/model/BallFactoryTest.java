package brickdestroy.model;

import org.junit.jupiter.api.Test;

import java.awt.geom.Point2D;

import static org.junit.jupiter.api.Assertions.*;

class BallFactoryTest {

    private Point2D center = new Point2D.Double(200, 200);

    Ball ball = new RubberBall(center);
    BallFactory ballFactory = new BallFactory();

    @Test
    void makeBall() {
        Ball rubberBall = ballFactory.makeBall("RubberBall", center);
        assertEquals(ball.getBallFace(), rubberBall.getBallFace());
    }
}