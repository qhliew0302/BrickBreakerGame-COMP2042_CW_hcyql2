package brickdestroy.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class HighScoreView extends JFrame implements ActionListener {

    private JLabel HIGHSCORE_TITLE;
    private JLabel SCORE_TEXT;
    private JButton backButton;
    private static final String DEF_TITLE = "Brick Destroy";

    private static final int DEF_WIDTH = 600;
    private static final int DEF_HEIGHT = 450;

    private String highScore1;
    private String highScore2;
    private String highScore3;

    private int highScore;


    public HighScoreView(){
        readScores();
        showHSTitle();
        drawScore();
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
        this.add(HIGHSCORE_TITLE);
        this.add(SCORE_TEXT);
        this.add(backButton);
    }

    private void autoLocate(){
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (size.width - this.getWidth()) / 2;
        int y = (size.height - this.getHeight()) / 2;
        this.setLocation(x,y);
    }

    public void readScores(){
        FileReader readFile;
        BufferedReader reader = null;
        try{
            readFile = new FileReader("src/brickdestroy/resources/highScore.txt");
            reader = new BufferedReader(readFile);
            highScore1 = reader.readLine();
            highScore2 = reader.readLine();
            highScore3 = reader.readLine();
        }catch(Exception e){
            System.out.println("Cannot read file!");
        } finally{
            try {
                if (reader != null)
                    reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    private void showHSTitle(){

        HIGHSCORE_TITLE = new JLabel();

        String infoText = "High Score Leaderboard";

        HIGHSCORE_TITLE.setText(infoText);
        HIGHSCORE_TITLE.setBounds(160,30,500,40);
        HIGHSCORE_TITLE.setFont(new Font("Verdana",Font.BOLD,20));


    }



    private void drawScore(){

        SCORE_TEXT = new JLabel();

        String scoreText =   "<html>" +
                "1. " + highScore1 + "<br/><br/>" +
                "2. " + highScore2 + "<br/><br/>" +
                "3. " + highScore3 + "<br/></html>" ;

        SCORE_TEXT.setText(scoreText);
        SCORE_TEXT.setBounds(160,10,500,300);
        SCORE_TEXT.setFont(new Font("Verdana",Font.PLAIN,13));


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
