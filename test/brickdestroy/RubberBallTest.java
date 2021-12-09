package brickdestroy;

import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.RectangularShape;

import static org.junit.jupiter.api.Assertions.*;

class RubberBallTest {
    private final int DEF_RADIUS = 10;
    private final Color DEF_INNER_COLOR = new Color(255, 219, 88);
    private final Color DEF_BORDER_COLOR = DEF_INNER_COLOR.darker().darker();
    private Point2D center = new Point2D.Double(200, 200);

    RubberBall rubberBall = new RubberBall(center);

    @Test
    void move() {
        Shape rubberBallFace = rubberBall.makeBall(center, DEF_RADIUS, DEF_RADIUS);
        RectangularShape ball = (RectangularShape) rubberBallFace;
        assertEquals(ball, rubberBallFace);
    }

    @Test
    void setSpeed() {
        rubberBall.setSpeed(2, 2);
        assertEquals(2, rubberBall.getSpeedX());
        assertEquals(2, rubberBall.getSpeedY());
    }

    @Test
    void setXSpeed() {
        rubberBall.setXSpeed(10);
        assertEquals(10, rubberBall.getSpeedX());
    }

    @Test
    void setYSpeed() {
        rubberBall.setYSpeed(20);
        assertEquals(20, rubberBall.getSpeedY());
    }

    @Test
    void reverseX() {
        rubberBall.setXSpeed(20);
        rubberBall.reverseX();
        assertEquals(-20, rubberBall.getSpeedX());
    }

    @Test
    void reverseY() {
        rubberBall.setYSpeed(100);
        rubberBall.reverseY();
        assertEquals(-100, rubberBall.getSpeedY());
    }

    @Test
    void getBorderColor() {
        assertEquals(DEF_BORDER_COLOR, rubberBall.getBorderColor());
    }

    @Test
    void getInnerColor() {
        assertEquals(DEF_BORDER_COLOR, rubberBall.getBorderColor());
    }

    @Test
    void getPosition() {
        assertEquals(center, rubberBall.getPosition());
    }

    @Test
    void getBallFace() {
        Shape rubberBallFace = rubberBall.makeBall(center, DEF_RADIUS, DEF_RADIUS);
        assertEquals(rubberBallFace, rubberBall.getBallFace());
    }

    @Test
    void moveTo() {
        Shape rubberBallFace = rubberBall.makeBall(center, DEF_RADIUS, DEF_RADIUS);
        RectangularShape ball = (RectangularShape) rubberBallFace;
        assertEquals(ball, rubberBallFace);
    }

    @Test
    void getSpeedX() {
        rubberBall.setXSpeed(100);
        assertEquals(100, rubberBall.getSpeedX());
    }

    @Test
    void getSpeedY() {
        rubberBall.setYSpeed(200);
        assertEquals(200, rubberBall.getSpeedY());
    }

    @Test
    void makeBall() {
        double x = center.getX() - (DEF_RADIUS/2);
        double y = center.getY() - (DEF_RADIUS/2);
        Ellipse2D expectedBall = new Ellipse2D.Double(x, y, DEF_RADIUS, DEF_RADIUS);
        assertEquals(expectedBall, rubberBall.makeBall(center, DEF_RADIUS, DEF_RADIUS));

    }
}