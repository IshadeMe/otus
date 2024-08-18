package uobject.error.handler;

import uobject.command.Command;

public interface CommandExceptionHandler {

    /**
     * @param command   команда, завершенная неудачно
     * @param exception исключение
     * @return
     */
    Command handle(Command command, Exception exception);
}
