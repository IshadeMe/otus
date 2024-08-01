package uobject.model;

public interface UObject {

    /**
     * @param key название property
     * @return значение property
     */
    Object getProperty(String key) throws IllegalArgumentException;

    /**
     * @param key      название property
     * @param newValue новое значение property
     */
    void setProperty(String key, Object newValue) throws IllegalArgumentException;
}
