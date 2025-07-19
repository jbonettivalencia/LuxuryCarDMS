import javax.swing.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.List;
/**
 * LuxuryCarDMSGUI provides the gui for the Luxury Car Management System.
 * It allows the user to perform CRUD operations and display the most expensive car with tax.
 * Users interact with buttons to manage the inventory of luxury cars.
 */
public class LuxuryCarDMSGUI extends JFrame {
    private DatabaseManager dbManager;
    /**
     * Constructs the LuxuryCarDMSGUI, initializing the database connection
     * and setting up the GUI components.
     */
    public LuxuryCarDMSGUI() {
        String dbPath = JOptionPane.showInputDialog("Enter database file name (e.g., cars.db):");
        dbManager = new DatabaseManager(dbPath);

        JButton displayCarsButton = new JButton("Display Cars");
        JButton addCarButton = new JButton("Add Car");
        JButton deleteCarButton = new JButton("Delete Car");
        JButton updateCarButton = new JButton("Update Car");
        JButton customFeatureButton = new JButton("Show Most Expensive Car (+Tax)");

        JTextArea displayArea = new JTextArea(20, 60);
        JScrollPane scrollPane = new JScrollPane(displayArea);

        displayCarsButton.addActionListener(e -> displayCars(displayArea));
        addCarButton.addActionListener(e -> addCar(displayArea));
        deleteCarButton.addActionListener(e -> deleteCar(displayArea));
        updateCarButton.addActionListener(e -> updateCar(displayArea));
        customFeatureButton.addActionListener(e -> showMostExpensiveCar(displayArea));

        JPanel panel = new JPanel();
        panel.add(displayCarsButton);
        panel.add(addCarButton);
        panel.add(deleteCarButton);
        panel.add(updateCarButton);
        panel.add(customFeatureButton);

        add(panel, "North");
        add(scrollPane, "Center");

        setTitle("Luxury Car DMS");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }
    /**
     * Displays the list of cars currently in the database using a message dialog.
     *
     * @param displayArea caused when the button is clicked.
     */
    private void displayCars(JTextArea displayArea) {
        try {
            List<Car> cars = dbManager.loadCars();
            displayArea.setText("");
            for (Car car : cars) {
                displayArea.append(car.getDetails() + "\n");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error loading cars.");
        }
    }
    /**
     * Allows the user to add a new car to the database through input dialogs.
     *
     * @param displayArea caused when the button is clicked
     */
    private void addCar(JTextArea displayArea) {
        try {
            String make = inputString("Enter Make:");
            String model = inputString("Enter Model:");
            int year = inputInt("Enter Year:");
            String fuelType = inputString("Enter Fuel Type:");
            double topSpeed = inputDouble("Enter Top Speed:");
            double price = inputDouble("Enter Price:");
            boolean isElectric = JOptionPane.showConfirmDialog(this, "Is it electric?", "Electric", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;

            Car car = new Car(make, model, year, fuelType, topSpeed, price, isElectric);
            dbManager.addCar(car);
            JOptionPane.showMessageDialog(this, "Car added successfully!");

            displayCars(displayArea);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error adding car.");
        }
    }
    /**
     * Allows the user to delete a car by entering its ID.
     *
     * @param displayArea caused when the button is clicked.
     */
    private void deleteCar(JTextArea displayArea) {
        try {
            int id = inputInt("Enter ID of car to delete:");
            dbManager.deleteCar(id);
            JOptionPane.showMessageDialog(this, "Car deleted successfully!");

            displayCars(displayArea);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error deleting car.");
        }
    }
    /**
     * Allows the user to update an existing car by entering new details.
     *
     * @param displayArea caused when the button is clicked.
     */
    private void updateCar(JTextArea displayArea) {
        try {
            int id = inputInt("Enter ID of car to update:");
            String make = inputString("Enter New Make:");
            String model = inputString("Enter New Model:");
            int year = inputInt("Enter New Year:");
            String fuelType = inputString("Enter New Fuel Type:");
            double topSpeed = inputDouble("Enter New Top Speed:");
            double price = inputDouble("Enter New Price:");
            boolean isElectric = JOptionPane.showConfirmDialog(this, "Is it electric?", "Electric", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;

            Car car = new Car(make, model, year, fuelType, topSpeed, price, isElectric);
            car.setId(id);
            dbManager.updateCar(car);

            JOptionPane.showMessageDialog(this, "Car updated successfully!");

            displayCars(displayArea);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error updating car.");
        }
    }
    /**
     * Displays the most expensive car with its total price including tax.
     *
     * @param displayArea caused when the button is clicked.
     */
    private void showMostExpensiveCar(JTextArea displayArea) {
        try {
            Car car = dbManager.getMostExpensiveCar();
            if (car != null) {
                double tax = car.getPrice() * 0.10;
                double total = car.getPrice() + tax;
                displayArea.setText("Most Expensive Car:\n" + car.getDetails() + "\nPrice with 10% tax: $" + String.format("%.2f", total));
            } else {
                displayArea.setText("No cars found.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error calculating most expensive car.");
        }
    }

    /**
     * Input validation method
     *
     * @param message The prompt message
     * @return The entered String value
     */
    private String inputString(String message) {
        String input;
        do {
            input = JOptionPane.showInputDialog(message);
        } while (input == null || input.trim().isEmpty());
        return input.trim();
    }
    /**
     * Input validation method
     *
     * @param message The prompt message.
     * @return The entered integer.
     */
    private int inputInt(String message) {
        while (true) {
            try {
                return Integer.parseInt(inputString(message));
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Invalid integer input.");
            }
        }
    }
    /**
     * Input validation method
     *
     * @param message The prompt message
     * @return The entered double value.
     */
    private double inputDouble(String message) {
        while (true) {
            try {
                return Double.parseDouble(inputString(message));
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Invalid decimal input.");
            }
        }
    }
    /**
     * Launches GUI application.
     *
     * @param args Command-line arguments (not used).
     */

    public static void main(String[] args) {
        new LuxuryCarDMSGUI();
    }
}
