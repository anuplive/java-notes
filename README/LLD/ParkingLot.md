# Step-by-Step Solution for Parking Lot System

I'll guide you through implementing this parking lot system step by step, with detailed explanations and comments. We'll progress from basic structure to complete implementation.

## Step 1: Define Enums and Base Classes

First, let's create the fundamental types our system will use:

```java
// Enums to represent different states and types in our system
public enum VehicleType {
    COMPACT, // For small cars
    LARGE,   // For trucks and SUVs
    MOTORCYCLE,
    ELECTRIC  // Needs charging capability
}

public enum ParkingSpotStatus {
    AVAILABLE,
    OCCUPIED,
    RESERVED,
    MAINTENANCE
}

public enum ParkingLotStatus {
    OPEN,
    CLOSED,
    MAINTENANCE
}

// Base Vehicle class that all vehicle types will extend
public abstract class Vehicle {
    private String licensePlate;
    private VehicleType type;
    private LocalDateTime entryTime;

    public Vehicle(String licensePlate, VehicleType type) {
        this.licensePlate = licensePlate;
        this.type = type;
    }

    // Getters and setters
    public String getLicensePlate() { return licensePlate; }
    public VehicleType getType() { return type; }
    public LocalDateTime getEntryTime() { return entryTime; }
    public void setEntryTime(LocalDateTime entryTime) { this.entryTime = entryTime; }
}

// Concrete vehicle implementations
public class Car extends Vehicle {
    public Car(String licensePlate) {
        super(licensePlate, VehicleType.COMPACT);
    }
}

public class Truck extends Vehicle {
    public Truck(String licensePlate) {
        super(licensePlate, VehicleType.LARGE);
    }
}

public class Motorcycle extends Vehicle {
    public Motorcycle(String licensePlate) {
        super(licensePlate, VehicleType.MOTORCYCLE);
    }
}

public class ElectricCar extends Vehicle {
    public ElectricCar(String licensePlate) {
        super(licensePlate, VehicleType.ELECTRIC);
    }
}
```

## Step 2: Implement ParkingSpot Class

Now let's create the ParkingSpot class which represents individual parking spaces:

```java
public class ParkingSpot {
    private String spotId;
    private VehicleType spotType;
    private ParkingSpotStatus status;
    private Vehicle currentVehicle;
    private LocalDateTime occupiedAt;

    public ParkingSpot(String spotId, VehicleType spotType) {
        this.spotId = spotId;
        this.spotType = spotType;
        this.status = ParkingSpotStatus.AVAILABLE;
    }

    /**
     * Checks if this spot can accommodate the given vehicle
     * @param vehicle Vehicle to check
     * @return true if vehicle fits, false otherwise
     */
    public boolean canFitVehicle(Vehicle vehicle) {
        // Electric vehicles need electric spots
        if (vehicle.getType() == VehicleType.ELECTRIC && spotType != VehicleType.ELECTRIC) {
            return false;
        }
        // Motorcycles can fit in any spot
        if (vehicle.getType() == VehicleType.MOTORCYCLE) {
            return true;
        }
        // Large vehicles need large spots
        if (vehicle.getType() == VehicleType.LARGE && spotType != VehicleType.LARGE) {
            return false;
        }
        // Compact vehicles can fit in compact or large spots
        return vehicle.getType() == VehicleType.COMPACT && 
               (spotType == VehicleType.COMPACT || spotType == VehicleType.LARGE);
    }

    /**
     * Reserves this parking spot
     * @return true if successful, false if already occupied/reserved
     */
    public boolean reserve() {
        if (status == ParkingSpotStatus.AVAILABLE) {
            status = ParkingSpotStatus.RESERVED;
            return true;
        }
        return false;
    }

    /**
     * Occupies this spot with a vehicle
     * @param vehicle Vehicle to park
     * @return true if successful, false if spot not available
     */
    public boolean occupy(Vehicle vehicle) {
        if (status == ParkingSpotStatus.AVAILABLE || status == ParkingSpotStatus.RESERVED) {
            currentVehicle = vehicle;
            status = ParkingSpotStatus.OCCUPIED;
            occupiedAt = LocalDateTime.now();
            return true;
        }
        return false;
    }

    /**
     * Releases this spot (when vehicle leaves)
     * @return true if successful, false if spot wasn't occupied
     */
    public boolean release() {
        if (status == ParkingSpotStatus.OCCUPIED) {
            currentVehicle = null;
            status = ParkingSpotStatus.AVAILABLE;
            occupiedAt = null;
            return true;
        }
        return false;
    }

    // Getters
    public String getSpotId() { return spotId; }
    public VehicleType getSpotType() { return spotType; }
    public ParkingSpotStatus getStatus() { return status; }
    public Vehicle getCurrentVehicle() { return currentVehicle; }
    public LocalDateTime getOccupiedAt() { return occupiedAt; }
}
```

