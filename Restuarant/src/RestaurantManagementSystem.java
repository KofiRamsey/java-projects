import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class MenuItem {
    private int id;
    private String name;
    private double price;

    public MenuItem(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}

class Menu {
    private List<MenuItem> items;

    public Menu() {
        items = new ArrayList<>();
    }

    public void addItem(MenuItem item) {
        items.add(item);
    }

    public void removeItem(MenuItem item) {
        items.remove(item);
    }

    public List<MenuItem> getMenuItems() {
        return items;
    }
}

class Order {
    private int orderId;
    private List<MenuItem> items;

    public Order(int orderId) {
        this.orderId = orderId;
        items = new ArrayList<>();
    }

    public int getOrderId() {
        return orderId;
    }

    public void addItem(MenuItem item) {
        items.add(item);
    }

    public void removeItem(MenuItem item) {
        items.remove(item);
    }

    public List<MenuItem> getItems() {
        return items;
    }

    public double getTotal() {
        double total = 0;
        for (MenuItem item : items) {
            total += item.getPrice();
        }
        return total;
    }
}

class Reservation {
    private int reservationId;
    private String customerName;
    private int numberOfGuests;
    private String dateTime;

    public Reservation(int reservationId, String customerName, int numberOfGuests, String dateTime) {
        this.reservationId = reservationId;
        this.customerName = customerName;
        this.numberOfGuests = numberOfGuests;
        this.dateTime = dateTime;
    }

    public int getReservationId() {
        return reservationId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public int getNumberOfGuests() {
        return numberOfGuests;
    }

    public String getDateTime() {
        return dateTime;
    }
}

class Restaurant {
    private Menu menu;
    private List<Order> orders;
    private List<Reservation> reservations;

    public Restaurant() {
        menu = new Menu();
        orders = new ArrayList<>();
        reservations = new ArrayList<>();
    }

    public void addMenuItem(MenuItem item) {
        menu.addItem(item);
    }

    public void removeMenuItem(MenuItem item) {
        menu.removeItem(item);
    }

    public List<MenuItem> getMenuItems() {
        return menu.getMenuItems();
    }

    public void placeOrder(Order order) {
        orders.add(order);
    }

    public void removeOrder(Order order) {
        orders.remove(order);
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void addReservation(Reservation reservation) {
        reservations.add(reservation);
    }

    public void removeReservation(Reservation reservation) {
        reservations.remove(reservation);
    }

    public List<Reservation> getReservations() {
        return reservations;
    }
}

public class RestaurantManagementSystem {
    public static void main(String[] args) {
        // Create a new restaurant
        Restaurant restaurant = new Restaurant();

        MenuItem item1 = new MenuItem(1, "Pizza", 12.99);
        MenuItem item2 = new MenuItem(2, "Burger", 9.99);
        MenuItem item3 = new MenuItem(3, "Salad", 7.99);
        MenuItem item4 = new MenuItem(4, "Pasta", 10.99);
        MenuItem item5 = new MenuItem(5, "Sandwich", 8.99);
        MenuItem item6 = new MenuItem(6, "Tacos", 11.99);
        MenuItem item7 = new MenuItem(7, "Sushi", 14.99);
        MenuItem item8 = new MenuItem(8, "Chicken Wings", 9.99);
        MenuItem item9 = new MenuItem(9, "Steak", 16.99);
        MenuItem item10 = new MenuItem(10, "Burrito", 10.99);
        MenuItem item11 = new MenuItem(11, "Water", 1.99);
        MenuItem item12 = new MenuItem(12, "Cola", 2.49);
        MenuItem item13 = new MenuItem(13, "Lemonade", 2.99);
        MenuItem item14 = new MenuItem(14, "Iced Tea", 2.99);
        MenuItem item15 = new MenuItem(15, "Coffee", 3.49);

        // Add menu items to the restaurant's menu
        restaurant.addMenuItem(item1);
        restaurant.addMenuItem(item2);
        restaurant.addMenuItem(item3);
        restaurant.addMenuItem(item4);
        restaurant.addMenuItem(item5);
        restaurant.addMenuItem(item6);
        restaurant.addMenuItem(item7);
        restaurant.addMenuItem(item8);
        restaurant.addMenuItem(item9);
        restaurant.addMenuItem(item10);
        restaurant.addMenuItem(item11);
        restaurant.addMenuItem(item12);
        restaurant.addMenuItem(item13);
        restaurant.addMenuItem(item14);
        restaurant.addMenuItem(item15);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("--------------------------");
            System.out.println("Welcome to the Restaurant Management System!");
            System.out.println("--------------------------");
            System.out.println("Please select an option:");
            System.out.println("1. Place an order");
            System.out.println("2. Add a reservation");
            System.out.println("3. Display menu items");
            System.out.println("4. Display orders");
            System.out.println("5. Display reservations");
            System.out.println("6. Exit");
            System.out.println("--------------------------");

            int option = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (option) {
                case 1:
                    System.out.println("Placing an order");
                    System.out.println("--------------------------");
                    System.out.println("Enter the order ID:");
                    int orderId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline character

                    Order order = new Order(orderId);

                    while (true) {
                        System.out.println("Enter the item ID to add or 0 to finish:");
                        int itemId = scanner.nextInt();
                        scanner.nextLine(); // Consume newline character

                        if (itemId == 0) {
                            break;
                        }

                        MenuItem item = findMenuItem(restaurant, itemId);
                        if (item != null) {
                            order.addItem(item);
                            System.out.println("Item added to the order.");
                        } else {
                            System.out.println("Invalid item ID. Please try again.");
                        }
                    }

                    restaurant.placeOrder(order);
                    System.out.println("Order placed successfully.");
                    break;

                case 2:
                    System.out.println("Adding a reservation");
                    System.out.println("--------------------------");
                    System.out.println("Enter the reservation ID:");
                    int reservationId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline character

                    System.out.println("Enter the customer name:");
                    String customerName = scanner.nextLine();

                    System.out.println("Enter the number of guests:");
                    int numberOfGuests = scanner.nextInt();
                    scanner.nextLine(); // Consume newline character

                    System.out.println("Enter the date and time (YYYY-MM-DD HH:mm):");
                    String dateTime = scanner.nextLine();

                    Reservation reservation = new Reservation(reservationId, customerName, numberOfGuests, dateTime);
                    restaurant.addReservation(reservation);
                    System.out.println("Reservation added successfully.");
                    break;

                case 3:
                    System.out.println("Menu Items:");
                    System.out.println("--------------------------");
                    List<MenuItem> menuItems = restaurant.getMenuItems();
                    for (MenuItem item : menuItems) {
                        System.out.println(item.getId() + ". " + item.getName() + " - $" + item.getPrice());
                    }
                    break;

                case 4:
                    System.out.println("Orders:");
                    System.out.println("--------------------------");
                    List<Order> orders = restaurant.getOrders();
                    for (Order ord : orders) {
                        System.out.println("Order ID: " + ord.getOrderId());
                        System.out.println("Items:");
                        for (MenuItem item : ord.getItems()) {
                            System.out.println("- " + item.getName() + " - $" + item.getPrice());
                        }
                        System.out.println("Total: $" + ord.getTotal());
                        System.out.println();
                    }
                    break;

                case 5:
                    System.out.println("Reservations:");
                    System.out.println("--------------------------");
                    List<Reservation> reservations = restaurant.getReservations();
                    for (Reservation res : reservations) {
                        System.out.println("Reservation ID: " + res.getReservationId());
                        System.out.println("Customer Name: " + res.getCustomerName());
                        System.out.println("Number of Guests: " + res.getNumberOfGuests());
                        System.out.println("Date and Time: " + res.getDateTime());
                        System.out.println();
                    }
                    break;

                case 6:
                    System.out.println("Exiting the Restaurant Management System. Goodbye!");
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }
    }

    private static MenuItem findMenuItem(Restaurant restaurant, int itemId) {
        List<MenuItem> menuItems = restaurant.getMenuItems();
        for (MenuItem item : menuItems) {
            if (item.getId() == itemId) {
                return item;
            }
        }
        return null;
    }
}


