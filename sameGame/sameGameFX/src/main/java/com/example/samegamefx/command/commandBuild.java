package com.example.samegamefx.command;

import com.example.samegamefx.model.Board;

import java.util.Stack;

public class commandBuild {
    private Board game;
    private Stack<Command> historyCommand = new Stack<>();
    private Stack<Command> redoCommand = new Stack<>();

    public commandBuild(Board game) {
        this.game = game;
    }

    private void executeCommand(Command command) {
        command.execute();
        if (game.getNbColorDelete() > 1) {
            historyCommand.push(command);
            redoCommand.clear();
        }
    }

    /**
     * Method that undo or redo depending on the player's request.
     *
     * @param command : the com.example.samegamefx.command of the player
     */
    public void runCommand(String command) {
        switch (command) {
            case "undo":
                undo();
                break;
            case "redo":
                redo();
                break;
        }
    }

    /**
     * Play a new Shot and execute.
     *
     * @param x
     * @param y
     */
    public void playAShot(int x, int y) {
        Command playAShot = new playAShot(x, y, game);
        executeCommand(playAShot);
    }

    /**
     * Undoes the last com.example.samegamefx.command.
     */
    public void undo() {
        if (historyCommand.size() != 0) {
            Command commandToUndo = historyCommand.pop();
            commandToUndo.undo();
            redoCommand.push(commandToUndo);
        }
    }

    /**
     * Redoes the last com.example.samegamefx.command.
     */
    public void redo() {
        if (redoCommand.size() != 0) {
            Command commandToRedo = redoCommand.pop();
            commandToRedo.execute();
            historyCommand.push(commandToRedo);
        }
    }
}

