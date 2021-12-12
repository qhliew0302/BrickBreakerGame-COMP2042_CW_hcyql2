package brickdestroy.model;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.RectangularShape;

/**
 * An abstract Ball class that is responsible to create
 * the ball face and set the position, speed,
 * colour of the ball.
 */

abstract public class Ball {

    /**
     * ball shape
     */
    private Shape ballFace;

    /**
     * center of the ball
     */
    private Point2D center;

    // encapsulation for variables up, down, left and right
    /**
     * top point of the ball
     */
    private Point2D up;
    /**
     * bottom point of the ball
     */
    private Point2D down;
    /**
     * left point of the ball
     */
    private Point2D left;
    /**
     * right point of the ball
     */
    private Point2D right;

    /**
     * border color of the ball
     */
    private Color border;
    /**
     * inner color of the ball
     */
    private Color inner;

    /**
     * speed of ball in x-direction
     */
    private int speedX;
    /**
     * speed of ball in y-direction
     */
    private int speedY;

    /**
     * A ball constructor that initialises the ball variables.
     *
     * @param center center of the ball
     * @param radiusA diameter of the ball (width of rectangle)
     * @param radiusB diameter of the ball (height of rectangle)
     * @param inner inner color of the ball
     * @param border border color of the ball
     */
    public Ball(Point2D center,int radiusA,int radiusB,Color inner,Color border){
        this.center = center;

        setUp(new Point2D.Double());
        setDown(new Point2D.Double());
        setLeft(new Point2D.Double());
        setRight(new Point2D.Double());

        // set the location of the ball (circumference: up, down, left, right)
        getUp().setLocation(center.getX(),center.getY()-(radiusB / 2));
        getDown().setLocation(center.getX(),center.getY()+(radiusB / 2));
        getLeft().setLocation(center.getX()-(radiusA /2),center.getY());
        getRight().setLocation(center.getX()+(radiusA /2),center.getY());


        ballFace = makeBall(center,radiusA,radiusB);
        this.border = border;
        this.inner  = inner;
        speedX = 0;
        speedY = 0;
    }

    /**
     * Makes ball shape.
     *
     * @param center center of the ball
     * @param radiusA diameter of the ball (width of rectangle)
     * @param radiusB diameter of the ball (height of rectangle)
     * @return ball shape
     */

    protected abstract Shape makeBall(Point2D center,int radiusA,int radiusB);


    /**
     * Moves the ball.
     */
    public void move(){

        // create a temporary ballFace which converts the initial ballFace to a rectangular shape
        RectangularShape tempBallFace = (RectangularShape) ballFace;
        // the center of the ball will move according to the speed change
        center.setLocation((center.getX() + speedX),(center.getY() + speedY));
        double w = tempBallFace.getWidth();
        double h = tempBallFace.getHeight();

        // set a frame for temporary ballFace using the center of ball
        tempBallFace.setFrame((center.getX() -(w / 2)),(center.getY() - (h / 2)),w,h);
        setPoints(w,h);


        ballFace = tempBallFace;
    }

    /**
     * Sets speed of the ball.
     *
     * @param x speed of ball in x-direction
     * @param y speed of ball in y-direction
     */
    public void setSpeed(int x,int y){
        speedX = x;
        speedY = y;
    }


    /**
     * Sets the speed of the ball in x-direction.
     *
     * @param s speed of ball in x-direction
     */
    public void setXSpeed(int s){
        speedX = s;
    }

    /**
     * Sets the speed of the ball in y-direction.
     *
     * @param s speed of ball in y-direction
     */
    public void setYSpeed(int s){
        speedY = s;
    }

    /**
     * Reverses the speed of ball in x-direction.
     */
    public void reverseX(){
        speedX *= -1;
    }


    /**
     * Reverses the speed of ball in y-direction.
     */
    public void reverseY(){
        speedY *= -1;
    }

    /**
     * Gets the border color of the ball.
     *
     * @return border color of the ball
     */
    public Color getBorderColor(){
        return border;
    }

    /**
     * Gets the inner color of the ball.
     * @return inner color of the ball
     */
    public Color getInnerColor(){
        return inner;
    }

    /**
     * Gets the center position of the ball.
     *
     * @return center of the ball
     */
    public Point2D getPosition(){
        return center;
    }

    /**
     * Gets the ball shape.
     *
     * @return ball shape
     */
    public Shape getBallFace(){
        return ballFace;
    }

    /**
     * Moves the ball to its starting position.
     *
     * @param p starting position of the ball
     */
    public void moveTo(Point p){
        center.setLocation(p);

        RectangularShape tmp = (RectangularShape) ballFace;
        double w = tmp.getWidth();
        double h = tmp.getHeight();

        tmp.setFrame((center.getX() -(w / 2)),(center.getY() - (h / 2)),w,h);
        ballFace = tmp;
    }

    /**
     * Sets the top, bottom, left and right point of the ball.
     *
     * @param width width of the rectangle (radius of ball)
     * @param height height of the rectangle (radius of ball)
     */
    private void setPoints(double width,double height){
        getUp().setLocation(center.getX(),center.getY()-(height / 2));
        getDown().setLocation(center.getX(),center.getY()+(height / 2));

        getLeft().setLocation(center.getX()-(width / 2),center.getY());
        getRight().setLocation(center.getX()+(width / 2),center.getY());
    }

    /**
     * Gets the speed of ball in x-direction.
     *
     * @return speed of ball in x-direction
     */
    public int getSpeedX(){
        return speedX;
    }

    /**
     * Gets the speed of ball in y-direction.
     *
     * @return speed of ball in y-direction
     */
    public int getSpeedY(){
        return speedY;
    }

    /**
     * Gets the top point of the ball.
     *
     * @return top point of the ball
     */
    public Point2D getUp() {
        return up;
    }

    /**
     * Sets the top point of the ball.
     *
     * @param up top point of the ball
     */
    public void setUp(Point2D up) {
        this.up = up;
    }

    /**
     * Gets the bottom point of the ball.
     *
     * @return bottom point of the ball
     */
    public Point2D getDown() {
        return down;
    }

    /**
     * Sets the bottom point of the ball.
     *
     * @param down bottom point of the ball
     */
    public void setDown(Point2D down) {
        this.down = down;
    }

    /**
     * Gets the left point of the ball.
     *
     * @return left point of the ball
     */
    public Point2D getLeft() {
        return left;
    }

    /**
     * Sets the left point of the ball.
     *
     * @param left left point of the ball
     */
    public void setLeft(Point2D left) {
        this.left = left;
    }

    /**
     * Gets the right point of the ball.
     *
     * @return right point of the ball
     */
    public Point2D getRight() {
        return right;
    }

    /**
     * Sets the right point of the ball.
     *
     * @param right right point of the ball
     */
    public void setRight(Point2D right) {
        this.right = right;
    }
}
