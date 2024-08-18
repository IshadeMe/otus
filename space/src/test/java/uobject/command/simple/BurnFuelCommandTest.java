package uobject.command.simple;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import uobject.adapter.FuelAdapterImpl;
import uobject.error.exception.BurnFuelException;
import uobject.model.Property;
import uobject.model.UObjectImpl;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BurnFuelCommandTest {

    @Test
    void executeTest() {
        Map<String, Object> properties = new HashMap<>();
        properties.put(Property.FUEL_AMOUNT.getKey(), 10);
        properties.put(Property.FUEL_TICK_BURN.getKey(), 10);
        var ship = new UObjectImpl(properties);
        var adapter = new FuelAdapterImpl(ship);
        var command = new BurnFuelCommand(adapter);

        command.execute();
        assertEquals(0, adapter.getRemainingFuel());
    }

    @Test
    void doThrow() {
        var mockAdapter = Mockito.mock(FuelAdapterImpl.class);
        Mockito.when(mockAdapter.getRemainingFuel()).thenReturn(10);
        Mockito.when(mockAdapter.getFuelPerBurn()).thenReturn(5);
        var command = new BurnFuelCommand(mockAdapter);

        assertThrows(BurnFuelException.class, command::execute);
    }

}