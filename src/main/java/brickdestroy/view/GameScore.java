package brickdestroy.view;

/**
 * A game score class that is responsible to reset the game score in rank mode.
 */
public class GameScore {

    public static int score;

    public GameScore(){
        score = 0;
    }


    public void resetScore(){
        score = 0;
    }

}
