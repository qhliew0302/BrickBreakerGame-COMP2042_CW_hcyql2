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
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;


public class GameFrame extends JFrame {

    private static final String DEF_TITLE = "Brick Destroy";

    private GameBoard gameBoard;
    private GameBoard rankGameBoard;
    private HomeMenu homeMenu;
    private GameFrameController gameFrameController;


    public GameFrame(){
        super();

        this.setLayout(new BorderLayout());
        homeMenu = new HomeMenu(this,new Dimension(450,300));
        this.add(homeMenu,BorderLayout.CENTER);


        this.setUndecorated(true);

    }

    public void initialize(){
        this.setTitle(DEF_TITLE);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();
        this.autoLocate();
        this.setVisible(true);
        this.setResizable(false);
    }



    public void enableGameBoard(String gameType){
        GameFrameController gameFrameController = new GameFrameController(this);
        this.gameFrameController = gameFrameController;
        this.addWindowFocusListener(gameFrameController);
        this.dispose();
        this.remove(homeMenu);
        if(gameType == "Start"){
            gameBoard = new GameBoard(this, homeMenu.getGameType());
            this.add(gameBoard,BorderLayout.CENTER);
        }
        else{
            rankGameBoard = new GameBoard(this, homeMenu.getGameType());
            this.add(rankGameBoard,BorderLayout.CENTER);
        }
        this.setUndecorated(false);
        initialize();
        /*to avoid problems with graphics focus controller is added here*/
        this.addWindowFocusListener(gameFrameController);
    }


    private void autoLocate(){
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (size.width - this.getWidth()) / 2;
        int y = (size.height - this.getHeight()) / 2;
        this.setLocation(x,y);
    }

    public GameBoard getGameBoard() {
        return gameBoard;
    }

    public GameBoard getRankGameBoard(){
        return rankGameBoard;
    }

}
