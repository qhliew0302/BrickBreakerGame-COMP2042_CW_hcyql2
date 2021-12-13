/*
 *  Brick Destroy - A simple Arcade video game
 *   Copyright (C) 2017  Filippo Ranza
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package brickdestroy.model;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

/**
 * A rubber ball class that inherits from the abstract ball class.
 */
public class RubberBall extends Ball {


    private static final int DEF_RADIUS = 10;
    private static final Color DEF_INNER_COLOR = new Color(255, 219, 88);
    private static final Color DEF_BORDER_COLOR = DEF_INNER_COLOR.darker().darker();


    /**
     * A rubber ball constructor that initialises the rubber ball variables.
     * @param center
     */
    public RubberBall(Point2D center){
        super(center,DEF_RADIUS,DEF_RADIUS,DEF_INNER_COLOR,DEF_BORDER_COLOR); // call the constructor of parent class Ball
    }


    /**
     * Makes a circular rubber ball shape.
     *
     * @param center center of the ball
     * @param radiusA diameter of the ball (width of rectangle)
     * @param radiusB diameter of the ball (height of rectangle)
     * @return
     */
    @Override
    public Shape makeBall(Point2D center, int radiusA, int radiusB) {

        double x = center.getX() - (radiusA / 2); // get the X-coordinate of the upper left corner of framing rectangle
        double y = center.getY() - (radiusB / 2); // get the X-coordinate of the upper left corner of framing rectangle

        return new Ellipse2D.Double(x,y,radiusA,radiusB);
    }
}
