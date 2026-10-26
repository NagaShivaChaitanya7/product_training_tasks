import java.util.*;

public final class ParkingFloor {
    private final int number;
    private final List<ParkingSlot> slots = new ArrayList<>();
    public ParkingFloor(int number) {
        if (number < 1) throw new IllegalArgumentException("Invalid floor");
        this.number = number;
    }
    public int number() { return number; }
    public void addSlot(ParkingSlot slot) { slots.add(Objects.requireNonNull(slot)); }
    public List<ParkingSlot> slots() { return Collections.unmodifiableList(slots); }
    public List<ParkingSlot> freeSlots(VehicleType type) {
        List<ParkingSlot> result = new ArrayList<>();
        for (ParkingSlot slot : slots) if (slot.status() == SlotStatus.FREE && slot.type() == type) result.add(slot);
        return result;
    }
}
