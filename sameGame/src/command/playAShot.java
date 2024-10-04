package command;

import model.Case;
import model.ballColored;

public class playAShot implements Command {
    private Case aCase;
    private int x;
    private int y;
    private ballColored[][] lastBoard;
    private int lastScore;

    /**
     * Constructor of playAShot
     *
     * @param x
     * @param y
     * @param aCase
     */
    public playAShot(int x, int y, Case aCase) {
        this.aCase = aCase;
        this.x = x;
        this.y = y;
        this.lastBoard = this.aCase.getCopyBoard();
        this.lastScore = this.aCase.getScore();
    }

    @Override
    public void execute() {
        aCase.deleteBall(y, x);
    }

    @Override
    public void undo() {
        aCase.setTableau(lastBoard);
        aCase.setScore(lastScore);
    }
}