import java.util.List;

public interface SlotAllocationStrategy {
    ParkingSlot allocate(List<ParkingFloor> floors, Vehicle vehicle);
}
