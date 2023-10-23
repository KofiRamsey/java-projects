import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

class Car {
    private int carId;
    private String brand;
    private String model;
    private boolean available;

    public Car(int carId, String brand, String model) {
        this.carId = carId;
        this.brand = brand;
        this.model = model;
        this.available = true;
    }

    public int getCarId() {
        return carId;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}

class Booking {
    private int bookingId;
    private Car car;
    private LocalDate startDate;
    private LocalDate endDate;

    public Booking(int bookingId, Car car, LocalDate startDate, LocalDate endDate) {
        this.bookingId = bookingId;
        this.car = car;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getBookingId() {
        return bookingId;
    }

    public Car getCar() {
        return car;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }
}

class CarRentalSystem {
    private List<Car> cars;
    private List<Booking> bookings;

    public CarRentalSystem() {
        cars = new ArrayList<>();
        bookings = new ArrayList<>();
    }

    public void addCar(Car car) {
        cars.add(car);
    }

    public void removeCar(Car car) {
        cars.remove(car);
    }

    public List<Car> getCars() {
        return cars;
    }

    public boolean isCarAvailable(Car car, LocalDate startDate, LocalDate endDate) {
        for (Booking booking : bookings) {
            if (booking.getCar().equals(car) && !booking.getEndDate().isBefore(startDate) &&
                    !booking.getStartDate().isAfter(endDate)) {
                return false;
            }
        }
        return true;
    }

    public Booking bookCar(Car car, LocalDate startDate, LocalDate endDate) {
        if (!isCarAvailable(car, startDate, endDate)) {
            return null;
        }

        int bookingId = bookings.size() + 1;
        Booking booking = new Booking(bookingId, car, startDate, endDate);
        bookings.add(booking);
        car.setAvailable(false);
        return booking;
    }

    public void cancelBooking(Booking booking) {
        bookings.remove(booking);
        booking.getCar().setAvailable(true);
    }

    public List<Booking> getBookings() {
        return bookings;
    }
}

public class CarRentalManagementSystem {
    public static void main(String[] args) {
        CarRentalSystem carRentalSystem = new CarRentalSystem();

        // Add cars to the rental system
        Car car1 = new Car(1, "Toyota", "Corolla");
        Car car2 = new Car(2, "Honda", "Civic");
        Car car3 = new Car(3, "Ford", "Mustang");
        carRentalSystem.addCar(car1);
        carRentalSystem.addCar(car2);
        carRentalSystem.addCar(car3);

        // Check car availability
        LocalDate startDate = LocalDate.of(2023, 5, 10);
        LocalDate endDate = LocalDate.of(2023, 5, 15);
        boolean car1Available = carRentalSystem.isCarAvailable(car1, startDate, endDate);

        // Book a car
        if (car1Available) {
            Booking booking = carRentalSystem.bookCar(car1, startDate, endDate);
            System.out.println("Booking successful. Booking ID: " + booking.getBookingId());
        } else {
            System.out.println("Car is not available for the given dates.");
        }

        // Display available cars
        List<Car> availableCars = carRentalSystem.getCars();
        System.out.println("Available Cars:");
        for (Car car : availableCars) {
            if (car.isAvailable()) {
                System.out.println("Car ID: " + car.getCarId());
                System.out.println("Brand: " + car.getBrand());
                System.out.println("Model: " + car.getModel());
                System.out.println();
            }
        }

        // Display bookings
        List<Booking> bookings = carRentalSystem.getBookings();
        System.out.println("Bookings:");
        for (Booking booking : bookings) {
            System.out.println("Booking ID: " + booking.getBookingId());
            System.out.println("Car ID: " + booking.getCar().getCarId());
            System.out.println("Brand: " + booking.getCar().getBrand());
            System.out.println("Model: " + booking.getCar().getModel());
            System.out.println("Start Date: " + booking.getStartDate());
            System.out.println("End Date: " + booking.getEndDate());
            System.out.println();
        }

        // Cancel a booking
        if (!bookings.isEmpty()) {
            Booking bookingToCancel = bookings.get(0);
            carRentalSystem.cancelBooking(bookingToCancel);
            System.out.println("Booking canceled successfully.");
        }
    }
}

