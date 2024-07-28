package uobject.action;

import uobject.adapter.RotatableAdapter;

public class Rotate implements Command {

    private final RotatableAdapter adapter;

    public Rotate(RotatableAdapter adapter) {
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
