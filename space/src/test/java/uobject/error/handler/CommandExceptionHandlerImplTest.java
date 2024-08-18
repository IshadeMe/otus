package uobject.error.handler;


import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import uobject.adapter.MovableAdapter;
import uobject.command.Command;
import uobject.command.error.LastRetryCommand;
import uobject.command.error.LogErrorCommand;
import uobject.command.error.RetryCommand;
import uobject.command.simple.MoveCommand;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertSame;


class CommandExceptionHandlerImplTest {

    private CommandExceptionHandlerImpl handler;

    @Test
    void handleErrorLog() {
        handler = new CommandExceptionHandlerImpl();
        var response = handler.handle(new MoveCommand(null), new RuntimeException());
        assertSame(LogErrorCommand.class, response.getClass());
    }

    @Test
    void retry() {
        var command = Mockito.mock(Command.class);
        var adapter = Mockito.mock(MovableAdapter.class);
        var exception = new RuntimeException();
        var move = new MoveCommand(adapter);

        handler = new CommandExceptionHandlerImpl(
                Map.of(
                        move.getClass(), Map.of(exception.getClass(), (c, e) -> new RetryCommand(command))
                )
        );
        var response = handler.handle(new MoveCommand(null), new RuntimeException());
        assertSame(RetryCommand.class, response.getClass());
    }

    @Test
    void retryAndLog() {
        handler = new CommandExceptionHandlerImpl(
                Map.of(
                        MoveCommand.class, Map.of(RuntimeException.class, (c, e) -> new RetryCommand(c)),
                        RetryCommand.class, Map.of(RuntimeException.class, LogErrorCommand::new)
                )
        );
        var response = handler.handle(new MoveCommand(null), new RuntimeException());
        var resp = handler.handle(response, new RuntimeException());
        assertSame(RetryCommand.class, response.getClass());
        assertSame(LogErrorCommand.class, resp.getClass());
    }

    @Test
    void retryAndRetryAndLog() {
        handler = new CommandExceptionHandlerImpl(
                Map.of(
                        MoveCommand.class, Map.of(RuntimeException.class, (c, e) -> new RetryCommand(c)),
                        RetryCommand.class, Map.of(RuntimeException.class, (c, e) -> new LastRetryCommand(c)),
                        LastRetryCommand.class, Map.of(RuntimeException.class, LogErrorCommand::new)
                )
        );
        var response = handler.handle(new MoveCommand(null), new RuntimeException());
        var resp = handler.handle(handler.handle(response, new RuntimeException()), new RuntimeException());
        assertSame(LogErrorCommand.class, resp.getClass());
    }

}