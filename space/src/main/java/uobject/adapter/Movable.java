package uobject.adapter;

import java.util.List;

public interface Movable {

    /**
     * @return положение объекта
     */
    List<Integer> getPosition();

    /**
     * @param newVector новое положение объекта
     */
    void setPosition(List<Integer> newVector);

    /**
     * @return скорость объекта
     */
    double getVelocity();

    /**
     * @return угол скорости объекта
     */
    double getAngular();
}
