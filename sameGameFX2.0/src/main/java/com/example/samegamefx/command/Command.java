package com.example.samegamefx.command;

public interface Command {
    /**
     * Executes the com.example.samegamefx.command
     */
    void execute();

    /**
     * Undoes the specified com.example.samegamefx.command
     */
    void undo();
}

