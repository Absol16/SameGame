package com.example.samegamefx.model;

public class Facade {

    Board board;

    public void dimensionCase(int x, int y) {
        board = new Board(x, y);
    }

    public void StartGame(String numberColor) {
        board.Start(numberColor);
    }

    public int getWidth() {
        return board.getWidth();
    }

    public int getHeight() {
        return board.getHeight();
    }

    public boolean GameIsFinish() {
        return board.gameIsFinish();
    }

    public Board getBoard() {
        return board;
    }

    public ballColored[][] getTableau() {
        return board.getTableau();
    }

    public int getScore() {
        return board.getScore();
    }
}
