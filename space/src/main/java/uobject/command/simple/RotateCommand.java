package uobject.command.simple;

import uobject.adapter.RotatableAdapter;
import uobject.command.Command;

public class RotateCommand implements Command {

    private final RotatableAdapter adapter;

    public RotateCommand(RotatableAdapter adapter) {
        this.adapter = adapter;
    }

    @Override
    public void execute() {
        var oldDirection = adapter.getDirection();
        var velocity = adapter.getAngularVelocity();
        var perCount = 360 / adapter.getDirectionsNumber();
        var delta = velocity / perCount;
        int direction = (oldDirection + delta * perCount) % 360;
        adapter.setDirection(direction);
        if (direction != adapter.getDirection()) {
            throw new RuntimeException();
        }
    }
}
