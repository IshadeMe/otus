package uobject.error.exception;

import uobject.command.Command;

public class MacroCommandException extends CommandException {
    public MacroCommandException(String mess, Command command, Throwable throwable) {
        super(mess, command, throwable);
    }

    public MacroCommandException(Command command, Throwable throwable) {
        super(command, throwable);
    }

    public MacroCommandException(Command command) {
        super(command);
    }
}
