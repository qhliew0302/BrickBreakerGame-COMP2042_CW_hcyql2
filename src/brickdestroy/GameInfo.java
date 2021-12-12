package brickdestroy;

import javax.swing.*;
import java.awt.*;

public class GameInfo extends JFrame{

    private JLabel INFO_TITLE;
    private JLabel INFO_TEXT;
    private JButton backButton;
    private static final String DEF_TITLE = "Brick Destroy";

    private static final int DEF_WIDTH = 600;
    private static final int DEF_HEIGHT = 450;

    private GameInfoController gameInfoController;


    public GameInfo() {
        gameInfoController = new GameInfoController(this);
        showInfoTitle();
        showInfoText();
        drawButton();
        initialize();
    }

    public void initialize(){
        this.setTitle(DEF_TITLE);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(DEF_WIDTH, DEF_HEIGHT);
        this.setLayout(null);
        this.autoLocate();
        this.setVisible(true);
        this.setResizable(false);
        this.add(INFO_TITLE);
        this.add(INFO_TEXT);
        this.add(backButton);
    }

    private void autoLocate(){
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (size.width - this.getWidth()) / 2;
        int y = (size.height - this.getHeight()) / 2;
        this.setLocation(x,y);
    }

    private void showInfoTitle(){

        INFO_TITLE = new JLabel();

        String infoText = "How To Play";

        INFO_TITLE.setText(infoText);
        INFO_TITLE.setBounds(220,15,500,40);
        INFO_TITLE.setFont(new Font("Verdana",Font.BOLD,20));


    }



    private void showInfoText(){

        INFO_TEXT = new JLabel();

        String infoText =   "<html>" +
                            "There are two modes in this Brick Destroy game.<br/>" +
                            "In normal mode, the player can skip level and adjust the ball's speed.<br/>" +
                            "In ranked mode, timer will be set and score will be recorded.<br/>" +
                            "Skipping levels and changing ball's speed are not allowed in ranked mode!<br/>" +
                            "There are 5 levels in each mode. <br/><br/>" +
                            "1. Click the Start button to enter normal mode.<br/>" +
                            "2. Click the Ranked Mode button to enter ranked mode.<br/>" +
                            "3. Click the Exit button to close the game.<br/>" +
                            "4. Press space bar to start/stop the motion of the ball.<br/>" +
                            "5. Press A key or left arrow key to move the Player bar to the left.<br/>" +
                            "6. Press D key or right arrow key to move the Player bar to the right.<br/>" +
                            "7. Press ALT key + Shift key + F1 key to call the Debug Console. (Not applicable in ranked mode)<br/>" +
                            "8. The debug console is used to change the ball's speed, skip level and reset ball.<br/>" +
                            "9. Press Esc key to show Pause Menu.</html>";

        INFO_TEXT.setText(infoText);
        INFO_TEXT.setBounds(60,50,500,300);
        INFO_TEXT.setFont(new Font("Verdana",Font.PLAIN,13));


    }

    private void drawButton(){
        backButton = new JButton("Back");
        backButton.setBounds(250,350,100, 40);
        backButton.setFont(new Font("Verdana",Font.BOLD,15));
        backButton.setForeground(Color.BLACK);
        backButton.setBackground(Color.white);
        backButton.setFocusable(false);
        backButton.addActionListener(gameInfoController);
    }

    public JButton getBackButton(){
        return backButton;
    }

}
