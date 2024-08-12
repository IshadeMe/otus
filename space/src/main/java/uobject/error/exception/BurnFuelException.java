package uobject.error.exception;

import uobject.command.Command;

public class BurnFuelException extends CommandException {

    public BurnFuelException(String mess, Command command, Throwable throwable) {
        super(mess, command, throwable);
    }

    public BurnFuelException(Command command, Throwable throwable) {
        super(command, throwable);
    }

    public BurnFuelException(Command command) {
        super(command);
    }
}
