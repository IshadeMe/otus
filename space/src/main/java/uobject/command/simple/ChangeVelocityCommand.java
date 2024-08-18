package uobject.command.simple;

import uobject.adapter.Acceleration;
import uobject.command.Command;

public class ChangeVelocityCommand implements Command {

    private final Acceleration acceleration;

    public ChangeVelocityCommand(Acceleration acceleration) {
        this.acceleration = acceleration;
    }

    @Override
    public void execute() {
        var acc = acceleration.getAcceleration();
        int velocity = 0;
        try {
            velocity = acceleration.getVelocity();
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        acceleration.setVelocity(acc + velocity);
    }
}
