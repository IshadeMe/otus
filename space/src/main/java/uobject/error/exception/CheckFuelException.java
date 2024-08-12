package uobject.error.exception;

import uobject.command.Command;

public class CheckFuelException extends CommandException {

    public CheckFuelException(String mess, Command command, Throwable exception) {
        super(mess, command, exception);
    }

    public CheckFuelException(Command command, Throwable exception) {
        super(command, exception);
    }

    public CheckFuelException(Command command) {
        super(command);
    }
}
