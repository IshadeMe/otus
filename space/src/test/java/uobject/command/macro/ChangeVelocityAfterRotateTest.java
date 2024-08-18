package uobject.command.macro;


import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import uobject.command.simple.ChangeVelocityCommand;
import uobject.command.simple.RotateCommand;
import uobject.error.exception.CommandException;

import static org.junit.jupiter.api.Assertions.assertThrows;

class ChangeVelocityAfterRotateTest {

    @Test
    void execute() {
        var rotateMock = Mockito.mock(RotateCommand.class);
        var changeVelocityCommand = Mockito.mock(ChangeVelocityCommand.class);
        var command = new ChangeVelocityAfterRotate(rotateMock, changeVelocityCommand);
        command.execute();
        Mockito.verify(rotateMock, Mockito.times(1)).execute();
        Mockito.verify(changeVelocityCommand, Mockito.times(1)).execute();
    }

    @Test
    void doThrow() {
        var rotateMock = Mockito.mock(RotateCommand.class);
        var changeVelocityCommand = Mockito.mock(ChangeVelocityCommand.class);
        var command = new ChangeVelocityAfterRotate(rotateMock, changeVelocityCommand);
        Mockito.doThrow(CommandException.class).when(changeVelocityCommand).execute();
        assertThrows(CommandException.class, command::execute);
        Mockito.verify(rotateMock, Mockito.times(1)).execute();
        Mockito.verify(changeVelocityCommand, Mockito.times(1)).execute();
    }


}