package model;

public class Facade {

    Case board;

    public void dimensionCase(int x, int y) {
        board = new Case(x, y);
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

    public Case getBoard() {
        return board;
    }

    public ballColored[][] getTableau() {
        return board.getTableau();
    }

    public int getScore() {
        return board.getScore();
    }
}
