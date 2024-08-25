package uobject.resolver;

import uobject.command.Command;

import java.util.Map;
import java.util.function.Function;

public class SetCurrentScope implements Command {

    private final Map<String, Function<Object[], Object>> scope;

    public SetCurrentScope(Map<String, Function<Object[], Object>> scope) {
        this.scope = scope;
    }

    @Override
    public void execute() {
        IoC.currentScope.set(scope);
    }
}
