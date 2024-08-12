package uobject.command.macro;

import uobject.command.Command;
import uobject.command.simple.ChangeVelocityCommand;
import uobject.command.simple.RotateCommand;

import java.util.LinkedList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ChangeVelocityAfterRotate implements Command {

    private final MacroCommand macro;

    public ChangeVelocityAfterRotate(RotateCommand rotateCommand,
                                     ChangeVelocityCommand changeVelocityCommand) {

        this.macro = new MacroCommand(
                Stream.of(
                                rotateCommand,
                                changeVelocityCommand
                        )
                        .collect(Collectors.toCollection(LinkedList::new))
        );
    }

    @Override
    public void execute() {
        macro.execute();
    }
}
