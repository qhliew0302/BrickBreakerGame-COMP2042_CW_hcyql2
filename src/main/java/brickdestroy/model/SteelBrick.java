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
import java.awt.geom.Point2D;
import java.util.Random;

/**
 * A steel brick class that inherits from the abstract brick class.
 */
public class SteelBrick extends Brick {

    private static final String NAME = "Steel Brick";
    private static final Color DEF_INNER = new Color(203, 203, 201);
    private static final Color DEF_BORDER = Color.BLACK;
    private static final int STEEL_STRENGTH = 1;
    private static final double STEEL_PROBABILITY = 0.4;

    private Random rnd;
    private Shape brickFace;

    /**
     * A Steel Brick constructor that initialises the steel brick variables.
     *
     * @param point position of steel brick
     * @param size size of the steel brick
     */
    public SteelBrick(Point point, Dimension size){
        super(NAME,point,size,DEF_BORDER,DEF_INNER,STEEL_STRENGTH);
        rnd = new Random();
        brickFace = super.getBrickFace();
    }


    /**
     * Gets the brick shape of the brick.
     *
     * @return steel brick shape
     */
    @Override
    public Shape getBrick() {
        return brickFace;
    }

    /**
     * Sets impact on the brick if the random value is less than 0.4.
     */
    public void impact(){
        if(rnd.nextDouble() < STEEL_PROBABILITY){
            super.impact();
        }
    }

    /**
     * Gets a random value.
     *
     * @return random value
     */
    public Random getRnd(){
        return rnd;
    }

}
