import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class LuxuryCarDMSGUI {
    private LuxuryCarDMS dms;
    private JFrame frame;
    private JTable table;
    private DefaultTableModel tableModel;

    public LuxuryCarDMSGUI() {
        dms = new LuxuryCarDMS(0.10f);  // 10% tax rate
        initGUI();
    }

    private void initGUI() {
        frame = new JFrame("Luxury Car DMS");
        frame.setSize(1000, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Table Setup
        tableModel = new DefaultTableModel(new String[]{
                "ID", "Make", "Model", "Year", "Engine Type",
                "Top Speed", "Base Price", "Is Electric"
        }, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(scrollPane, BorderLayout.CENTER);

        // Buttons
        JPanel buttonPanel = new JPanel();

        JButton loadButton = new JButton("Load from File");
        loadButton.addActionListener(e -> loadCarsFromFile());

        JButton addButton = new JButton("Add Car");
        addButton.addActionListener(e -> addCar());

        JButton updateButton = new JButton("Update Car");
        updateButton.addActionListener(e -> updateCar());

        JButton removeButton = new JButton("Remove Car");
        removeButton.addActionListener(e -> removeCar());

        JButton mostExpensiveButton = new JButton("Most Expensive Car with Tax");
        mostExpensiveButton.addActionListener(e -> showMostExpensive());

        buttonPanel.add(loadButton);
        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(mostExpensiveButton);

        frame.add(buttonPanel, BorderLayout.SOUTH);
        frame.setVisible(true);
    }

    private void refreshTable() {
        tableModel.setRowCount(0);
        for (Car car : dms.viewCars()) {
            tableModel.addRow(new Object[]{
                    car.getId(), car.getMake(), car.getModel(), car.getYear(),
                    car.getEngineType(), car.getTopSpeed(),
                    car.getBasePrice(), car.getIsElectric()
            });
        }
    }

    private void loadCarsFromFile() {
        try {
            dms.loadCarsFromFile("cars.txt");
            refreshTable();
            JOptionPane.showMessageDialog(frame, "Cars loaded successfully.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, "Failed to load cars: " + e.getMessage());
        }
    }

    private void addCar() {
        try {
            String make = JOptionPane.showInputDialog("Enter make:");
            String model = JOptionPane.showInputDialog("Enter model:");
            int year = Integer.parseInt(JOptionPane.showInputDialog("Enter year:"));
            String engineType = JOptionPane.showInputDialog("Enter engine type:");
            float topSpeed = Float.parseFloat(JOptionPane.showInputDialog("Enter top speed:"));
            float basePrice = Float.parseFloat(JOptionPane.showInputDialog("Enter base price:"));
            boolean isElectric = Boolean.parseBoolean(JOptionPane.showInputDialog("Is electric (true/false):"));

            Car newCar = new Car(make, model, year, engineType, topSpeed, basePrice, isElectric);
            dms.addCar(newCar);
            refreshTable();
            JOptionPane.showMessageDialog(frame, "Car added successfully.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, "Error adding car: " + e.getMessage());
        }
    }

    private void updateCar() {
        try {
            int id = Integer.parseInt(JOptionPane.showInputDialog("Enter ID of car to update:"));
            String make = JOptionPane.showInputDialog("Enter new make:");
            String model = JOptionPane.showInputDialog("Enter new model:");
            int year = Integer.parseInt(JOptionPane.showInputDialog("Enter new year:"));
            String engineType = JOptionPane.showInputDialog("Enter new engine type:");
            float topSpeed = Float.parseFloat(JOptionPane.showInputDialog("Enter new top speed:"));
            float basePrice = Float.parseFloat(JOptionPane.showInputDialog("Enter new base price:"));
            boolean isElectric = Boolean.parseBoolean(JOptionPane.showInputDialog("Is electric (true/false):"));

            Car updatedCar = new Car(make, model, year, engineType, topSpeed, basePrice, isElectric);
            boolean success = dms.updateCarInfo(id, updatedCar);

            if (success) {
                refreshTable();
                JOptionPane.showMessageDialog(frame, "Car updated successfully.");
            } else {
                JOptionPane.showMessageDialog(frame, "Car with ID " + id + " not found.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, "Error updating car: " + e.getMessage());
        }
    }

    private void removeCar() {
        try {
            int id = Integer.parseInt(JOptionPane.showInputDialog("Enter ID of car to remove:"));
            boolean removed = dms.removeCarById(id);
            if (removed) {
                refreshTable();
                JOptionPane.showMessageDialog(frame, "Car removed successfully.");
            } else {
                JOptionPane.showMessageDialog(frame, "Car with ID " + id + " not found.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, "Error removing car: " + e.getMessage());
        }
    }

    private void showMostExpensive() {
        Car car = dms.getMostExpensiveCar();
        if (car != null) {
            float priceWithTax = dms.calculatePriceWithTax(car);
            JOptionPane.showMessageDialog(frame,
                    "Most Expensive Car: " + car.getDetails() +
                            "\nPrice with tax: $" + String.format("%.2f", priceWithTax));
        } else {
            JOptionPane.showMessageDialog(frame, "No cars in system.");
        }
    }

    // Entry point launch GUI
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LuxuryCarDMSGUI());
    }
}
