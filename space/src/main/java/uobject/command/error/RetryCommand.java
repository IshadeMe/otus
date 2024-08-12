package uobject.command.error;

import uobject.command.Command;

public class RetryCommand implements Command {

    private final Command command;

    public RetryCommand(Command command) {
        this.command = command;
    }

    @Override
    public void execute() {
        command.execute();
    }
}
