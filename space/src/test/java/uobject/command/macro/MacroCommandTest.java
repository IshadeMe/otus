package uobject.command.macro;


import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import uobject.adapter.FuelAdapterImpl;
import uobject.command.Command;
import uobject.command.simple.BurnFuelCommand;
import uobject.command.simple.CheckFuelCommand;
import uobject.error.exception.CommandException;
import uobject.error.exception.MacroCommandException;
import uobject.model.Property;
import uobject.model.UObjectImpl;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


class MacroCommandTest {

    @Test
    void execute() {
        Map<String, Object> properties = new HashMap<>();
        properties.put(Property.FUEL_AMOUNT.getKey(), 10);
        properties.put(Property.FUEL_TICK_BURN.getKey(), 10);
        var ship = new UObjectImpl(properties);
        var adapter = new FuelAdapterImpl(ship);
        var checkFuelCommand = new CheckFuelCommand(adapter);
        var burnFuelCommand = new BurnFuelCommand(adapter);
        var queue = Stream.of(checkFuelCommand, burnFuelCommand).collect(Collectors.toCollection(LinkedList::new));
        var macro = new MacroCommand(queue);
        macro.execute();

        assertEquals(0, queue.size());
        assertEquals(0, adapter.getRemainingFuel());
    }

    @Test
    void doThrow() {
        var mockCommand = Mockito.mock(Command.class);
        Mockito.doThrow(CommandException.class).when(mockCommand).execute();
        var queue = Stream.of(mockCommand).collect(Collectors.toCollection(LinkedList::new));
        var macro = new MacroCommand(queue);

        assertThrows(MacroCommandException.class, macro::execute);
        assertEquals(1, queue.size());
    }

}