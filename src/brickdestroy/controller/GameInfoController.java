package brickdestroy.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import brickdestroy.view.*;

public class GameInfoController implements ActionListener {

    private GameInfo gameInfo;

    public GameInfoController(GameInfo gameInfo){

        this.gameInfo = gameInfo;

    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if(event.getSource() == gameInfo.getBackButton()){
            gameInfo.dispose();
            new GameFrame();
        }
    }
}
