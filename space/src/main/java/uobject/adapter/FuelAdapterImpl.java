package uobject.adapter;

import uobject.model.Property;
import uobject.model.UObject;

public class FuelAdapterImpl implements FuelAdapter {

    private final UObject o;

    public FuelAdapterImpl(UObject o) {
        this.o = o;
    }

    @Override
    public int getRemainingFuel() {
        return (int) o.getProperty(Property.FUEL_AMOUNT.getKey());
    }

    @Override
    public void burnFuel() {
        var actual = (int) o.getProperty(Property.FUEL_AMOUNT.getKey());
        o.setProperty(Property.FUEL_AMOUNT.getKey(),
                actual - (int) o.getProperty(Property.FUEL_TICK_BURN.getKey()));
    }

    @Override
    public int getFuelPerBurn() {
        return (int) o.getProperty(Property.FUEL_TICK_BURN.getKey());
    }
}
