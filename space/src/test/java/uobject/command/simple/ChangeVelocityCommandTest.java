package uobject.command.simple;


import org.junit.jupiter.api.Test;
import uobject.adapter.AccelerationAdapter;
import uobject.model.Property;
import uobject.model.UObjectImpl;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ChangeVelocityCommandTest {

    @Test
    void executeTest() {
        Map<String, Object> properties = new HashMap<>();
        properties.put(Property.ACCELERATION.getKey(), 2);
        properties.put(Property.VELOCITY.getKey(), 2);
        var ship = new UObjectImpl(properties);
        var adapter = new AccelerationAdapter(ship);
        var command = new ChangeVelocityCommand(adapter);

        command.execute();
        assertEquals(4, adapter.getVelocity());
    }

    @Test
    void doesntThrow() {
        Map<String, Object> properties = new HashMap<>();
        properties.put(Property.ACCELERATION.getKey(), 2);
        var ship = new UObjectImpl(properties);
        var adapter = new AccelerationAdapter(ship);
        var command = new ChangeVelocityCommand(adapter);

        assertDoesNotThrow(command::execute);
    }

}