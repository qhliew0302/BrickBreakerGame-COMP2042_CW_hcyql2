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
import java.awt.*;
import java.awt.font.FontRenderContext;
import brickdestroy.model.*;
import brickdestroy.controller.*;


/**
 * A game board class that draws the game on the screen.
 */
public class GameBoard extends JComponent{

    private static final String CONTINUE = "Continue";
    private static final String RESTART = "Restart";
    private static final String EXIT = "Exit";
    private static final String PAUSE = "Pause Menu";
    private static final int TEXT_SIZE = 30;
    private static final Color MENU_COLOR = new Color(0,255,0);
    private static final String RANK = "Rank Mode";


    private static final int DEF_WIDTH = 600;
    private static final int DEF_HEIGHT = 450;

    private static final Color BG_COLOR = Color.WHITE;

    private Timer timer;
    private GameTimer gameTimer;

    private Wall wall;
    private Level level;
    private GameBoardController gameBoardController;

    private String message;
    private String timerMessage;
    private String scoreMessage;
    private String highScoreMessage;

    private boolean showPauseMenu;

    private Font menuFont;

    private Rectangle continueButtonRect;
    private Rectangle exitButtonRect;
    private Rectangle restartButtonRect;
    private int strLen;

    private DebugConsole debugConsole;
    private boolean rankMode;
    private String gameType;
    private GameScore gameScore;
    private HighScoreController highScore;
    private int score;
    private int bonusScore;
    private int brickCount;
    private int brickCountDiff;


    public GameBoard(JFrame owner, String gameType){
        super();

        gameBoardController = new GameBoardController(this);
        strLen = 0;
        showPauseMenu = false;
        this.gameType = gameType;
        rankMode = false;


        menuFont = new Font("Monospaced",Font.PLAIN,TEXT_SIZE);

        this.initialize();
        message = "";
        wall = new Wall(new Rectangle(0,0,DEF_WIDTH,DEF_HEIGHT),new Point(300,430));
        level = new Level(new Rectangle(0,0,DEF_WIDTH,DEF_HEIGHT),30,3, 6/2, wall);
        if(!rankMode)
            debugConsole = new DebugConsole(owner,wall,level,this);
        //initialize the first level
        level.nextLevel();
        if(gameType == RANK){
            enableRankMode(wall);
        }

        timer = new Timer(10, e ->{
            wall.move();
            wall.findImpacts();
            message = String.format("Bricks: %d Balls %d",wall.getBrickCount(),wall.getBallCount());
            if(rankMode){
                calculateScore();
                timerMessage = String.format("Time Left: %s:%s", gameTimer.getDdMinute(), gameTimer.getDdSecond());
                scoreMessage = String.format("Score: %d", GameScore.score);
                highScoreMessage = String.format("Current High Score: %d", highScore.getHighScore());
                gameTimer.setGameStatus(true);
                if(gameTimer.getSecond() == 0 && gameTimer.getMinute() == 0){
                    wall.wallReset();
                    message = "Time's up! Game Over";
                    wall.ballReset();
                    timer.stop();
                    gameTimer.resetTimer();
                }
            }
            if(wall.isBallLost()){
                if(wall.ballEnd()){
                    wall.wallReset();
                    message = "Game over";
                    if(rankMode){
                        highScore.checkScore();
                        gameTimer.resetTimer();
                        gameScore.resetScore();
                        fixSpeed();
                        brickCount = wall.getBrickCount();
                    }
                }
                wall.ballReset();
                timer.stop();
                if(rankMode){
                    gameTimer.setGameStatus(false);
                    fixSpeed();
                }
            }
            else if(wall.isDone()){
                if(level.hasLevel()){
                    message = "Go to Next Level";
                    timer.stop();
                    wall.ballReset();
                    wall.wallReset();
                    level.nextLevel();
                    if(rankMode)
                        calculateScore();
                    brickCount = wall.getBrickCount();
                }
                else{
                    message = "ALL WALLS DESTROYED";
                    if(rankMode)
                        highScore.checkScore();
                    timer.stop();
                }
                if(rankMode){
                    gameTimer.resetTimer();
                    gameTimer.setGameStatus(false);
                    fixSpeed();
                }
            }

            repaint();
        });

    }

    private void initialize(){
        this.setPreferredSize(new Dimension(DEF_WIDTH,DEF_HEIGHT));
        this.setFocusable(true);
        this.requestFocusInWindow();
        this.addKeyListener(gameBoardController);
        this.addMouseListener(gameBoardController);
        this.addMouseMotionListener(gameBoardController);
    }

    /**
     * Enables rank mode that will set timer and calculate score if rank mode is selected.
     * @param wall
     */
    private void enableRankMode(Wall wall){
        rankMode = true;
        gameTimer = new GameTimer();
        gameScore = new GameScore();
        highScore = new HighScoreController();
        gameTimer.resetTimer();
        gameTimer.setGameStatus(false);
        timerMessage = "";
        scoreMessage = "";
        timerMessage = String.format("Time Left: %s:%s", gameTimer.getDdMinute(), gameTimer.getDdSecond());
        scoreMessage = String.format("Score: %d", GameScore.score);
        highScoreMessage = String.format("Current High Score: %d", highScore.getHighScore());
        score = GameScore.score;
        brickCount = wall.getBrickCount();
        fixSpeed();
    }

    /**
     * Calculates the player's score.
     */
    private void calculateScore(){
        score = GameScore.score;
        bonusScore = 0;
        if(brickCount > wall.getBrickCount()){
            brickCountDiff = brickCount- wall.getBrickCount();
            brickCount = wall.getBrickCount();
        }
        if(wall.getBrickCount() == 0){
            bonusScore = wall.getBallCount() + (gameTimer.getMinute() * 60) + gameTimer.getSecond();
        }
        score += brickCountDiff + bonusScore;
        brickCountDiff = 0;
        GameScore.score = score;
    }

