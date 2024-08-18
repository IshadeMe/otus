package uobject.model;

public enum Property {

    POSITION("position"),
    VELOCITY("velocity"),
    ANGULAR("angular"),
    DIRECTION("direction"),
    ANGULAR_VELOCITY("angularVelocity"),
    DIRECTIONS_NUMBER("directionsNumber"),

    FUEL_AMOUNT("fuelAmount"),
    FUEL_TICK_BURN("burnFuelCount"),

    ACCELERATION("acceleration");

    private final String key;

    Property(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}
