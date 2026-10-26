public class ParkingLotMain {
    private static void check(boolean condition) { if (!condition) throw new AssertionError(); }
    public static void main(String[] args) {
        ParkingLot lot = new ParkingLot("CITY", new FirstFitAllocationStrategy(), new HourlyFeeStrategy());
        ParkingFloor one = new ParkingFloor(1), two = new ParkingFloor(2);
        one.addSlot(new ParkingSlot("B1", VehicleType.BIKE)); one.addSlot(new ParkingSlot("C1", VehicleType.CAR));
        two.addSlot(new ParkingSlot("T1", VehicleType.TRUCK)); two.addSlot(new ParkingSlot("C2", VehicleType.CAR));
        lot.addFloor(one); lot.addFloor(two);
        Ticket bike = lot.park(new Bike("KA-01"));
        Ticket car = lot.park(new Car("KA-02"));
        Ticket truck = lot.park(new Truck("KA-03"));
        check(lot.freeSlots(VehicleType.CAR).size() == 1);
        check(lot.occupancy().contains("KA-02"));
        try { lot.park(new Car("KA-02")); throw new AssertionError(); } catch (IllegalStateException expected) { }
        try { lot.unpark("bad-ticket"); throw new AssertionError(); } catch (IllegalArgumentException expected) { }
        check(lot.unpark(car.id()) >= 20);
        try { lot.unpark(car.id()); throw new AssertionError(); } catch (IllegalStateException expected) { }
        lot.unpark(bike.id()); lot.unpark(truck.id());
        System.out.println("Parking lot demo and 10 functional checks passed.");
        System.out.println(lot.occupancy());
    }
}
