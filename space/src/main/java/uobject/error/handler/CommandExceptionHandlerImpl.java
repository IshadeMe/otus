package uobject.error.handler;

import uobject.command.Command;
import uobject.command.error.LogErrorCommand;

import java.util.Collections;
import java.util.Map;
import java.util.function.BiFunction;

public class CommandExceptionHandlerImpl implements CommandExceptionHandler {

    private final Map<Class<? extends Command>, Map<Class<? extends Exception>, BiFunction<Command, Exception, Command>>> dict;


    public CommandExceptionHandlerImpl(Map<Class<? extends Command>, Map<Class<? extends Exception>, BiFunction<Command, Exception, Command>>> dict) {
        this.dict = dict;
    }

    public CommandExceptionHandlerImpl() {
        this(defaultDict());
    }

    private static Map<Class<? extends Command>, Map<Class<? extends Exception>, BiFunction<Command, Exception, Command>>> defaultDict() {
        return Map.ofEntries(

        );
    }

    /**
     * @param command   команда, завершенная неудачно
     * @param exception исключение
     * @return новую команду
     */
    @Override
    public Command handle(Command command, Exception exception) {
        return dict.getOrDefault(command.getClass(), Collections.emptyMap())
                .getOrDefault(exception.getClass(), defaultLoggingCommand(command, exception))
                .apply(command, exception);
    }

    private BiFunction<Command, Exception, Command> defaultLoggingCommand(Command command, Exception exception) {
        return (c, e) -> new LogErrorCommand(command, exception);
    }

}
