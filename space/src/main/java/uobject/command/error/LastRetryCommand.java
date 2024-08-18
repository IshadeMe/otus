package uobject.command.error;


import uobject.command.Command;

public class LastRetryCommand extends RetryCommand implements Command {

    public LastRetryCommand(Command command) {
        super(command);
    }
}
