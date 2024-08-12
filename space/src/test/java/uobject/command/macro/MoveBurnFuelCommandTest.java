package uobject.command.macro;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import uobject.command.simple.BurnFuelCommand;
import uobject.command.simple.CheckFuelCommand;
import uobject.command.simple.MoveCommand;
import uobject.error.exception.CommandException;

import static org.junit.jupiter.api.Assertions.assertThrows;


class MoveBurnFuelCommandTest {

    @Test
    void execute() {
        var moveMock = Mockito.mock(MoveCommand.class);
        var checkMock = Mockito.mock(CheckFuelCommand.class);
        var burnMock = Mockito.mock(BurnFuelCommand.class);
        var command = new MoveBurnFuelCommand(checkMock, moveMock, burnMock);
        command.execute();
        Mockito.verify(moveMock, Mockito.times(1)).execute();
        Mockito.verify(checkMock, Mockito.times(1)).execute();
        Mockito.verify(burnMock, Mockito.times(1)).execute();
    }

    @Test
    void doThrow() {
        var moveMock = Mockito.mock(MoveCommand.class);
        var checkMock = Mockito.mock(CheckFuelCommand.class);
        var burnMock = Mockito.mock(BurnFuelCommand.class);
        Mockito.doThrow(CommandException.class).when(moveMock).execute();
        var command = new MoveBurnFuelCommand(checkMock, moveMock, burnMock);

        assertThrows(CommandException.class, command::execute);
        Mockito.verify(moveMock, Mockito.times(1)).execute();
        Mockito.verify(checkMock, Mockito.times(1)).execute();
        Mockito.verify(burnMock, Mockito.times(0)).execute();
    }

}