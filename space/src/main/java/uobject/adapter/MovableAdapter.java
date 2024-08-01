package uobject.adapter;

import uobject.model.UObject;

import java.util.List;

import static uobject.model.Property.ANGULAR;
import static uobject.model.Property.POSITION;
import static uobject.model.Property.VELOCITY;

public class MovableAdapter implements Movable {

    private final UObject o;


    public MovableAdapter(UObject o) {
        this.o = o;
    }

    /**
     * @return положение объекта
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<Integer> getPosition() {
        return (List<Integer>) o.getProperty(POSITION.getKey());
    }

    /**
     * @return модуль скорости объекта
     */
    @Override
    @SuppressWarnings("unchecked")
    public double getVelocity() {
        return (double) o.getProperty(VELOCITY.getKey());
    }

    /**
     * @return угол скорости объекта
     */
    public double getAngular() {
        return (double) o.getProperty(ANGULAR.getKey());

    }

    /**
     * @param newVector новое положение объекта
     */
    @Override
    public void setPosition(List<Integer> newVector) {
        o.setProperty(POSITION.getKey(), newVector);
    }
}
