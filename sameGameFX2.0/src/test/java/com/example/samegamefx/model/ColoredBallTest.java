package com.example.samegamefx.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ColoredBallTest {

    @Test
    void deleteRed() {
        ColoredBall ball = new ColoredBall(1,2,ColorEnum.R);
        ball.delete();
        assertEquals(" ",ball.getColor());
    }
    @Test
    void deleteGreen() {
        ColoredBall ball = new ColoredBall(1,2,ColorEnum.G);
        ball.delete();
        assertEquals(" ",ball.getColor());
    }
    @Test
    void deleteBlue() {
        ColoredBall ball = new ColoredBall(1,2,ColorEnum.B);
        ball.delete();
        assertEquals(" ",ball.getColor());
    }
    @Test
    void deleteYellow() {
        ColoredBall ball = new ColoredBall(1,2,ColorEnum.Y);
        ball.delete();
        assertEquals(" ",ball.getColor());
    }
    @Test
    void deletePurple() {
        ColoredBall ball = new ColoredBall(1,2,ColorEnum.P);
        ball.delete();
        assertEquals(" ",ball.getColor());
    }

    @Test
    void getColor() {
        ColoredBall ball = new ColoredBall(1,2,ColorEnum.P);
        assertEquals(ball.getColor(),"P");
    }

    @Test
    void getX() {
        ColoredBall ball = new ColoredBall(1,2,ColorEnum.P);
        assertEquals(1,ball.getX());
    }

    @Test
    void getY() {
        ColoredBall ball = new ColoredBall(1,2,ColorEnum.P);
        assertEquals(2,ball.getY());
    }

    @Test
    void setColor() {
        ColoredBall ball = new ColoredBall(1,2,ColorEnum.P);
        ball.setColor(ColorEnum.B);
        assertEquals(ColorEnum.B,ball.getColor());
    }
}