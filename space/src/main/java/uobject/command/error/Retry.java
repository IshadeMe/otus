package uobject.command.error;

import uobject.command.Command;

public class Retry implements Command {

    private final Command command;

    public Retry(Command command) {
        this.command = command;
    }

    @Override
    public void execute() {
        command.execute();
    }
}
