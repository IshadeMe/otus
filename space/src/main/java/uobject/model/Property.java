package uobject.model;

public enum Property {

    POSITION("position"),
    VELOCITY("velocity"),
    ANGULAR("angular"),
    DIRECTION("direction"),
    ANGULAR_VELOCITY("angularVelocity"),
    DIRECTIONS_NUMBER("directionsNumber");

    private final String key;

    Property(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}