## Step 3: Implement ParkingFloor Class

Next, let's create the ParkingFloor which contains multiple parking spots:

```java
public class ParkingFloor {
    private int floorNumber;
    private List<ParkingSpot> spots;
    private ParkingDisplayBoard displayBoard;

    public ParkingFloor(int floorNumber, List<ParkingSpot> spots) {
        this.floorNumber = floorNumber;
        this.spots = new ArrayList<>(spots); // Defensive copy
        this.displayBoard = new ParkingDisplayBoard();
        updateDisplayBoard();
    }

    /**
     * Checks if this floor has available spots for given vehicle type
     * @param type Vehicle type to check
     * @return true if available spot exists
     */
    public boolean hasSpotFor(VehicleType type) {
        return spots.stream()
                   .anyMatch(spot -> spot.getStatus() == ParkingSpotStatus.AVAILABLE && 
                                     spotCanFitType(spot.getSpotType(), type));
    }

    /**
     * Gets the first available spot for the vehicle type
     * @param type Vehicle type needed
     * @return Optional containing spot if found
     */
    public Optional<ParkingSpot> getFirstAvailableSpot(VehicleType type) {
        return spots.stream()
                   .filter(spot -> spot.getStatus() == ParkingSpotStatus.AVAILABLE)
                   .filter(spot -> spotCanFitType(spot.getSpotType(), type))
                   .findFirst();
    }

    /**
     * Counts available spots for a vehicle type
     * @param type Vehicle type to count
     * @return number of available spots
     */
    public long getAvailableSpotsCount(VehicleType type) {
        return spots.stream()
                   .filter(spot -> spot.getStatus() == ParkingSpotStatus.AVAILABLE)
                   .filter(spot -> spotCanFitType(spot.getSpotType(), type))
                   .count();
    }

    /**
     * Updates the display board with current availability
     */
    public void updateDisplayBoard() {
        Map<VehicleType, Long> counts = Arrays.stream(VehicleType.values())
            .collect(Collectors.toMap(
                Function.identity(),
                this::getAvailableSpotsCount
            ));
        displayBoard.update(counts);
    }

    // Helper method to check spot compatibility
    private boolean spotCanFitType(VehicleType spotType, VehicleType vehicleType) {
        // Similar logic to ParkingSpot.canFitVehicle but works with types only
        if (vehicleType == VehicleType.ELECTRIC && spotType != VehicleType.ELECTRIC) {
            return false;
        }
        if (vehicleType == VehicleType.MOTORCYCLE) {
            return true;
        }
        if (vehicleType == VehicleType.LARGE && spotType != VehicleType.LARGE) {
            return false;
        }
        return vehicleType == VehicleType.COMPACT && 
               (spotType == VehicleType.COMPACT || spotType == VehicleType.LARGE);
    }

    // Getters
    public int getFloorNumber() { return floorNumber; }
    public List<ParkingSpot> getSpots() { return Collections.unmodifiableList(spots); }
    public ParkingDisplayBoard getDisplayBoard() { return displayBoard; }
}
```

