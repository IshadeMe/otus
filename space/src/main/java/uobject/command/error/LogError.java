package uobject.command.error;

import uobject.command.Command;

import java.util.logging.Logger;

public class LogError implements Command {

    private final Command command;
    private final Exception exception;

    public LogError(Command command, Exception exception) {
        this.command = command;
        this.exception = exception;
    }

    @Override
    public void execute() {
        Logger.getLogger(this.getClass().getName()).severe(
                () -> String.format("%s throws exception %s", command.getClass().getName(), exception.getClass().getName())
        );
        exception.printStackTrace();
    }
}
