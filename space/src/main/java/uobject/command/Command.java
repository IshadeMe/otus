package uobject.command;

import uobject.error.handler.CommandExceptionHandler;

public interface Command {
    void execute();

    default void execute(CommandExceptionHandler handler) {
        try {
            execute();
        } catch (Exception e) {
            handler.handle(this, e).execute();
        }
    }

}
