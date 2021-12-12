package brickdestroy.controller;

import java.awt.*;
import java.awt.event.*;
import brickdestroy.view.*;

/**
 * A GameBoard Controller class that controls gameBoard class.
 */
public class GameBoardController implements KeyListener, MouseListener, MouseMotionListener {

    private GameBoard gameBoard;

    public GameBoardController(GameBoard gameBoard){
        this.gameBoard = gameBoard;
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {
    }

    /**
     * Key A/ Arrow left key moves the player to the left.
     * Key D/ Arrow right key moves the player to the right.
     * Esc key shows Pause menu.
     * Space bar pause/resume the game.
     * ALT key + Shift key + F1 shows the debug console panel.
     *
     * @param keyEvent key pressed
     */
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

    /**
     * In Pause menu,
     * Continue is clicked, the game continues.
     * Restart is clicked, the game restarts.
     * Exit is clicked, the game window closes.
     *
     * @param mouseEvent mouse clicked
     */
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
            gameBoard.getGameTimer().resetTimer();
            gameBoard.getGameScore().resetScore();
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

    /**
     * When the mouse is hovered across the buttons in pause menu,
     * the cursor changes.
     *
     * @param mouseEvent mouse hovered the button
     */
    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if(gameBoard.getExitButtonRect() != null && gameBoard.isShowPauseMenu()) {
            if (gameBoard.getContinueButtonRect().contains(p) || gameBoard.getExitButtonRect().contains(p) || gameBoard.getRestartButtonRect().contains(p))
                this.gameBoard.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            else
                this.gameBoard.setCursor(Cursor.getDefaultCursor());
        }
        else{
            this.gameBoard.setCursor(Cursor.getDefaultCursor());
        }
    }
}
