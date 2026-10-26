public final class Bike extends Vehicle {
    public Bike(String number) { super(number); }
    @Override public VehicleType type() { return VehicleType.BIKE; }
}
