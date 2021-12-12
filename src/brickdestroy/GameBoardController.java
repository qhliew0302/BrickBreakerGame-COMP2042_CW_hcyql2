package brickdestroy;

import java.awt.*;
import java.awt.event.*;

public class GameBoardController implements KeyListener, MouseListener, MouseMotionListener {

    private GameBoard gameBoard;

    public GameBoardController(GameBoard gameBoard){
        this.gameBoard = gameBoard;
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        switch(keyEvent.getKeyCode()){
            case KeyEvent.VK_A:
            case KeyEvent.VK_LEFT: // left arrow key
                gameBoard.getWall().getPlayer().moveLeft();
                break;
            case KeyEvent.VK_D:
            case KeyEvent.VK_RIGHT: // right arrow key
                gameBoard.getWall().getPlayer().moveRight();
                break;
            case KeyEvent.VK_ESCAPE:
                gameBoard.setShowPauseMenu(!(gameBoard.isShowPauseMenu()));
                gameBoard.repaint();
                gameBoard.getTimer().stop();
                if(gameBoard.isRankMode())
                    gameBoard.getGameTimer().setGameStatus(false);
                break;
            case KeyEvent.VK_SPACE:
                if(!(gameBoard.isShowPauseMenu())) {
                    if (gameBoard.getTimer().isRunning()) {
                        if(gameBoard.isRankMode())
                            gameBoard.getGameTimer().setGameStatus(false);
                        gameBoard.getTimer().stop();
                    } else {
                        if(gameBoard.isRankMode())
                            gameBoard.getGameTimer().setGameStatus(true);
                        gameBoard.getTimer().start();
                    }
                }
                break;
            case KeyEvent.VK_F1:
                if(keyEvent.isAltDown() && keyEvent.isShiftDown() && !(gameBoard.isRankMode()))
                    gameBoard.getDebugConsole().setVisible(true);
            default:
                gameBoard.getWall().getPlayer().stop();
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        gameBoard.getWall().getPlayer().stop();
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if(!(gameBoard.isShowPauseMenu()))
            return;
        if(gameBoard.getContinueButtonRect().contains(p)){
            gameBoard.setShowPauseMenu(false);
            gameBoard.repaint();
        }
        else if(gameBoard.getRestartButtonRect().contains(p)){
            gameBoard.setMessage("Restarting Game...");
            gameBoard.getWall().ballReset();
            gameBoard.getWall().wallReset();
            gameBoard.setShowPauseMenu(false);
            gameBoard.repaint();
        }
        else if(gameBoard.getExitButtonRect().contains(p)){
            System.exit(0);
        }

    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseDragged(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if(gameBoard.getExitButtonRect() != null && gameBoard.isShowPauseMenu()) {
            if (gameBoard.getExitButtonRect().contains(p) || gameBoard.getExitButtonRect().contains(p) || gameBoard.getRestartButtonRect().contains(p))
                this.gameBoard.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            else
                this.gameBoard.setCursor(Cursor.getDefaultCursor());
        }
        else{
            this.gameBoard.setCursor(Cursor.getDefaultCursor());
        }
    }
}
