package uobject.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class UObjectImpl implements UObject {

    private final Map<String, Object> properties;

    public UObjectImpl(Map<String, Object> properties) {
        this.properties = new HashMap<>(properties);
    }

    /**
     * @param key название property
     * @return значение property
     */
    @Override
    public Object getProperty(String key) throws IllegalArgumentException {
        return Optional.of(properties.get(key)).orElseThrow(IllegalArgumentException::new);
    }

    /**
     * @param key      название property
     * @param newValue значение property
     */
    @Override
    public void setProperty(String key, Object newValue) throws IllegalArgumentException {
        if (null == properties.computeIfPresent(key, (k, v) -> newValue)) {
            throw new IllegalArgumentException();
        }
    }
}
