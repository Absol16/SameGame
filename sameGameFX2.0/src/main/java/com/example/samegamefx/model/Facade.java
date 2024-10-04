package com.example.samegamefx.model;

import com.example.samegamefx.command.CommandBuild;

public class Facade {

    private Board board;
    private CommandBuild command;

    public Facade() {
    }

    public void dimensionCase(int x, int y) {
        board = new Board(x, y);
    }

    public void initCommand(Board board){
        command = new CommandBuild(board);
    }

    public void startGame(Difficulty level) {
        board.start(level);
    }

    public int getWidth() {
        return board.getWidth();
    }

    public int getHeight() {
        return board.getHeight();
    }

    public boolean gameIsFinish() {
        return board.gameIsFinish();
    }

    public Board getBoard() {
        return board;
    }

    public CommandBuild getCommand(){return command;}

    public ColoredBall[][] getTableau() {
        return board.getBoard();
    }

    public int getScore() {
        return board.getScore();
    }
}
