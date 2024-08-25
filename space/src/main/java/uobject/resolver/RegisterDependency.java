package uobject.resolver;

import uobject.command.Command;

import java.util.Map;
import java.util.function.Function;

public class RegisterDependency implements Command {

    private final String dependency;
    private final Function<Object[], Object> func;

    public RegisterDependency(String dependency, Function<Object[], Object> func) {
        this.dependency = dependency;
        this.func = func;
    }

    @Override
    public void execute() {
        var currentScope = IoC.<Map<String, Function<Object[], Object>>>resolve("IoC.Scope.Current");
        currentScope.put(dependency, func);
    }
}