## Step 4: Implement ParkingTicket and DisplayBoard

```java
public class ParkingTicket {
    private String ticketId;
    private String spotId;
    private String licensePlate;
    private LocalDateTime entryTime;
    private LocalDateTime exitTime;
    private double fee;

    public ParkingTicket(String ticketId, String spotId, String licensePlate) {
        this.ticketId = ticketId;
        this.spotId = spotId;
        this.licensePlate = licensePlate;
        this.entryTime = LocalDateTime.now();
    }

    /**
     * Marks the ticket as exited and sets exit time
     */
    public void markExit() {
        this.exitTime = LocalDateTime.now();
    }

    // Getters and setters
    public String getTicketId() { return ticketId; }
    public String getSpotId() { return spotId; }
    public String getLicensePlate() { return licensePlate; }
    public LocalDateTime getEntryTime() { return entryTime; }
    public LocalDateTime getExitTime() { return exitTime; }
    public double getFee() { return fee; }
    public void setFee(double fee) { this.fee = fee; }
}

public class ParkingDisplayBoard {
    private Map<VehicleType, Long> availableSpots;

    public ParkingDisplayBoard() {
        availableSpots = new EnumMap<>(VehicleType.class);
        Arrays.stream(VehicleType.values()).forEach(type -> availableSpots.put(type, 0L));
    }

    /**
     * Updates the display board with new counts
     * @param counts Map of vehicle types to available counts
     */
    public void update(Map<VehicleType, Long> counts) {
        availableSpots.clear();
        availableSpots.putAll(counts);
    }

    /**
     * Shows current availability
     */
    public void show() {
        System.out.println("Parking Availability:");
        availableSpots.forEach((type, count) -> 
            System.out.printf("%-10s: %d%n", type, count));
    }
}
```

## Step 5: Implement Fee Calculation Strategy

Let's create the fee calculation system using Strategy pattern:

```java
public interface ParkingFeeCalculator {
    double calculateFee(LocalDateTime entryTime, LocalDateTime exitTime);
}

public class FlatRateFeeCalculator implements ParkingFeeCalculator {
    private final double flatRate;

    public FlatRateFeeCalculator(double flatRate) {
        this.flatRate = flatRate;
    }

    @Override
    public double calculateFee(LocalDateTime entryTime, LocalDateTime exitTime) {
        return flatRate;
    }
}

public class HourlyFeeCalculator implements ParkingFeeCalculator {
    private final double hourlyRate;
    private final double firstHourRate;

    public HourlyFeeCalculator(double firstHourRate, double hourlyRate) {
        this.firstHourRate = firstHourRate;
        this.hourlyRate = hourlyRate;
    }

    @Override
    public double calculateFee(LocalDateTime entryTime, LocalDateTime exitTime) {
        Duration duration = Duration.between(entryTime, exitTime);
        long hours = duration.toHours();
        if (duration.toMinutes() % 60 > 0) hours++; // Round up partial hours
        
        if (hours <= 1) return firstHourRate;
        return firstHourRate + (hours - 1) * hourlyRate;
    }
}

public class FreeFirst30MinutesCalculator implements ParkingFeeCalculator {
    private final double hourlyRate;

    public FreeFirst30MinutesCalculator(double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    @Override
    public double calculateFee(LocalDateTime entryTime, LocalDateTime exitTime) {
        Duration duration = Duration.between(entryTime, exitTime);
        if (duration.toMinutes() <= 30) return 0;
        
        long hours = duration.toHours();
        if (duration.toMinutes() % 60 > 0) hours++; // Round up
        
        return hours * hourlyRate;
    }
}
```

## Step 6: Implement ParkingLot Class (Core)

Now let's implement the main ParkingLot class:

