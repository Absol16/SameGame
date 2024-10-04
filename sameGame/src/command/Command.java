package command;

import model.Case;

public interface Command {
    /**
     * Executes the command
     */
    void execute();

    /**
     * Undoes the specified command
     */
    void undo();
}
