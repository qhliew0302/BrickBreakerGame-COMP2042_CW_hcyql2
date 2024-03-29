package brickdestroy.controller;

import javax.swing.*;
import java.io.*;
import brickdestroy.view.*;

/**
 * A high score controller class that controls the high score class.
 */
public class HighScoreController {

    private String highScore1;
    private String highScore2;
    private String highScore3;

    private int highScore;

    private int score;

    public HighScoreController(){
        readScores();
    }

    /**
     * Reads the top 3 high scores stored in the high score file.
     */
    public void readScores(){
        FileReader readFile;
        BufferedReader reader = null;
        try{
            readFile = new FileReader("src/main/java/brickdestroy/resources/highScore.txt");
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

    /**
     * Check the score whether it is higher than the current high score.
     */
    public void checkScore(){
        score = GameScore.score;
        if((score > convertToInt(highScore3) || score > convertToInt(highScore2))&& score <= convertToInt(highScore1)) {
            String name = JOptionPane.showInputDialog("You make into Top 3! Please enter you name");
            name = name.replace(" ", "");
            if (score > convertToInt(highScore2)) {
                highScore3 = highScore2;
                highScore2 = name + " " + score;
            } else {
                highScore3 = name + " " + score;
            }
        }
        else if(score > convertToInt(highScore1)){
            String name = JOptionPane.showInputDialog("You beat the high score! Please enter you name");
            name = name.replace(" ", "");
            highScore3 = highScore2;
            highScore2 = highScore1;
            highScore1 = name + " " + score;
        }

        File scoreFile = new File("src/main/java/brickdestroy/resources/highScore.txt");
        if(!scoreFile.exists()) {
            try {
                scoreFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        FileWriter writeFile;
        BufferedWriter writer = null;
        try {
            writeFile = new FileWriter(scoreFile);
            writer = new BufferedWriter(writeFile);
            writer.write(this.highScore1 + "\n");
            writer.write(this.highScore2 + "\n");
            writer.write(this.highScore3);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (writer != null)
                    writer.close();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * Convert string to int
     *
     * @param highScore current high score with player's name
     * @return integer form of high score
     */
    public int convertToInt(String highScore){
        return Integer.parseInt(highScore.split(" ")[1]);
    }

    public int getHighScore() {
        highScore = convertToInt(highScore1);
        return highScore;
    }
}
