package com.example.samegamefx.model;

public class ballColored implements ball {
    private String color;
    private int x;
    private int y;

    /**
     * Constructor of ballColored.
     *
     * @param x     : number of the axe x.
     * @param y     : number of the axe y.
     * @param color : the color of the ball.
     */
    public ballColored(int x, int y, String color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }

    @Override
    public void delete() {
        this.color = "◼️";
    }

    @Override
    public String getColor() {
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
    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public int getY() {
        return y;
    }
}

