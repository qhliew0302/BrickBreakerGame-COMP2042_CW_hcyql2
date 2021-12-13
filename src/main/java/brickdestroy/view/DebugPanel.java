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
package brickdestroy.view;

import javax.swing.*;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Hashtable;
import brickdestroy.model.*;

/**
 * A debug panel class that draws the debug panel on the screen.
 */
public class DebugPanel extends JPanel {

    private static final Color DEF_BKG = Color.WHITE;


    private JButton skipLevel;
    private JButton resetBalls;

    private JSlider ballXSpeed;
    private JSlider ballYSpeed;

    private Level level;
    private Wall wall;

    public DebugPanel(Wall wall, Level level){

        this.wall = wall;
        this.level = level;

        initialize();

        skipLevel = makeButton("Skip Level",e -> level.nextLevel());
        resetBalls = makeButton("Reset Balls",e -> wall.resetBallCount());

        ballXSpeed = makeSlider(-4,4,e -> wall.setBallXSpeed(ballXSpeed.getValue()), 1);
        ballYSpeed = makeSlider(-4,4,e -> wall.setBallYSpeed(ballYSpeed.getValue()), 2);

        this.add(skipLevel);
        this.add(resetBalls);

        this.add(ballXSpeed);
        this.add(ballYSpeed);

    }

    private void initialize(){
        this.setBackground(DEF_BKG);
        this.setLayout(new GridLayout(2,2));
    }

    /**
     * Makes button on the Debug Panel。
     *
     * @param title text on the button
     * @param e action listener
     * @return
     */
    private JButton makeButton(String title, ActionListener e){
        JButton out = new JButton(title);
        out.addActionListener(e);
        return  out;
    }

    /**
     * Makes slider on the Debug Panel to manipulate the speed of ball.
     *
     * @param min minimum value on the slider
     * @param max maximum value on the slider
     * @param e change listener
     * @param type the type of slider (speed in x or y-direction)
     * @return a Jslider
     */
    private JSlider makeSlider(int min, int max, ChangeListener e, int type){
        JSlider slider = new JSlider(min,max);
        slider.setMajorTickSpacing(1);
        slider.setSnapToTicks(true);
        slider.setPaintTicks(true);
        Hashtable<Integer, JLabel> labels = new Hashtable<>();
        if(type == 1){
            labels.put(0, new JLabel("Ball's SpeedX"));
        }
        else{
            labels.put(0, new JLabel("Ball's SpeedY"));
        }
        slider.setLabelTable(labels);
        slider.setPaintLabels(true);
        slider.addChangeListener(e);
        return slider;
    }

    /**
     * Sets the speed of ball.
     *
     * @param x the speed of ball in x-direction
     * @param y the speed of ball in y-direction
     */
    public void setValues(int x,int y){
        ballXSpeed.setValue(x);
        ballYSpeed.setValue(y);
    }

}