    /**
     * In rank mode, the speed of ball is fixed.
     */
    private void fixSpeed(){
        wall.getBall().setXSpeed(3);
        wall.getBall().setYSpeed(-3);
    }


    /**
     * Draws messages, bricks, ball and player on the game screen.
     *
     * @param g graphics
     */
    public void paint(Graphics g){

        Graphics2D g2d = (Graphics2D) g;

        clear(g2d);

        g2d.setColor(Color.BLUE);
        g2d.drawString(message,250,225);
        if(rankMode){
            g2d.drawString(timerMessage, 10, 80);
            g2d.drawString(scoreMessage, 420,80);
            g2d.drawString(highScoreMessage, 420, 100);
        }

        drawBall(wall.getBall(),g2d);

        for(Brick b : wall.getBricks())
            if(!b.isBroken())
                drawBrick(b,g2d);

        drawPlayer(wall.getPlayer(),g2d);

        if(showPauseMenu)
            drawMenu(g2d);

        Toolkit.getDefaultToolkit().sync();
    }

    private void clear(Graphics2D g2d){
        Color tmp = g2d.getColor();
        g2d.setColor(BG_COLOR);
        g2d.fillRect(0,0,getWidth(),getHeight());
        g2d.setColor(tmp);
    }

    private void drawBrick(Brick brick,Graphics2D g2d){
        Color tmp = g2d.getColor();

        g2d.setColor(brick.getInnerColor());
        g2d.fill(brick.getBrick());

        g2d.setColor(brick.getBorderColor());
        g2d.draw(brick.getBrick());


        g2d.setColor(tmp);
    }


    // draw Ball
    private void drawBall(Ball ball,Graphics2D g2d){
        Color tmp = g2d.getColor();

        Shape s = ball.getBallFace();

        g2d.setColor(ball.getInnerColor());
        g2d.fill(s);

        g2d.setColor(ball.getBorderColor());
        g2d.draw(s);

        g2d.setColor(tmp);
    }

    // draw Player
    private void drawPlayer(Player p,Graphics2D g2d){
        Color tmp = g2d.getColor();

        Shape s = p.getPlayerFace();
        g2d.setColor(Player.INNER_COLOR);
        g2d.fill(s);

        g2d.setColor(Player.BORDER_COLOR);
        g2d.draw(s);

        g2d.setColor(tmp);
    }

    private void drawMenu(Graphics2D g2d){
        obscureGameBoard(g2d);
        drawPauseMenu(g2d);
    }

    /**
     * Shows gameboard.
     *
     * @param g2d graphics 2d
     */
    private void obscureGameBoard(Graphics2D g2d){

        Composite tmp = g2d.getComposite();
        Color tmpColor = g2d.getColor();

        AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER,0.55f);
        g2d.setComposite(ac);

        g2d.setColor(Color.BLACK);
        g2d.fillRect(0,0,DEF_WIDTH,DEF_HEIGHT);

        g2d.setComposite(tmp);
        g2d.setColor(tmpColor);
    }

    /**
     * Draws pause menu.
     *
     * @param g2d graphics 2d
     */
    private void drawPauseMenu(Graphics2D g2d){
        Font tmpFont = g2d.getFont();
        Color tmpColor = g2d.getColor();


        g2d.setFont(menuFont);
        g2d.setColor(MENU_COLOR);

        if(strLen == 0){
            FontRenderContext frc = g2d.getFontRenderContext();
            strLen = menuFont.getStringBounds(PAUSE,frc).getBounds().width;
        }

        int x = (this.getWidth() - strLen) / 2;
        int y = this.getHeight() / 10;

        g2d.drawString(PAUSE,x,y);

        x = this.getWidth() / 8;
        y = this.getHeight() / 4;


        if(continueButtonRect == null){
            FontRenderContext frc = g2d.getFontRenderContext();
            continueButtonRect = menuFont.getStringBounds(CONTINUE,frc).getBounds();
            continueButtonRect.setLocation(x,y-continueButtonRect.height);
        }

        g2d.drawString(CONTINUE,x,y);

        y *= 2;

        if(restartButtonRect == null){
            restartButtonRect = (Rectangle) continueButtonRect.clone();
            restartButtonRect.setLocation(x,y-restartButtonRect.height);
        }

        g2d.drawString(RESTART,x,y);

        y *= 3.0/2;

        if(exitButtonRect == null){
            exitButtonRect = (Rectangle) continueButtonRect.clone();
            exitButtonRect.setLocation(x,y-exitButtonRect.height);
        }

        g2d.drawString(EXIT,x,y);



        g2d.setFont(tmpFont);
        g2d.setColor(tmpColor);
    }

    public GameScore getGameScore(){
        return gameScore;
    }


    public void onLostFocus(){
        timer.stop();
        message = "Focus Lost";
        repaint();
    }

    public Wall getWall(){
        return wall;
    }

    public boolean isShowPauseMenu(){
        return showPauseMenu;
    }

    public void setShowPauseMenu(boolean showPauseMenu){
        this.showPauseMenu = showPauseMenu;
    }

    public DebugConsole getDebugConsole(){
        return debugConsole;
    }

    public boolean isRankMode(){
        return rankMode;
    }

    public GameTimer getGameTimer(){
        return gameTimer;
    }

    public Timer getTimer(){
        return timer;
    }

    public Rectangle getContinueButtonRect(){
        return continueButtonRect;
    }

    public Rectangle getExitButtonRect(){
        return exitButtonRect;
    }

    public Rectangle getRestartButtonRect(){
        return restartButtonRect;
    }

    public void setMessage(String message){
        this.message = message;
    }
}
