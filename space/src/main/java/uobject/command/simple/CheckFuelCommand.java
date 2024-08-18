package uobject.command.simple;

import uobject.adapter.FuelAdapter;
import uobject.command.Command;
import uobject.error.exception.CheckFuelException;

public class CheckFuelCommand implements Command {

    private final FuelAdapter adapter;

    public CheckFuelCommand(FuelAdapter adapter) {
        this.adapter = adapter;
    }


    @Override
    public void execute() {
        if (adapter.getFuelPerBurn() > adapter.getRemainingFuel()) {
            throw new CheckFuelException(this);
        }
    }
}
