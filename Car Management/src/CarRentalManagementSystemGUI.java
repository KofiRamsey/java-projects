import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.List;

public class CarRentalManagementSystemGUI {
    private CarRentalSystem carRentalSystem;
    private JFrame frame;
    private JTable carTable;
    private JTable bookingTable;

    public CarRentalManagementSystemGUI() {
        carRentalSystem = new CarRentalSystem();
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setTitle("Car Rental Management System");
        frame.setBounds(100, 100, 800, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout(0, 0));

        JPanel panel = new JPanel();
        frame.getContentPane().add(panel, BorderLayout.NORTH);

        JLabel lblTitle = new JLabel("Car Rental Management System");
        lblTitle.setFont(new Font("Tahoma", Font.BOLD, 16));
        panel.add(lblTitle);

        JPanel mainPanel = new JPanel();
        frame.getContentPane().add(mainPanel, BorderLayout.CENTER);
        mainPanel.setLayout(new GridLayout(1, 2, 10, 0));

        JPanel carPanel = new JPanel();
        mainPanel.add(carPanel);
        carPanel.setLayout(new BorderLayout(0, 0));

        JLabel lblCars = new JLabel("Available Cars");
        lblCars.setFont(new Font("Tahoma", Font.BOLD, 14));
        carPanel.add(lblCars, BorderLayout.NORTH);

        JScrollPane carScrollPane = new JScrollPane();
        carPanel.add(carScrollPane, BorderLayout.CENTER);

        carTable = new JTable();
        carScrollPane.setViewportView(carTable);

        JPanel bookingPanel = new JPanel();
        mainPanel.add(bookingPanel);
        bookingPanel.setLayout(new BorderLayout(0, 0));

        JLabel lblBookings = new JLabel("Bookings");
        lblBookings.setFont(new Font("Tahoma", Font.BOLD, 14));
        bookingPanel.add(lblBookings, BorderLayout.NORTH);

        JScrollPane bookingScrollPane = new JScrollPane();
        bookingPanel.add(bookingScrollPane, BorderLayout.CENTER);

        bookingTable = new JTable();
        bookingScrollPane.setViewportView(bookingTable);

        JPanel actionPanel = new JPanel();
        frame.getContentPane().add(actionPanel, BorderLayout.SOUTH);

        JButton btnAddCar = new JButton("Add Car");
        actionPanel.add(btnAddCar);

        JButton btnBookCar = new JButton("Book Car");
        actionPanel.add(btnBookCar);

        JButton btnCancelBooking = new JButton("Cancel Booking");
        actionPanel.add(btnCancelBooking);

        // Add action listeners

        btnAddCar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addCar();
            }
        });

        btnBookCar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                bookCar();
            }
        });

        btnCancelBooking.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cancelBooking();
            }
        });

        // Initialize tables

        refreshCarTable();
        refreshBookingTable();
    }

    private void refreshCarTable() {
        List<Car> cars = carRentalSystem.getCars();
        DefaultTableModel carTableModel = new DefaultTableModel();
        carTableModel.addColumn("Car ID");
        carTableModel.addColumn("Brand");
        carTableModel.addColumn("Model");
        carTableModel.addColumn("Available");

        for (Car car : cars) {
            carTableModel.addRow(new Object[]{car.getCarId(), car.getBrand(), car.getModel(), car.isAvailable()});
        }

        carTable.setModel(carTableModel);
    }

    private void refreshBookingTable() {
        List<Booking> bookings = carRentalSystem.getBookings();
        DefaultTableModel bookingTableModel = new DefaultTableModel();
        bookingTableModel.addColumn("Booking ID");
        bookingTableModel.addColumn("Car ID");
        bookingTableModel.addColumn("Brand");
        bookingTableModel.addColumn("Model");
        bookingTableModel.addColumn("Start Date");
        bookingTableModel.addColumn("End Date");

        for (Booking booking : bookings) {
            bookingTableModel.addRow(new Object[]{booking.getBookingId(), booking.getCar().getCarId(),
                    booking.getCar().getBrand(), booking.getCar().getModel(),
                    booking.getStartDate(), booking.getEndDate()});
        }

        bookingTable.setModel(bookingTableModel);
    }

    private void addCar() {
        String brand = JOptionPane.showInputDialog(frame, "Enter the brand of the car:", "Add Car",
                JOptionPane.PLAIN_MESSAGE);
        String model = JOptionPane.showInputDialog(frame, "Enter the model of the car:", "Add Car",
                JOptionPane.PLAIN_MESSAGE);

        int carId = carRentalSystem.getCars().size() + 1;
        Car car = new Car(carId, brand, model);
        carRentalSystem.addCar(car);
        refreshCarTable();
    }

    private void bookCar() {
        int selectedCarIndex = carTable.getSelectedRow();
        if (selectedCarIndex == -1) {
            JOptionPane.showMessageDialog(frame, "Please select a car from the table.", "Book Car",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        Car selectedCar = carRentalSystem.getCars().get(selectedCarIndex);
        LocalDate startDate = LocalDate.parse(JOptionPane.showInputDialog(frame,
                "Enter the start date (yyyy-MM-dd) for the booking:", "Book Car", JOptionPane.PLAIN_MESSAGE));
        LocalDate endDate = LocalDate.parse(JOptionPane.showInputDialog(frame,
                "Enter the end date (yyyy-MM-dd) for the booking:", "Book Car", JOptionPane.PLAIN_MESSAGE));

        Booking booking = carRentalSystem.bookCar(selectedCar, startDate, endDate);
        if (booking != null) {
            JOptionPane.showMessageDialog(frame, "Booking successful. Booking ID: " + booking.getBookingId(),
                    "Book Car", JOptionPane.INFORMATION_MESSAGE);
            refreshCarTable();
            refreshBookingTable();
        } else {
            JOptionPane.showMessageDialog(frame, "Car is not available for the given dates.", "Book Car",
                    JOptionPane.WARNING_MESSAGE);
        }
    }

    private void cancelBooking() {
        int selectedBookingIndex = bookingTable.getSelectedRow();
        if (selectedBookingIndex == -1) {
            JOptionPane.showMessageDialog(frame, "Please select a booking from the table.", "Cancel Booking",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        Booking selectedBooking = carRentalSystem.getBookings().get(selectedBookingIndex);
        carRentalSystem.cancelBooking(selectedBooking);
        JOptionPane.showMessageDialog(frame, "Booking canceled successfully.", "Cancel Booking",
                JOptionPane.INFORMATION_MESSAGE);
        refreshCarTable();
        refreshBookingTable();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                CarRentalManagementSystemGUI carRentalGUI = new CarRentalManagementSystemGUI();
                carRentalGUI.frame.setVisible(true);
            }
        });
    }
}
