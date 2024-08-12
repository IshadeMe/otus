package uobject.command.macro;


import uobject.command.Command;
import uobject.command.simple.BurnFuelCommand;
import uobject.command.simple.CheckFuelCommand;
import uobject.command.simple.MoveCommand;

import java.util.LinkedList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MoveBurnFuelCommand implements Command {

    private final MacroCommand macro;


    public MoveBurnFuelCommand(CheckFuelCommand checkFuelCommand,
                               MoveCommand moveCommand,
                               BurnFuelCommand burnFuelCommand) {
        this.macro = new MacroCommand(
                Stream.of(
                                checkFuelCommand,
                                moveCommand,
                                burnFuelCommand
                        )
                        .collect(Collectors.toCollection(LinkedList::new))
        );
    }

    @Override
    public void execute() {
        macro.execute();
    }

}
