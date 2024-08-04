package uobject.command;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import uobject.adapter.RotatableAdapter;
import uobject.command.game.Rotate;
import uobject.model.Property;
import uobject.model.UObjectImpl;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RotateTest {

    @Test
    @DisplayName("Для объекта, находящегося под углом 180 и движущегося с угловой скоростью 60 движение меняет угол объекта на 240")
    void setPositionToTarget() {
        Map<String, Object> properties = new HashMap<>();
        properties.put(Property.DIRECTION.getKey(), 180);
        properties.put(Property.DIRECTIONS_NUMBER.getKey(), 360);
        properties.put(Property.ANGULAR_VELOCITY.getKey(), 60);

        var ship = new UObjectImpl(properties);
        var adapter = new RotatableAdapter(ship);
        var move = new Rotate(adapter);

        move.execute();

        assertEquals(240, adapter.getDirection());
    }

    @Test
    @DisplayName("Для объекта, находящегося под углом 180 и движущегося с угловой скоростью 50 движение меняет угол " +
            "объекта на 225 при 24 направлениях")
    void tmp() {
        Map<String, Object> properties = new HashMap<>();
        properties.put(Property.DIRECTION.getKey(), 180);
        properties.put(Property.DIRECTIONS_NUMBER.getKey(), 24);
        properties.put(Property.ANGULAR_VELOCITY.getKey(), 50);

        var ship = new UObjectImpl(properties);
        var adapter = new RotatableAdapter(ship);
        var move = new Rotate(adapter);

        move.execute();

        assertEquals(225, adapter.getDirection());
    }

    @Test
    @DisplayName("Не происходит переполнения")
    void setPositionToCircle() {
        Map<String, Object> properties = new HashMap<>();
        properties.put(Property.DIRECTION.getKey(), 180);
        properties.put(Property.DIRECTIONS_NUMBER.getKey(), 360);
        properties.put(Property.ANGULAR_VELOCITY.getKey(), 180);

        var ship = new UObjectImpl(properties);
        var adapter = new RotatableAdapter(ship);
        var move = new Rotate(adapter);

        move.execute();
        move.execute();

        assertEquals(180, adapter.getDirection());
    }

    @Test
    @DisplayName("Попытка сдвинуть объект, у которого невозможно прочитать угол, приводит к ошибке")
    void moveWithoutPositionThrows() {
        var undefinedObject = new UObjectImpl(new HashMap<>());
        var move = new Rotate(new RotatableAdapter(undefinedObject));
        assertThrows(RuntimeException.class, move::execute);
    }

    @Test
    @DisplayName("Попытка сдвинуть объект, у которого невозможно прочитать значение угловой скорости, приводит к ошибке")
    void moveWithoutVelocityThrows() {
        Map<String, Object> properties = new HashMap<>();
        properties.put(Property.DIRECTION.getKey(), 180);
        properties.put(Property.DIRECTIONS_NUMBER.getKey(), 360);
        var undefinedObject = new UObjectImpl(properties);

        var move = new Rotate(new RotatableAdapter(undefinedObject));

        assertThrows(RuntimeException.class, move::execute);
    }


    @Test
    @DisplayName("Попытка сдвинуть объект, у которого невозможно изменить положение в пространстве, приводит к ошибке")
    void moveFailThrows() {
        var mockAdapter = Mockito.mock(RotatableAdapter.class);
        Mockito.when(mockAdapter.getDirection()).thenReturn(45);
        var move = new Rotate(mockAdapter);

        assertThrows(RuntimeException.class, move::execute);
    }
}