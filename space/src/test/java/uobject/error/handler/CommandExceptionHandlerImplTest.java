package uobject.error.handler;


import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import uobject.adapter.MovableAdapter;
import uobject.command.Command;
import uobject.command.error.LastRetry;
import uobject.command.error.LogError;
import uobject.command.error.Retry;
import uobject.command.game.Move;

import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.assertSame;


class CommandExceptionHandlerImplTest {

    private CommandExceptionHandlerImpl handler;

    @Test
    void handleErrorLog() {
        handler = new CommandExceptionHandlerImpl();
        var response = handler.handle(new Move(null), new RuntimeException());
        assertSame(LogError.class, response.getClass());
    }

    @Test
    void retry() {
        var command = Mockito.mock(Command.class);
        var adapter = Mockito.mock(MovableAdapter.class);
        var exception = new RuntimeException();
        var move = new Move(adapter);

        handler = new CommandExceptionHandlerImpl(
                Map.of(
                        move.getClass(), Map.of(exception.getClass(), (c, e) -> new Retry(command))
                )
        );
        var response = handler.handle(new Move(null), new RuntimeException());
        assertSame(Retry.class, response.getClass());
    }

    @Test
    void retryAndLog() {
        handler = new CommandExceptionHandlerImpl(
                Map.of(
                        Move.class, Map.of(RuntimeException.class, (c, e) -> new Retry(c)),
                        Retry.class, Map.of(RuntimeException.class, LogError::new)
                )
        );
        var response = handler.handle(new Move(null), new RuntimeException());
        var resp = handler.handle(response, new RuntimeException());
        assertSame(Retry.class, response.getClass());
        assertSame(LogError.class, resp.getClass());
    }

    @Test
    void retryAndRetryAndLog() {
        handler = new CommandExceptionHandlerImpl(
                Map.of(
                        Move.class, Map.of(RuntimeException.class, (c, e) -> new Retry(c)),
                        Retry.class, Map.of(RuntimeException.class, (c, e) -> new LastRetry(c)),
                        LastRetry.class, Map.of(RuntimeException.class, LogError::new)
                )
        );
        var response = handler.handle(new Move(null), new RuntimeException());
        var resp = handler.handle(handler.handle(response, new RuntimeException()), new RuntimeException());
        assertSame(LogError.class, resp.getClass());
    }

}