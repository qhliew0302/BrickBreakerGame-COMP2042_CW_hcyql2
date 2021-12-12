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
package brickdestroy;

import javax.swing.*;
import java.awt.*;


public class DebugConsole extends JDialog{

    private static final String TITLE = "Debug Console";


    private JFrame owner;
    private DebugPanel debugPanel;
    private GameBoard gameBoard;
    private Wall wall;
    private Level level;
    private DebugConsoleController debugConsoleController;


    public DebugConsole(JFrame owner,Wall wall, Level level, GameBoard gameBoard){

        debugConsoleController = new DebugConsoleController(this);
        this.wall = wall;
        this.owner = owner;
        this.gameBoard = gameBoard;
        this.level = level;
        initialize();

        debugPanel = new DebugPanel(wall, level);
        this.add(debugPanel,BorderLayout.CENTER);

        this.pack();
    }

    private void initialize(){
        this.setModal(true);
        this.setTitle(TITLE);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.addWindowListener(debugConsoleController);
        this.setFocusable(true);
    }

    // the location of the debug console is set to be at the centre of the frame
    public void setLocation(){
        int x = ((owner.getWidth() - this.getWidth()) / 2) + owner.getX();
        int y = ((owner.getHeight() - this.getHeight()) / 2) + owner.getY();
        this.setLocation(x,y);
    }

    public GameBoard getGameBoard(){
        return gameBoard;
    }

    public Wall getWall(){
        return wall;
    }

    public DebugPanel getDebugPanel(){
        return debugPanel;
    }

}
