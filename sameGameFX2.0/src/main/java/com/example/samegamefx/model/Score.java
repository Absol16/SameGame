package com.example.samegamefx.model;

public class Score {
    private int score;
    private int countScore;

    /**
     * Constructor of Score
     */
    public Score() {
        this.score = 0;
        this.countScore=0;
    }

    /**
     * method that add point on the score
     * @param point
     */
    public void addPoint(int point){
        score += point;
    }

    /**
     * get the score
     * @return the score
     */
    public int getScore() {
        return score;
    }

    /**
     * get the point of the shot
     * @return the count score
     */
    public int getCountScore() {
        return countScore;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setCountScore(int score) {
        this.countScore = score;
    }
}
