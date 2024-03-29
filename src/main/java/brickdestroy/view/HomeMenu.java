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

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import brickdestroy.controller.*;

/**
 * A home menu class that shows the home menu of the game.
 */
public class HomeMenu extends JComponent {

    private static final String GREETINGS = "Welcome to:";
    private static final String GAME_TITLE = "Brick Destroy";
    private static final String CREDITS = "Version 1.0";
    private static final String START_TEXT = "Start";
    private static final String EXIT_TEXT = "Exit";
    private static final String INFO_TEXT = "Info";
    private static final String HIGHSCORE_TEXT = "High Score";
    private static final String RANK_TEXT = "Rank Mode";

    private static final Color BG_COLOR = Color.GREEN.darker();
    private static final Color BORDER_COLOR = new Color(200,8,21); //Venetian Red
    private static final Color DASH_BORDER_COLOR = new  Color(255, 216, 0);//school bus yellow
    private static final Color TEXT_COLOR = Color.WHITE;
    private static final Color CLICKED_BUTTON_COLOR = Color.WHITE;
    private static final Color CLICKED_TEXT = Color.WHITE;
    private static final int BORDER_SIZE = 5;
    private static final float[] DASHES = {12,6};

    private Rectangle menuFace;
    private Rectangle startButton;
    private Rectangle exitButton;
    private Rectangle infoButton;
    private Rectangle highScoreButton;
    private Rectangle rankButton;


    private BasicStroke borderStroke;
    private BasicStroke borderStroke_noDashes;

    private Font greetingsFont;
    private Font gameTitleFont;
    private Font creditsFont;
    private Font buttonFont;

    private GameFrame owner;
    private HomeMenuController homeMenuController;

    BufferedImage backgroundImage;

    private String gameType;


    public HomeMenu(GameFrame owner,Dimension area){

        this.setFocusable(true);
        this.requestFocusInWindow();
        HomeMenuController homeMenuController = new HomeMenuController(this);
        this.homeMenuController = homeMenuController;
        this.addMouseListener(homeMenuController);
        this.addMouseMotionListener(homeMenuController);

        this.owner = owner;


        menuFace = new Rectangle(new Point(0,0),area);
        this.setPreferredSize(area);

        Dimension btnDim = new Dimension(area.width / 3, area.height / 12);
        startButton = new Rectangle(btnDim);
        exitButton = new Rectangle(btnDim);
        infoButton = new Rectangle(btnDim);
        highScoreButton = new Rectangle(btnDim);
        rankButton = new Rectangle(btnDim);

        borderStroke = new BasicStroke(BORDER_SIZE,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND,0,DASHES,0);
        borderStroke_noDashes = new BasicStroke(BORDER_SIZE,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND);

        greetingsFont = new Font("Noto Mono",Font.PLAIN,25);
        gameTitleFont = new Font("Noto Mono",Font.BOLD,40);
        creditsFont = new Font("Monospaced",Font.PLAIN,10);
        buttonFont = new Font("Verdana",Font.BOLD,startButton.height-2);



    }


    public void paint(Graphics g){
        drawMenu((Graphics2D)g);
    }


    /**
     * Draws the texts and buttons on the home menu.
     *
     * @param g2d graphics 2d
     */
    public void drawMenu(Graphics2D g2d){

        drawContainer(g2d);

        /*
        all the following method calls need a relative
        painting directly into the HomeMenu rectangle,
        so the translation is made here so the other methods do not do that.
         */
        Color prevColor = g2d.getColor();
        Font prevFont = g2d.getFont();

        double x = menuFace.getX();
        double y = menuFace.getY();

        g2d.translate(x,y);

        //method calls
        drawText(g2d);
        drawButton(g2d);
        //end of methods calls

        g2d.translate(-x,-y);
        g2d.setFont(prevFont);
        g2d.setColor(prevColor);
    }

    /**
     * The background of homeMenu is an image.
     * If the image file could not be read,
     * the home menu will show its initial background.
     *
     * @param g2d graphics 2d
     */
    private void drawContainer(Graphics2D g2d) {
        try{
            backgroundImage =ImageIO.read(new File("src/main/java/brickdestroy/resources/Background.jpg"));
            g2d.drawImage(backgroundImage, 0,0,450,300,null);
        }catch(IOException ex){
            Color prev = g2d.getColor();

            g2d.setColor(BG_COLOR);
            g2d.fill(menuFace);

            Stroke tmp = g2d.getStroke();

            g2d.setStroke(borderStroke_noDashes);
            g2d.setColor(DASH_BORDER_COLOR);
            g2d.draw(menuFace);

            g2d.setStroke(borderStroke);
            g2d.setColor(BORDER_COLOR);
            g2d.draw(menuFace);

            g2d.setStroke(tmp);

            g2d.setColor(prev);
        }

    }

