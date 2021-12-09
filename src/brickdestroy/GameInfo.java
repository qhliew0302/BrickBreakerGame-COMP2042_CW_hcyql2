package brickdestroy;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class GameInfo extends JFrame implements ActionListener {

    private JLabel INFO_TITLE;
    private JLabel INFO_TEXT;
    private JButton backButton;
    private static final String DEF_TITLE = "Brick Destroy";

    private static final int DEF_WIDTH = 600;
    private static final int DEF_HEIGHT = 450;




    public GameInfo() {
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
        INFO_TITLE.setBounds(220,30,500,40);
        INFO_TITLE.setFont(new Font("Verdana",Font.BOLD,20));


    }



    private void showInfoText(){

        INFO_TEXT = new JLabel();

        String infoText =   "<html>" +
                            "There are 6 levels in this Brick Destroy game. <br/><br/><br/>" +
                            "1. Click the Start button to play the game.<br/>" +
                            "2. Click the Exit button to close the game.<br/>" +
                            "3. Press space bar to start/stop the motion of the ball.<br/>" +
                            "4. Press A key or left arrow key to move the Player bar to the left.<br/>" +
                            "5. Press D key or right arrow key to move the Player bar to the right.<br/>" +
                            "6. Press ALT key + Shift key + F1 key to call the Debug Console.<br/>" +
                            "7. The debug console is used to change the ball's speed, skip level and reset ball.<br/>" +
                            "8. Press Esc key to show Pause Menu.</html>";

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
        backButton.addActionListener(this);
    }



    @Override
    public void actionPerformed(ActionEvent event) {
        if(event.getSource() == backButton){
            dispose();
            new GameFrame();
        }
    }
}
