package brickdestroy;

public class GameScore {

    private int score;

    public GameScore(){
        score = getScore();
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

}