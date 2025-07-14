package uobject.resolver;


import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

@SuppressWarnings("unchecked")
public class IoC {

    static ThreadLocal<Object> currentScope = new ThreadLocal<>();

    private static final Map<String, Function<Object[], Object>> root = new ConcurrentHashMap<>();

    static {
        root.put("IoC.Register", (Object[] args) -> new RegisterDependency((String) args[0], (Function<Object[], Object>) args[1]));
        root.put("IoC.Scope.Current", (Object[] args) -> null != currentScope.get() ? currentScope.get() : root);
        root.put("IoC.Scope.Current.Set", (Object[] args) -> new SetCurrentScope((Map<String, Function<Object[], Object>>) args[0]));
        root.put("IoC.Scope.Parent", (Object[] args) -> null);
        root.put("IoC.Scope.New", (Object[] args) -> {
                    var scope = new ConcurrentHashMap<>();
                    scope.put("IoC.Scope.Parent", args.length > 0 ? args[0] : IoC.resolve("IoC.Scope.Current"));
                    return scope;
                }
        );

    }

    public static <T> T resolve(String key, Object... params) {
        Map<?, ?> scope = null != currentScope.get() ? (Map<String, Object>) currentScope.get() : root;
        var func = scope.get(key);
        while (null == func) {
            if (scope == root) {
                throw new RuntimeException();
            }
            scope = (Map<?, ?>) scope.get("IoC.Scope.Parent");
            func = scope.get(key);
        }
        var response = (Function<Object[], Object>) func;
        return (T) response.apply(params);
    }

}
