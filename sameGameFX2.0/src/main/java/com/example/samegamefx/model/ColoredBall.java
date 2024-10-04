package com.example.samegamefx.model;

public class ColoredBall implements BallColor {
    private ColorEnum color;
    private final int x;
    private final int y;

    /**
     * Constructor of BallColored.
     *
     * @param x     : number of the axe x.
     * @param y     : number of the axe y.
     * @param color : the color of the ball.
     */
    public ColoredBall(int x , int y , ColorEnum color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }

    @Override
    public void delete() {
        this.color = ColorEnum.NONE;
    }

    @Override
    public ColorEnum getColor() {
        return this.color;
    }

    @Override
    public int getX() {
        return x;
    }

    /**
     * Set a new color
     *
     * @param color
     */
    public void setColor(ColorEnum color) {
        this.color = color;
    }

    @Override
    public int getY() {
        return y;
    }
}

