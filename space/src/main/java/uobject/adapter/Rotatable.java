package uobject.adapter;

public interface Rotatable {

    /**
     * @return текущий угол поворота объекта
     */
    int getDirection();

    /**
     * установить угол поворота объекта
     */
    void setDirection(int direction);

    /**
     * @return скорость поворота объекта
     */
    int getAngularVelocity();

    /**
     * @return количество возможных направлений
     */
    int getDirectionsNumber();
}
