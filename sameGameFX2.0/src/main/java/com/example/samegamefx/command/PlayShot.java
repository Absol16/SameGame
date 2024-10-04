package com.example.samegamefx.command;
import com.example.samegamefx.model.Board;
import com.example.samegamefx.model.ColoredBall;

public class PlayShot implements Command {
    private Board board;
    private int x;
    private int y;
    private ColoredBall[][] lastBoard;
    private int lastScore;

    /**
     * Constructor of PlayAShot
     * @param x
     * @param y
     * @param board
     */
    public PlayShot(int x, int y, Board board) {
        this.board = board;
        this.x = x;
        this.y = y;
        this.lastBoard = this.board.getCopyBoard();
        this.lastScore = this.board.getScore();
    }

    @Override
    public void execute() {
        board.deleteBall(x, y);
    }

    @Override
    public void undo() {
        board.setBoard(lastBoard);
        board.setScore(lastScore);
    }
}
