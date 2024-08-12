package uobject.command.simple;

import org.junit.jupiter.api.Test;
import uobject.adapter.FuelAdapterImpl;
import uobject.error.exception.CheckFuelException;
import uobject.model.Property;
import uobject.model.UObjectImpl;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;


class CheckFuelCommandTest {

    @Test
    void doesntThrow() {
        Map<String, Object> properties = new HashMap<>();
        properties.put(Property.FUEL_AMOUNT.getKey(), 10);
        properties.put(Property.FUEL_TICK_BURN.getKey(), 10);
        var ship = new UObjectImpl(properties);
        var adapter = new FuelAdapterImpl(ship);
        var command = new CheckFuelCommand(adapter);

        assertDoesNotThrow(command::execute);
    }

    @Test
    void doThrow() {
        Map<String, Object> properties = new HashMap<>();
        properties.put(Property.FUEL_AMOUNT.getKey(), 10);
        properties.put(Property.FUEL_TICK_BURN.getKey(), 11);
        var ship = new UObjectImpl(properties);
        var adapter = new FuelAdapterImpl(ship);
        var command = new CheckFuelCommand(adapter);

        assertThrows(CheckFuelException.class, command::execute);
    }

}