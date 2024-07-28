package uobject.adapter;

import uobject.model.Property;
import uobject.model.UObject;

public class RotatableAdapter implements Rotatable {

    private final UObject o;

    public RotatableAdapter(UObject o) {
        this.o = o;
    }

    /**
     * @return текущий угол поворота объекта
     */
    @Override
    public int getDirection() {
        return (int) o.getProperty(Property.DIRECTION.getKey());
    }

    /**
     * установить угол поворота объекта
     */
    @Override
    public void setDirection(int direction) {
        o.setProperty(Property.DIRECTION.getKey(), direction);
    }

    /**
     * @return скорость поворота объекта
     */
    @Override
    public int getAngularVelocity() {
        return (int) o.getProperty(Property.ANGULAR_VELOCITY.getKey());
    }

    /**
     * @return количество возможных направлений
     */
    @Override
    public int getDirectionsNumber() {
        return (int) o.getProperty(Property.DIRECTIONS_NUMBER.getKey());
    }
}
