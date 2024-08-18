package uobject.command.simple;

import uobject.adapter.Movable;
import uobject.command.Command;

import java.util.List;

public class MoveCommand implements Command {

    private final Movable movable;

    public MoveCommand(Movable movable) {
        this.movable = movable;
    }

    @Override
    public void execute() {
        var oldPosition = movable.getPosition();
        var velocity = movable.getVelocity();
        var angular = movable.getAngular();
        var newPosition = List.of(
                oldPosition.get(0) + (int) Math.round(velocity * Math.cos(angular)),
                oldPosition.get(1) + (int) Math.round(velocity * Math.sin(angular))
        );
        movable.setPosition(newPosition);
        if (!movable.getPosition().equals(newPosition)) {
            throw new RuntimeException();
        }
    }
}
