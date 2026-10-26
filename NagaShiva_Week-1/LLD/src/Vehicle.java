import java.util.Locale;

public abstract class Vehicle {
    private final String number;
    protected Vehicle(String number) {
        if (number == null || number.isBlank()) throw new IllegalArgumentException("Vehicle number is required");
        this.number = number.toUpperCase(Locale.ROOT);
    }
    public String number() { return number; }
    public abstract VehicleType type();
}