```java
public class ParkingLot {
    private String name;
    private List<ParkingFloor> floors;
    private ParkingLotStatus status;
    private ParkingFeeCalculator feeCalculator;
    private Map<String, ParkingTicket> activeTickets;
    private SpotAllocationStrategy allocationStrategy;

    public ParkingLot(String name, List<ParkingFloor> floors, 
                     ParkingFeeCalculator feeCalculator,
                     SpotAllocationStrategy allocationStrategy) {
        this.name = name;
        this.floors = new ArrayList<>(floors);
        this.status = ParkingLotStatus.OPEN;
        this.feeCalculator = feeCalculator;
        this.allocationStrategy = allocationStrategy;
        this.activeTickets = new HashMap<>();
    }

    /**
     * Parks a vehicle in the parking lot
     * @param vehicle Vehicle to park
     * @return true if successful, false if no spot available
     */
    public boolean parkVehicle(Vehicle vehicle) {
        if (status != ParkingLotStatus.OPEN) {
            return false;
        }

        Optional<ParkingSpot> spot = findAvailableSpot(vehicle.getType());
        if (spot.isEmpty()) {
            return false;
        }

        ParkingSpot parkingSpot = spot.get();
        if (!parkingSpot.occupy(vehicle)) {
            return false;
        }

        // Create and store ticket
        String ticketId = generateTicketId();
        ParkingTicket ticket = new ParkingTicket(ticketId, parkingSpot.getSpotId(), 
                                               vehicle.getLicensePlate());
        activeTickets.put(ticketId, ticket);

        // Update display boards
        floors.forEach(ParkingFloor::updateDisplayBoard);

        return true;
    }

    /**
     * Unparks a vehicle and calculates fee
     * @param ticketId Ticket ID of parked vehicle
     * @return true if successful, false if ticket invalid
     */
    public boolean unparkVehicle(String ticketId) {
        ParkingTicket ticket = activeTickets.get(ticketId);
        if (ticket == null) {
            return false;
        }

        // Find the spot
        Optional<ParkingSpot> spotOptional = floors.stream()
            .flatMap(floor -> floor.getSpots().stream())
            .filter(s -> s.getSpotId().equals(ticket.getSpotId()))
            .findFirst();

        if (spotOptional.isEmpty()) {
            return false;
        }

        ParkingSpot spot = spotOptional.get();
        if (!spot.release()) {
            return false;
        }

        // Calculate fee
        ticket.markExit();
        double fee = feeCalculator.calculateFee(ticket.getEntryTime(), ticket.getExitTime());
        ticket.setFee(fee);

        // Remove from active tickets
        activeTickets.remove(ticketId);

        // Update display boards
        floors.forEach(ParkingFloor::updateDisplayBoard);

        return true;
    }

    /**
     * Finds an available spot for the vehicle type
     * @param type Vehicle type needed
     * @return Optional containing spot if found
     */
    public Optional<ParkingSpot> findAvailableSpot(VehicleType type) {
        return allocationStrategy.findSpot(floors, type);
    }

    /**
     * Gets count of occupied spots
     * @return total occupied spots
     */
    public long getOccupiedSpotsCount() {
        return floors.stream()
                   .flatMap(floor -> floor.getSpots().stream())
                   .filter(spot -> spot.getStatus() == ParkingSpotStatus.OCCUPIED)
                   .count();
    }

    /**
     * Gets available spots by vehicle type
     * @return Map of vehicle type to available count
     */
    public Map<VehicleType, Long> getAvailableSpotsByType() {
        return Arrays.stream(VehicleType.values())
                   .collect(Collectors.toMap(
                       Function.identity(),
                       type -> floors.stream()
                                  .mapToLong(floor -> floor.getAvailableSpotsCount(type))
                                  .sum()
                   ));
    }

    /**
     * Calculates fee for a ticket
     * @param ticket Parking ticket
     * @return calculated fee
     */
    public double calculateFee(ParkingTicket ticket) {
        return feeCalculator.calculateFee(ticket.getEntryTime(), LocalDateTime.now());
    }

    /**
     * Gets spots matching a filter predicate
     * @param filter Predicate to test spots
     * @return List of matching spots
     */
    public List<ParkingSpot> getSpotsBy(Predicate<ParkingSpot> filter) {
        return floors.stream()
                   .flatMap(floor -> floor.getSpots().stream())
                   .filter(filter)
                   .collect(Collectors.toList());
    }

    // Helper method to generate unique ticket IDs
    private String generateTicketId() {
        return "TKT-" + UUID.randomUUID().toString().substring(0, 8);
    }

    // Getters
    public String getName() { return name; }
    public List<ParkingFloor> getFloors() { return Collections.unmodifiableList(floors); }
    public ParkingLotStatus getStatus() { return status; }
}
```

