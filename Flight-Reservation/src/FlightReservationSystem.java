import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class FlightReservationSystem {
    private Map<String, Integer> availableSeats;
    private Map<String, Integer> reservations;

    public FlightReservationSystem() {
        availableSeats = new HashMap<>();
        reservations = new HashMap<>();
    }

    public void addFlight(String flightNumber, int totalSeats) {
        availableSeats.put(flightNumber, totalSeats);
    }

    public boolean makeReservation(String flightNumber, int numSeats) {
        if (availableSeats.containsKey(flightNumber)) {
            int available = availableSeats.get(flightNumber);
            if (numSeats <= available) {
                availableSeats.put(flightNumber, available - numSeats);
                reservations.put(flightNumber, numSeats);
                return true;
            }
        }
        return false;
    }

    public int getAvailableSeats(String flightNumber) {
        if (availableSeats.containsKey(flightNumber)) {
            return availableSeats.get(flightNumber);
        }
        return 0;
    }

    public void displayAvailableFlights() {
        System.out.println("Available Flights:");
        for (String flightNumber : availableSeats.keySet()) {
            int availableSeats = getAvailableSeats(flightNumber);
            System.out.println("Flight Number: " + flightNumber + " | Available Seats: " + availableSeats);
        }
        System.out.println();
    }

    public void displayReservations() {
        System.out.println("Your Reservations:");
        for (String flightNumber : reservations.keySet()) {
            int numSeats = reservations.get(flightNumber);
            System.out.println("Flight Number: " + flightNumber + " | Reserved Seats: " + numSeats);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        FlightReservationSystem reservationSystem = new FlightReservationSystem();

        // Add flights
        reservationSystem.addFlight("TOKYO-123", 100);
        reservationSystem.addFlight("BERLIN-456", 200);
        reservationSystem.addFlight("PARIS-789", 300);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("******************");
            System.out.println("Flight Reservation System");
            System.out.println("******************");
            System.out.println("1. Display Available Flights");
            System.out.println("2. Make Reservation");
            System.out.println("3. Check Available Seats");
            System.out.println("4. Display Reservations");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    reservationSystem.displayAvailableFlights();
                    break;

                case 2:
                    System.out.println("******************");
                    System.out.println("Make Reservation");
                    System.out.println("******************");
                    System.out.print("Enter flight number: ");
                    String flightNumber = scanner.next();
                    System.out.print("Enter number of seats: ");
                    int numSeats = scanner.nextInt();

                    boolean reservation = reservationSystem.makeReservation(flightNumber, numSeats);
                    if (reservation) {
                        System.out.println("Reservation successful.");
                    } else {
                        System.out.println("Reservation failed. Not enough available seats.");
                    }
                    System.out.println();
                    break;

                case 3:
                    System.out.println("******************");
                    System.out.println("Check Available Seats");
                    System.out.println("******************");
                    System.out.print("Enter flight number: ");
                    flightNumber = scanner.next();
                    int availableSeats = reservationSystem.getAvailableSeats(flightNumber);
                    System.out.println("Available seats for flight " + flightNumber + ": " + availableSeats);
                    System.out.println();
                    break;

                case 4:
                    reservationSystem.displayReservations();
                    break;

                case 5:
                    System.out.println("Exiting...");
                    System.exit(0);

                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }
}
