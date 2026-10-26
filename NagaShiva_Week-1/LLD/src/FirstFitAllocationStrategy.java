import java.util.List;

public final class FirstFitAllocationStrategy implements SlotAllocationStrategy {
    @Override
    public ParkingSlot allocate(List<ParkingFloor> floors, Vehicle vehicle) {
        for (ParkingFloor floor : floors)
            for (ParkingSlot slot : floor.slots())
                if (slot.canFit(vehicle)) return slot;
        return null;
    }
}