    /**
     * Draws the greeting, title and credit texts on the home menu.
     *
     * @param g2d graphics 2d
     */
    private void drawText(Graphics2D g2d){

        g2d.setColor(TEXT_COLOR);

        FontRenderContext frc = g2d.getFontRenderContext();

        Rectangle2D greetingsRect = greetingsFont.getStringBounds(GREETINGS,frc);
        Rectangle2D gameTitleRect = gameTitleFont.getStringBounds(GAME_TITLE,frc);
        Rectangle2D creditsRect = creditsFont.getStringBounds(CREDITS,frc);

        int sX,sY;

        sX = (int)(menuFace.getWidth() - greetingsRect.getWidth()) / 2;
        sY = (int)(menuFace.getHeight() / 5);

        g2d.setFont(greetingsFont);
        g2d.drawString(GREETINGS,sX,sY);

        sX = (int)(menuFace.getWidth() - gameTitleRect.getWidth()) / 2;
        sY += (int) gameTitleRect.getHeight() * 1.1;//add 10% of String height between the two strings

        g2d.setFont(gameTitleFont);
        g2d.drawString(GAME_TITLE,sX,sY);

        sX = (int)(menuFace.getWidth() - creditsRect.getWidth()) / 2;
        sY += (int) creditsRect.getHeight() * 1.1;

        g2d.setFont(creditsFont);
        g2d.drawString(CREDITS,sX,sY);


    }

    /**
     * Draws the buttons on the home menu.
     *
     * @param g2d graphics 2d
     */
    private void drawButton(Graphics2D g2d){

        FontRenderContext frc = g2d.getFontRenderContext();

        Rectangle2D startTextRectangle = buttonFont.getStringBounds(START_TEXT,frc);
        Rectangle2D exitTextRectangle = buttonFont.getStringBounds(EXIT_TEXT,frc);
        Rectangle2D infoTextRectangle = buttonFont.getStringBounds(INFO_TEXT,frc);
        Rectangle2D highScoreTextRectangle = buttonFont.getStringBounds(HIGHSCORE_TEXT,frc);
        Rectangle2D rankTextRectangle = buttonFont.getStringBounds(HIGHSCORE_TEXT,frc);

        g2d.setFont(buttonFont);

        int x = (menuFace.width - startButton.width) / 2;
        int y =(int) ((menuFace.height - startButton.height) * 0.55);

        setPosition(g2d, startButton, startTextRectangle, homeMenuController.isStartClicked(), START_TEXT, x - 80, y);

        setPosition(g2d, rankButton, rankTextRectangle, homeMenuController.isRankClicked(), RANK_TEXT, x + 80, y);

        y += 35.0;

        setPosition(g2d, infoButton, infoTextRectangle, homeMenuController.isInfoClicked(), INFO_TEXT, x, y);

        x = infoButton.x;
        y = infoButton.y;

        y += 35.0;

        setPosition(g2d, exitButton, exitTextRectangle, homeMenuController.isExitClicked(), EXIT_TEXT, x, y);

        x = exitButton.x;
        y = exitButton.y;

        y += 35.0;

        setPosition(g2d, highScoreButton, highScoreTextRectangle, homeMenuController.isHighScoreClicked(), HIGHSCORE_TEXT, x, y);


    }

    // create a setPosition method to remove duplicate codes when setting positions of the buttons

    /**
     * Sets the position of the buttons.
     *
     * @param g2d graphics 2d
     * @param button type of button
     * @param textRectangle rectangle text of button
     * @param buttonClicked button is clicked
     * @param text the text on the button
     * @param x the x-position of the button
     * @param y the y-position of the button
     */
    public void setPosition(Graphics2D g2d, Rectangle button, Rectangle2D textRectangle, boolean buttonClicked, String text, int x, int y){
        button.setLocation(x, y);
        x = (int)(button.getWidth() - textRectangle.getWidth()) / 2;
        y = (int)(button.getHeight() - textRectangle.getHeight()) / 2;

        x += button.x;
        y += button.y + (button.height * 0.9);

        if(buttonClicked){
            Color tmp = g2d.getColor();
            g2d.setColor(CLICKED_BUTTON_COLOR);
            g2d.draw(button);
            g2d.setColor(CLICKED_TEXT);
            g2d.drawString(text,x,y);
            g2d.setColor(tmp);
        }
        else{
            g2d.draw(button);
            g2d.drawString(text,x,y);
        }
    }

    public Rectangle getStartButton(){
        return startButton;
    }

    public Rectangle getExitButton(){
        return exitButton;
    }

    public Rectangle getInfoButton(){
        return infoButton;
    }

    public Rectangle getRankButton(){
        return rankButton;
    }

    public Rectangle getHighScoreButton(){
        return highScoreButton;
    }

    public GameFrame getOwner(){
        return owner;
    }

    public String getGameType(){
        return gameType;
    }

    public void setGameType(String gameType){
        this.gameType = gameType;
    }
}
