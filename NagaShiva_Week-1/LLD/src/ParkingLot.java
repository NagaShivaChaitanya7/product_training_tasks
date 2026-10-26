import java.time.Duration;
import java.time.Instant;
import java.util.*;

public final class ParkingLot {
    private final String name;
    private final List<ParkingFloor> floors = new ArrayList<>();
    private final Map<String, Ticket> tickets = new HashMap<>();
    private final Map<String, Ticket> activeVehicles = new HashMap<>();
    private final SlotAllocationStrategy allocationStrategy;
    private final FeeStrategy feeStrategy;
    private int nextTicket = 1;
    public ParkingLot(String name, SlotAllocationStrategy allocationStrategy, FeeStrategy feeStrategy) {
        this.name = name; this.allocationStrategy = allocationStrategy; this.feeStrategy = feeStrategy;
    }
    public void addFloor(ParkingFloor floor) { floors.add(Objects.requireNonNull(floor)); }
    public Ticket park(Vehicle vehicle) {
        if (activeVehicles.containsKey(vehicle.number())) throw new IllegalStateException("Vehicle is already parked");
        ParkingSlot slot = allocationStrategy.allocate(floors, vehicle);
        if (slot == null) throw new IllegalStateException("No suitable slot available");
        slot.occupy(vehicle);
        Ticket ticket = new Ticket(name + "-T" + nextTicket++, vehicle, slot);
        tickets.put(ticket.id(), ticket); activeVehicles.put(vehicle.number(), ticket);
        return ticket;
    }
    public double unpark(String ticketId) {
        Ticket ticket = tickets.get(ticketId);
        if (ticket == null) throw new IllegalArgumentException("Invalid ticket");
        if (ticket.closed()) throw new IllegalStateException("Ticket already closed");
        double fee = feeStrategy.calculate(ticket.vehicle().type(), Duration.between(ticket.entryTime(), Instant.now()));
        ticket.slot().release(); ticket.close(); activeVehicles.remove(ticket.vehicle().number());
        return fee;
    }
    public List<String> freeSlots(VehicleType type) {
        List<String> result = new ArrayList<>();
        for (ParkingFloor floor : floors)
            for (ParkingSlot slot : floor.freeSlots(type)) result.add("F" + floor.number() + "-" + slot.id());
        return result;
    }
    public String occupancy() {
        StringBuilder result = new StringBuilder();
        for (ParkingFloor floor : floors) {
            result.append("Floor ").append(floor.number()).append(": ");
            for (ParkingSlot slot : floor.slots())
                result.append(slot.id()).append("=").append(slot.status())
                    .append(slot.vehicle() == null ? "" : "(" + slot.vehicle().number() + ")").append(" ");
            result.append(System.lineSeparator());
        }
        return result.toString();
    }
}
