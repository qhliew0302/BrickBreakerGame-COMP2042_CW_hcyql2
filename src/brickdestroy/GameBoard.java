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
import java.awt.event.*;
import java.awt.font.FontRenderContext;



public class GameBoard extends JComponent implements KeyListener,MouseListener,MouseMotionListener {

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

    private String message;
    private String timerMessage;
    private String scoreMessage;

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
    private int score;
    private int brickCount;
    private int brickCountDiff;


    public GameBoard(JFrame owner, String gameType){
        super();


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
                scoreMessage = String.format("Score: %d", gameScore.getScore());
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
                        gameTimer.resetTimer();
                        fixSpeed();
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
                    calculateScore();
                    brickCount = wall.getBrickCount();
                }
                else{
                    message = "ALL WALLS DESTROYED";
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
        this.addKeyListener(this);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }

    private void enableRankMode(Wall wall){
        rankMode = true;
        gameTimer = new GameTimer();
        gameScore = new GameScore();
        gameTimer.resetTimer();
        gameTimer.setGameStatus(false);
        timerMessage = "";
        scoreMessage = "";
        timerMessage = String.format("Time Left: %s:%s", gameTimer.getDdMinute(), gameTimer.getDdSecond());
        scoreMessage = String.format("Score: %d", gameScore.getScore());
        score = gameScore.getScore();
        brickCount = wall.getBrickCount();
        fixSpeed();
    }

    private void calculateScore(){
        if(brickCount > wall.getBrickCount()){
            brickCountDiff = brickCount- wall.getBrickCount();
            brickCount = wall.getBrickCount();
        }
        score += brickCountDiff;
        brickCountDiff = 0;
        gameScore.setScore(score);
    }

    private void fixSpeed(){
        wall.getBall().setXSpeed(3);
        wall.getBall().setYSpeed(-3);
    }


    public void paint(Graphics g){

        Graphics2D g2d = (Graphics2D) g;

        clear(g2d);

        g2d.setColor(Color.BLUE);
        g2d.drawString(message,250,225);
        if(rankMode){
            g2d.drawString(timerMessage, 10, 80);
            g2d.drawString(scoreMessage, 500,80);
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

    // show GameBoard
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

    // draw Pause Menu
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

    @Override
    public void keyTyped(KeyEvent keyEvent) {
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        switch(keyEvent.getKeyCode()){
            case KeyEvent.VK_A:
            case KeyEvent.VK_LEFT: // left arrow key
                wall.getPlayer().moveLeft();
                break;
            case KeyEvent.VK_D:
            case KeyEvent.VK_RIGHT: // right arrow key
                wall.getPlayer().moveRight();
                break;
            case KeyEvent.VK_ESCAPE:
                showPauseMenu = !showPauseMenu;
                repaint();
                timer.stop();
                if(rankMode)
                    gameTimer.setGameStatus(false);
                break;
            case KeyEvent.VK_SPACE:
                if(!showPauseMenu) {
                    if (timer.isRunning()) {
                        if(rankMode)
                            gameTimer.setGameStatus(false);
                        timer.stop();
                    } else {
                        if(rankMode)
                            gameTimer.setGameStatus(true);
                        timer.start();
                    }
                }
                break;
            case KeyEvent.VK_F1:
                if(keyEvent.isAltDown() && keyEvent.isShiftDown() && !rankMode)
                    debugConsole.setVisible(true);
            default:
                wall.getPlayer().stop();
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        wall.getPlayer().stop();
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if(!showPauseMenu)
            return;
        if(continueButtonRect.contains(p)){
            showPauseMenu = false;
            repaint();
        }
        else if(restartButtonRect.contains(p)){
            message = "Restarting Game...";
            wall.ballReset();
            wall.wallReset();
            showPauseMenu = false;
            repaint();
        }
        else if(exitButtonRect.contains(p)){
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
        if(exitButtonRect != null && showPauseMenu) {
            if (exitButtonRect.contains(p) || continueButtonRect.contains(p) || restartButtonRect.contains(p))
                this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            else
                this.setCursor(Cursor.getDefaultCursor());
        }
        else{
            this.setCursor(Cursor.getDefaultCursor());
        }
    }

    public void onLostFocus(){
        timer.stop();
        message = "Focus Lost";
        repaint();
    }

}
