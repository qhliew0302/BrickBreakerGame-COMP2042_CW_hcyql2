package brickdestroy.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import brickdestroy.view.*;

/**
 * A GameInfo Controller class that controls the gameInfo class.
 */
public class GameInfoController implements ActionListener {

    private GameInfo gameInfo;

    public GameInfoController(GameInfo gameInfo){

        this.gameInfo = gameInfo;

    }

    /**
     * If the back button is clicked, it returns to the homeMenu.
     *
     * @param event click back button
     */
    @Override
    public void actionPerformed(ActionEvent event) {
        if(event.getSource() == gameInfo.getBackButton()){
            gameInfo.dispose();
            new GameFrame();
        }
    }
}
