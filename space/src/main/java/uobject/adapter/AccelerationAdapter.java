package uobject.adapter;

import uobject.model.Property;
import uobject.model.UObject;

public class AccelerationAdapter implements Acceleration {

    private final UObject uObject;

    public AccelerationAdapter(UObject uObject) {
        this.uObject = uObject;
    }

    @Override
    public int getAcceleration() {
        return (int) uObject.getProperty(Property.ACCELERATION.getKey());
    }

    @Override
    public int getVelocity() {
        return (int) uObject.getProperty(Property.VELOCITY.getKey());
    }

    @Override
    public void setVelocity(int velocity) {
        uObject.setProperty(Property.VELOCITY.getKey(), velocity);
    }
}
