package com.example.samegamefx.controller;

import com.example.samegamefx.command.CommandBuild;
import com.example.samegamefx.model.Board;
import com.example.samegamefx.model.Difficulty;
import com.example.samegamefx.model.Facade;
import com.example.samegamefx.view.JavaFX.ViewFX;
import com.example.samegamefx.view.View;

public class Controller {

    private Facade facade;
    private View view;

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
        facade.initCommand(facade.getBoard());
        Difficulty level = view.askingLevelColor();
        facade.startGame(level);
        while (!facade.gameIsFinish() && !quit){
            if (facade.getScore() == 0) {
                view.paintCase();
            }
            String decision = view.askingPlayer();
            if (decision.equals("undo") || decision.equals("redo")) {
                facade.getCommand().runCommand(decision);
            } else if (decision.equals("continue")) {
                int y = view.askPosition("x") - 1;
                int x = view.askPosition("y") - 1;
                facade.getCommand().playAShot(x,y);
            } else if (decision.equals("quit")) {
                quit = true;
            }
            view.score();
            view.paintCase();
        }
        view.endGame();
    }
}
