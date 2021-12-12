package brickdestroy.controller;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import brickdestroy.view.*;

/**
 * A home menu controller class that controls the home menu class.
 */
public class HomeMenuController implements MouseListener, MouseMotionListener {
    private HomeMenu homeMenu;

    private boolean startClicked;
    private boolean exitClicked;
    private boolean infoClicked;
    private boolean highScoreClicked;
    private boolean rankClicked;

    private static final String START_TEXT = "Start";
    private static final String RANK_TEXT = "Rank Mode";

    public HomeMenuController(HomeMenu homeMenu){
        this.homeMenu = homeMenu;
    }

    public boolean isStartClicked() {
        return startClicked;
    }

    public boolean isExitClicked() {
        return exitClicked;
    }

    public boolean isInfoClicked() {
        return infoClicked;
    }

    public boolean isHighScoreClicked() {
        return highScoreClicked;
    }

    public boolean isRankClicked() {
        return rankClicked;
    }

    /**
     * If the button on the homeMenu is clicked, different window will be prompted.
     * @param mouseEvent
     */
    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if(homeMenu.getStartButton().contains(p)){
            homeMenu.setGameType(START_TEXT);
            homeMenu.getOwner().enableGameBoard(START_TEXT);
        }
        else if(homeMenu.getExitButton().contains(p)){
            System.out.println("Goodbye " + System.getProperty("user.name"));
            System.exit(0);
        }
        else if(homeMenu.getInfoButton().contains(p)){
            new GameInfo();
        }
        else if(homeMenu.getHighScoreButton().contains(p)){
            new HighScoreView();
        }
        else if(homeMenu.getRankButton().contains(p)){
            homeMenu.setGameType(RANK_TEXT);
            homeMenu.getOwner().enableGameBoard(RANK_TEXT);
        }
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if(homeMenu.getStartButton().contains(p)){
            startClicked = true;
            homeMenu.repaint(homeMenu.getStartButton().x,homeMenu.getStartButton().y,homeMenu.getStartButton().width+1,homeMenu.getStartButton().height+1);

        }
        else if(homeMenu.getExitButton().contains(p)){
            exitClicked = true;
            homeMenu.repaint(homeMenu.getExitButton().x, homeMenu.getExitButton().y, homeMenu.getExitButton().width+1, homeMenu.getExitButton().height+1);
        }
        else if(homeMenu.getInfoButton().contains(p)){
            infoClicked = true;
            homeMenu.repaint(homeMenu.getInfoButton().x, homeMenu.getInfoButton().y, homeMenu.getInfoButton().width+1, homeMenu.getInfoButton().height+1);
        }
        else if(homeMenu.getHighScoreButton().contains(p)){
            highScoreClicked = true;
            homeMenu.repaint(homeMenu.getHighScoreButton().x, homeMenu.getHighScoreButton().y, homeMenu.getHighScoreButton().width+1, homeMenu.getHighScoreButton().height+1);
        }
        else if(homeMenu.getRankButton().contains(p)){
            rankClicked = true;
            homeMenu.repaint(homeMenu.getRankButton().x, homeMenu.getRankButton().y, homeMenu.getRankButton().width+1, homeMenu.getRankButton().height+1);
        }
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        if(startClicked){
            startClicked = false;
            homeMenu.repaint(homeMenu.getStartButton().x,homeMenu.getStartButton().y,homeMenu.getStartButton().width+1,homeMenu.getStartButton().height+1);
        }
        else if(exitClicked){
            exitClicked = false;
            homeMenu.repaint(homeMenu.getExitButton().x, homeMenu.getExitButton().y, homeMenu.getExitButton().width+1, homeMenu.getExitButton().height+1);
        }
        else if(infoClicked){
            infoClicked = false;
            homeMenu.repaint(homeMenu.getInfoButton().x, homeMenu.getInfoButton().y, homeMenu.getInfoButton().width+1, homeMenu.getInfoButton().height+1);
        }
        else if(highScoreClicked){
            highScoreClicked = false;
            homeMenu.repaint(homeMenu.getHighScoreButton().x, homeMenu.getHighScoreButton().y, homeMenu.getHighScoreButton().width+1, homeMenu.getHighScoreButton().height+1);
        }
        else if(rankClicked){
            rankClicked = false;
            homeMenu.repaint(homeMenu.getRankButton().x, homeMenu.getRankButton().y, homeMenu.getRankButton().width+1, homeMenu.getRankButton().height+1);
        }
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
     * When the mouse hovers across the buttons, the cursor will change.
     *
     * @param mouseEvent mouse hovered across button
     */
    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if(homeMenu.getStartButton().contains(p) || homeMenu.getExitButton().contains(p) || homeMenu.getInfoButton().contains(p) || homeMenu.getHighScoreButton().contains(p) || homeMenu.getRankButton().contains(p))
            this.homeMenu.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        else
            this.homeMenu.setCursor(Cursor.getDefaultCursor());

    }
}
