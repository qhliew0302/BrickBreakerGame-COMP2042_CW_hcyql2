package brickdestroy;

public class GameScore {

    private int score;

    public GameScore(){
        score = 0;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void resetScore(){
        score = 0;
    }

}
