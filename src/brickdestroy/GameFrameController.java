package brickdestroy;

import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

public class GameFrameController implements WindowFocusListener {

    private GameFrame gameFrame;
    private boolean gaming;

    public GameFrameController(GameFrame gameFrame){
        this.gameFrame = gameFrame;
        gaming = false;

    }

    @Override
    public void windowGainedFocus(WindowEvent windowEvent) {
        /*
            the first time the frame loses focus is because
            it has been disposed to install the GameBoard,
            so went it regains the focus it's ready to play.
            of course calling a method such as 'onLostFocus'
            is useful only if the GameBoard as been displayed
            at least once
         */
        gaming = true;
    }

    @Override
    public void windowLostFocus(WindowEvent windowEvent) {
        if(gaming){
            try{
                gameFrame.getGameBoard().onLostFocus();
            }catch(Exception e){
                gameFrame.getRankGameBoard().onLostFocus();
            }

        }
    }
}
