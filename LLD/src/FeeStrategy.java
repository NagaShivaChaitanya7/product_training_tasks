import java.time.Duration;

public interface FeeStrategy {
    double calculate(VehicleType type, Duration duration);
}
