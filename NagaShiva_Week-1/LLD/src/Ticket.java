import java.time.Instant;

public final class Ticket {
    private final String id;
    private final Vehicle vehicle;
    private final ParkingSlot slot;
    private final Instant entryTime = Instant.now();
    private boolean closed;
    public Ticket(String id, Vehicle vehicle, ParkingSlot slot) { this.id = id; this.vehicle = vehicle; this.slot = slot; }
    public String id() { return id; }
    public Vehicle vehicle() { return vehicle; }
    public ParkingSlot slot() { return slot; }
    public Instant entryTime() { return entryTime; }
    public boolean closed() { return closed; }
    public void close() { closed = true; }
}
