package uobject.action;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import uobject.adapter.MovableAdapter;
import uobject.model.Property;
import uobject.model.UObjectImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


class MoveTest {


    @Test
    @DisplayName("Для объекта, находящегося в точке (12, 5) и движущегося со скоростью (-7, 3) движение меняет положение объекта на (5, 8)")
    void setPositionToTarget() {
        Map<String, Object> properties = new HashMap<>();
        properties.put(Property.POSITION.getKey(), new ArrayList<>(List.of(12, 5)));
        properties.put(Property.VELOCITY.getKey(), -1 * Math.sqrt(58));
        properties.put(Property.ANGULAR.getKey(), 5.9);

        var ship = new UObjectImpl(properties);
        var adapter = new MovableAdapter(ship);
        var move = new Move(adapter);

        move.execute();

        assertEquals(List.of(5, 8), adapter.getPosition());
    }

    @Test
    @DisplayName("Попытка сдвинуть объект, у которого невозможно прочитать положение в пространстве, приводит к ошибке")
    void moveWithoutPositionThrows() {
        var undefinedObject = new UObjectImpl(new HashMap<>());
        var move = new Move(new MovableAdapter(undefinedObject));
        assertThrows(RuntimeException.class, move::execute);
    }

    @Test
    @DisplayName("Попытка сдвинуть объект, у которого невозможно прочитать значение мгновенной скорости, приводит к ошибке")
    void moveWithoutVelocityThrows() {
        Map<String, Object> properties = new HashMap<>();
        properties.put(Property.POSITION.getKey(), new ArrayList<>(List.of(0, 0)));
        var undefinedObject = new UObjectImpl(properties);

        var move = new Move(new MovableAdapter(undefinedObject));

        assertThrows(RuntimeException.class, move::execute);
    }

    @Test
    @DisplayName("Попытка сдвинуть объект, у которого невозможно изменить положение в пространстве, приводит к ошибке")
    void moveFailThrows() {
        var mockAdapter = Mockito.mock(MovableAdapter.class);
        Mockito.when(mockAdapter.getPosition()).thenReturn(List.of(1, 1));
        Mockito.when(mockAdapter.getVelocity()).thenReturn(2.5);
        var move = new Move(mockAdapter);

        assertThrows(RuntimeException.class, move::execute);
    }
}