package controller;

import command.commandBuild;
import model.Facade;
import view.View;

public class controller {

    private Facade facade;
    private View view;
    private commandBuild commandBuilder;

    /**
     * Method that start the game.
     */
    public void Game() {
        boolean quit = false;
        facade = new Facade();
        view = new View(facade);
        view.sameGameTitle();
        int H = view.dimensionGame("Height");
        int W = view.dimensionGame("Width");
        facade.dimensionCase(H, W);
        String numberColor = view.askingLevelColor();
        facade.StartGame(numberColor);
        commandBuilder = new commandBuild(facade.getBoard());
        while (!facade.GameIsFinish() && !quit) {
            if (facade.getScore() == 0) {
                view.PaintCase();
            }
            String decision = view.askingPlayer();
            if (decision.equals("undo") || decision.equals("redo")) {
                commandBuilder.runCommand(decision);
            } else if (decision.equals("continue")) {
                int x = view.askPosition("x") - 1;
                int y = view.askPosition("y") - 1;
                commandBuilder.playAShot(x, y);
            } else if (decision.equals("quit")) {
                quit = true;
            }
            view.Score();
            view.PaintCase();
        }
        view.endGame();
    }

    public static void main(String[] args) {
        controller sameGame = new controller();
        sameGame.Game();
    }
}