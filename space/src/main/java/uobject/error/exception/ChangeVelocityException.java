package uobject.error.exception;

import uobject.command.Command;

public class ChangeVelocityException extends CommandException {

    public ChangeVelocityException(String mess, Command command, Throwable throwable) {
        super(mess, command, throwable);
    }

    public ChangeVelocityException(Command command, Throwable throwable) {
        super(command, throwable);
    }

    public ChangeVelocityException(Command command) {
        super(command);
    }
}