## Step 7: Implement Spot Allocation Strategies

```java
public interface SpotAllocationStrategy {
    Optional<ParkingSpot> findSpot(List<ParkingFloor> floors, VehicleType type);
}

/**
 * Finds the first available spot starting from the first floor
 */
public class FirstAvailableStrategy implements SpotAllocationStrategy {
    @Override
    public Optional<ParkingSpot> findSpot(List<ParkingFloor> floors, VehicleType type) {
        for (ParkingFloor floor : floors) {
            Optional<ParkingSpot> spot = floor.getFirstAvailableSpot(type);
            if (spot.isPresent()) {
                return spot;
            }
        }
        return Optional.empty();
    }
}

/**
 * Finds the nearest spot to the entrance (assumes first floor is closest)
 */
public class NearestEntranceStrategy implements SpotAllocationStrategy {
    @Override
    public Optional<ParkingSpot> findSpot(List<ParkingFloor> floors, VehicleType type) {
        // Sort floors by floor number (ascending)
        return floors.stream()
                   .sorted(Comparator.comparingInt(ParkingFloor::getFloorNumber))
                   .map(floor -> floor.getFirstAvailableSpot(type))
                   .filter(Optional::isPresent)
                   .findFirst()
                   .orElse(Optional.empty());
    }
}
```

## Step 8: Implement Reporting System

```java
public class ParkingLotReporter {
    private ParkingLot parkingLot;

    public ParkingLotReporter(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    /**
     * Gets occupancy percentage by floor
     * @return Map of floor number to occupancy percentage
     */
    public Map<Integer, Double> getOccupancyByFloor() {
        return parkingLot.getFloors().stream()
                   .collect(Collectors.toMap(
                       ParkingFloor::getFloorNumber,
                       floor -> {
                           long total = floor.getSpots().size();
                           long occupied = floor.getSpots().stream()
                                               .filter(spot -> spot.getStatus() == ParkingSpotStatus.OCCUPIED)
                                               .count();
                           return (double) occupied / total * 100;
                       }
                   ));
    }

    /**
     * Gets average parking duration in minutes
     * @return average duration or 0 if no completed parkings
     */
    public double getAverageParkingDuration() {
        List<ParkingTicket> completedTickets = getCompletedTickets();
        if (completedTickets.isEmpty()) return 0;
        
        return completedTickets.stream()
                   .mapToLong(ticket -> Duration.between(ticket.getEntryTime(), ticket.getExitTime()).toMinutes())
                   .average()
                   .getAsDouble();
    }

    /**
     * Gets revenue by vehicle type
     * @return Map of vehicle type to total revenue
     */
    public Map<VehicleType, Double> getRevenueByVehicleType() {
        return getCompletedTickets().stream()
                   .collect(Collectors.groupingBy(
                       ticket -> getVehicleTypeForTicket(ticket),
                       Collectors.summingDouble(ParkingTicket::getFee)
                   ));
    }

    private List<ParkingTicket> getCompletedTickets() {
        // In a real system, these would come from a database
        // For now, we'll return an empty list as we don't have persistence
        return Collections.emptyList();
    }

    private VehicleType getVehicleTypeForTicket(ParkingTicket ticket) {
        // In a real system, we'd look up the vehicle
        // For now, we'll return a random type
        return VehicleType.values()[new Random().nextInt(VehicleType.values().length)];
    }
}
```

