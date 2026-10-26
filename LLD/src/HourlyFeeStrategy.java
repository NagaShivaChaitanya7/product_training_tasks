import java.time.Duration;
import java.util.Map;

public final class HourlyFeeStrategy implements FeeStrategy {
    private final Map<VehicleType, Double> rates = Map.of(VehicleType.BIKE, 10.0, VehicleType.CAR, 20.0, VehicleType.TRUCK, 40.0);
    @Override
    public double calculate(VehicleType type, Duration duration) {
        long hours = Math.max(1, (duration.toMinutes() + 59) / 60);
        return hours * rates.get(type);
    }
}
