public final class ParkingSlot {
    private final String id;
    private final VehicleType type;
    private SlotStatus status = SlotStatus.FREE;
    private Vehicle vehicle;
    public ParkingSlot(String id, VehicleType type) { this.id = id; this.type = type; }
    public String id() { return id; }
    public VehicleType type() { return type; }
    public SlotStatus status() { return status; }
    public Vehicle vehicle() { return vehicle; }
    public boolean canFit(Vehicle candidate) { return status == SlotStatus.FREE && type == candidate.type(); }
    public void occupy(Vehicle candidate) {
        if (!canFit(candidate)) throw new IllegalStateException("Slot unavailable");
        status = SlotStatus.OCCUPIED; vehicle = candidate;
    }
    public void release() { status = SlotStatus.FREE; vehicle = null; }
}