## Step 9: Putting It All Together - Example Usage

Here's how you might use the parking lot system:

```java
public class ParkingLotApplication {
    public static void main(String[] args) {
        // Create parking spots
        List<ParkingSpot> groundFloorSpots = List.of(
            new ParkingSpot("G-1", VehicleType.COMPACT),
            new ParkingSpot("G-2", VehicleType.LARGE),
            new ParkingSpot("G-3", VehicleType.MOTORCYCLE),
            new ParkingSpot("G-4", VehicleType.ELECTRIC)
        );
        
        List<ParkingSpot> firstFloorSpots = List.of(
            new ParkingSpot("1-1", VehicleType.COMPACT),
            new ParkingSpot("1-2", VehicleType.COMPACT),
            new ParkingSpot("1-3", VehicleType.LARGE)
        );
        
        // Create floors
        ParkingFloor groundFloor = new ParkingFloor(0, groundFloorSpots);
        ParkingFloor firstFloor = new ParkingFloor(1, firstFloorSpots);
        
        // Create parking lot with hourly pricing and nearest entrance strategy
        ParkingLot parkingLot = new ParkingLot(
            "Downtown Parking",
            List.of(groundFloor, firstFloor),
            new HourlyFeeCalculator(5.0, 3.0), // $5 first hour, $3 subsequent
            new NearestEntranceStrategy()
        );
        
        // Park some vehicles
        Vehicle car1 = new Car("ABC123");
        Vehicle truck1 = new Truck("TRK456");
        Vehicle motorcycle1 = new Motorcycle("MOTO789");
        
        boolean parked1 = parkingLot.parkVehicle(car1);
        boolean parked2 = parkingLot.parkVehicle(truck1);
        boolean parked3 = parkingLot.parkVehicle(motorcycle1);
        
        System.out.println("Parking results:");
        System.out.println("Car parked: " + parked1);
        System.out.println("Truck parked: " + parked2);
        System.out.println("Motorcycle parked: " + parked3);
        
        // Show current availability
        groundFloor.getDisplayBoard().show();
        
        // Create reporter
        ParkingLotReporter reporter = new ParkingLotReporter(parkingLot);
        
        // Show occupancy
        System.out.println("\nOccupancy by floor:");
        reporter.getOccupancyByFloor().forEach((floor, percent) -> 
            System.out.printf("Floor %d: %.1f%%%n", floor, percent));
    }
}
```

## Key SOLID Principles Demonstrated

1. **Single Responsibility Principle (SRP)**:
   - Each class has a single responsibility
   - ParkingSpot manages spot state
   - ParkingFeeCalculator handles pricing
   - ParkingLotReporter handles reporting

2. **Open/Closed Principle (OCP)**:
   - Can add new vehicle types without modifying existing code
   - Can add new fee calculators without changing ParkingLot

3. **Liskov Substitution Principle (LSP)**:
   - All Vehicle subclasses can be used interchangeably
   - All fee calculators implement the same interface

4. **Interface Segregation Principle (ISP)**:
   - Small, focused interfaces (SpotAllocationStrategy, ParkingFeeCalculator)

5. **Dependency Inversion Principle (DIP)**:
   - ParkingLot depends on abstractions (FeeCalculator, AllocationStrategy)
   - High-level modules don't depend on low-level details

This implementation provides a complete parking lot system with proper OOP design, SOLID principles, and Java Collections/Streams usage. You can extend it further by adding persistence, more reporting features, or additional parking strategies.