public final class Truck extends Vehicle {
    public Truck(String number) { super(number); }
    @Override public VehicleType type() { return VehicleType.TRUCK; }
}
