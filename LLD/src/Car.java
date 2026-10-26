public final class Car extends Vehicle {
    public Car(String number) { super(number); }
    @Override public VehicleType type() { return VehicleType.CAR; }
}
