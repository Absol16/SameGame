package com.example.samegamefx.command;

import com.example.samegamefx.model.Board;

import java.util.Stack;

public class CommandBuild {
    private final Board game;
    private final Stack<Command> historyCommand = new Stack<>();
    private final Stack<Command> redoCommand = new Stack<>();
    private final Stack<Command> resetCommand = new Stack<>();

    public CommandBuild(Board game) {
        this.game = game;
    }

    private void executeCommand(Command command) {
        if(historyCommand.size()==0 || game.getScore()<1){
            resetCommand.push(command);
        }
        command.execute();
        if (game.getCountScore() > 1) {
            historyCommand.push(command);
            redoCommand.clear();
        }
    }

    /**
     * Method that undo or redo depending on the player's request.
     *
     * @param command : the command of the player
     */
    public void runCommand(String command) {
        switch (command) {
            case "undo" -> undo();
            case "redo" -> redo();
        }
    }

    /**
     * Play a new Shot and execute.
     *
     * @param x position x of the ball
     * @param y position y of the ball
     */
    public void playAShot(int x, int y) {
        Command playAShot = new PlayShot(x, y, game);
        executeCommand(playAShot);
    }

    /**
     * Undoes the last command.
     */
    public void undo() {
        if (historyCommand.size() != 0) {
            Command commandToUndo = historyCommand.pop();
            commandToUndo.undo();
            redoCommand.push(commandToUndo);
        }
    }

    /**
     * Redoes the last command.
     */
    public void redo() {
        if (redoCommand.size() != 0) {
            Command commandToRedo = redoCommand.pop();
            commandToRedo.execute();
            historyCommand.push(commandToRedo);
        }
    }

    public void clearStack(){
        redoCommand.clear();
        historyCommand.clear();
    }

    public void reset() {
        if (resetCommand.size() != 0) {
            Command commandToReset = resetCommand.pop();
            resetCommand.push(commandToReset);
            commandToReset.undo();
        }
    }
}

