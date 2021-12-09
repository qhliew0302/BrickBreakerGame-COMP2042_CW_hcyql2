package brickdestroy;

import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.geom.Point2D;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    private Point2D center = new Point2D.Double(200, 200);
    private Point point = new Point(20, 20);

    Ball rubberBall = new RubberBall(center);
    Rectangle playerFace = new Rectangle(19,20,2,2);
    Player player = new Player(point, 2, 2, new Rectangle(0, 0, 200, 300));

    @Test
    void impact() {
        assertFalse(player.impact(rubberBall));
    }

    @Test
    void move() {
        player.move();
        assertEquals(center, rubberBall.getPosition());
    }

    @Test
    void moveLeft() {
        player.moveLeft();
        assertEquals(-5, player.getMoveAmount());
    }

    @Test
    void movRight() {
        player.moveRight();
        assertEquals(5, player.getMoveAmount());
    }

    @Test
    void stop() {
        player.stop();
        assertEquals(0, player.getMoveAmount());
    }

    @Test
    void getPlayerFace() {
        assertEquals(playerFace, player.getPlayerFace());
    }

}