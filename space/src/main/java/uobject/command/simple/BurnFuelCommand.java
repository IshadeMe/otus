package uobject.command.simple;

import uobject.adapter.FuelAdapterImpl;
import uobject.command.Command;
import uobject.error.exception.BurnFuelException;

public class BurnFuelCommand implements Command {

    private final FuelAdapterImpl adapter;

    public BurnFuelCommand(FuelAdapterImpl adapter) {
        this.adapter = adapter;
    }

    @Override
    public void execute() {
        var oldRemainingFuel = adapter.getRemainingFuel();
        adapter.burnFuel();
        var remainingFuel = adapter.getRemainingFuel();

        if (remainingFuel != oldRemainingFuel - adapter.getFuelPerBurn()) {
            throw new BurnFuelException(this);
        }
    }
}
