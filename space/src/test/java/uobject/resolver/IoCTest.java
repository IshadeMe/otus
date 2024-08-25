package uobject.resolver;

import org.junit.jupiter.api.Test;
import uobject.adapter.MovableAdapter;
import uobject.command.Command;
import uobject.command.simple.MoveCommand;
import uobject.model.UObject;
import uobject.model.UObjectImpl;

import java.util.HashMap;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


class IoCTest {

    @Test
    void resolveCommand() {
        var scope = IoC.resolve("IoC.Scope.New");
        IoC.<Command>resolve("IoC.Scope.Current.Set", scope).execute();
        IoC.<Command>resolve(
                        "IoC.Register",
                        "Adapter.Movable",
                        (Function<Object[], ?>) (Object[] args) -> new MovableAdapter((UObject) args[0])
                )
                .execute();
        IoC.<Command>resolve(
                        "IoC.Register",
                        "Command.Move",
                        (Function<Object[], ?>) (Object[] args) -> new MoveCommand(IoC.resolve("Adapter.Movable", args[0]))
                )
                .execute();
        var command = IoC.<Command>resolve("Command.Move", new UObjectImpl(new HashMap<>()));
        assertEquals(MoveCommand.class, command.getClass());
    }

    @Test
    void emptyScope() {
        var scope = IoC.resolve("IoC.Scope.New");
        IoC.<Command>resolve("IoC.Scope.Current.Set", scope).execute();
        assertThrows(RuntimeException.class, () -> IoC.<Command>resolve("Command.Move", new UObjectImpl(new HashMap<>())));
    }

    @Test
    void nestedScope() {
        var scope = IoC.resolve("IoC.Scope.New");
        IoC.<Command>resolve("IoC.Scope.Current.Set", scope).execute();
        IoC.<Command>resolve(
                        "IoC.Register",
                        "Adapter.Movable",
                        (Function<Object[], ?>) (Object[] args) -> new MovableAdapter((UObject) args[0])
                )
                .execute();
        var scope_2 = IoC.resolve("IoC.Scope.New", scope);
        IoC.<Command>resolve("IoC.Scope.Current.Set", scope_2).execute();
        IoC.<Command>resolve(
                        "IoC.Register",
                        "Command.Move",
                        (Function<Object[], ?>) (Object[] args) -> new MoveCommand(IoC.resolve("Adapter.Movable", args[0]))
                )
                .execute();
        var scope_3 = IoC.resolve("IoC.Scope.New", scope_2);
        IoC.<Command>resolve("IoC.Scope.Current.Set", scope_3).execute();
        var command = IoC.<Command>resolve("Command.Move", new UObjectImpl(new HashMap<>()));
        assertEquals(MoveCommand.class, command.getClass());
    }


}