package brickdestroy.view;

import java.text.DecimalFormat;
import java.util.TimerTask;
import java.util.Timer;

/**
 * A game timer class that makes a countdown timer in rank mode.
 */
public class GameTimer {

    private int minute;
    private int second;

    private String ddSecond;
    private String ddMinute;
    private Timer timer;
    private TimerTask timerTask;

    private boolean gameStatus;

    private DecimalFormat dFormat;

    /**
     * A game timer constructor that counts down the time if the game is running in every second.
     */
    public GameTimer() {
        second = 0;
        minute = 5;
        gameStatus = false;
        dFormat = new DecimalFormat("00");
        ddSecond = dFormat.format(second);
        ddMinute = dFormat.format(minute);

        timer = new Timer();
        timerTask = new TimerTask() {
            @Override
            public void run() {
                if (getGameStatus()) {
                    second--;
                    ddSecond = dFormat.format(second);
                    ddMinute = dFormat.format(minute);

                    if (second == -1) {
                        second = 59;
                        minute--;
                        ddSecond = dFormat.format(second);
                        ddMinute = dFormat.format(minute);
                    }
                }
            }
        };
        timer.schedule(timerTask, 0, 1000);
    }



    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public int getSecond() {
        return second;
    }

    public void setSecond(int second) {
        this.second = second;
    }

    /**
     * Gets the second in decimal format.
     *
     * @return second in decimal format
     */
    public String getDdSecond() {
        return ddSecond;
    }

    /**
     * Gets the minute in decimal format.
     *
     * @return minute in decimal format
     */
    public String getDdMinute() {
        return ddMinute;
    }

    public void setGameStatus(boolean gameStatus) {
        this.gameStatus = gameStatus;
    }

    /**
     * Gets the game status. (either is gaming or not)
     * @return
     */
    public boolean getGameStatus(){
        return gameStatus;
    }

    /**
     * Resets the timer to 5 minutes.
     */
    public void resetTimer(){
        second = 0;
        minute = 5;
    }
}
