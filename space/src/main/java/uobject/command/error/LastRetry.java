package uobject.command.error;


import uobject.command.Command;

public class LastRetry extends Retry implements Command {

    public LastRetry(Command command) {
        super(command);
    }
}
