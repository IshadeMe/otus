package uobject.error.exception;

import uobject.command.Command;

public class CommandException extends RuntimeException {
    private final Command command;
    private final Throwable throwable;

    public CommandException(String mess, Command command, Throwable throwable) {
        super(mess);
        this.command = command;
        this.throwable = throwable;
    }

    public CommandException(Command command, Throwable throwable) {
        super();
        this.command = command;
        this.throwable = throwable;
    }

    public CommandException(Command command) {
        this(command, null);
    }


}
