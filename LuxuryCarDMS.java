import java.util.*;
import java.io.*;

public class LuxuryCarDMS {
    private List<Car> carList = new ArrayList<>();
    private float taxRate;

    // Constructor
    public LuxuryCarDMS(float taxRate) {
        this.taxRate = taxRate;
    }

    // Add a new car to the system
    public boolean addCar(Car car) {
        return carList.add(car);
    }

    // View all cars
    public List<Car> viewCars() {
        return carList;
    }

    // Remove a car from the system
    public boolean removeCar(Car car) {
        return carList.remove(car);
    }

    // Update an existing car's details
    public boolean updateCarInfo(Car car, String updatedDetails) {
        if (!carList.contains(car)) return false;
        String[] parts = updatedDetails.split(",");
        if (parts.length != 7) return false;
        try {
            return car.setDetails(
                    parts[0].trim(),
                    parts[1].trim(),
                    Integer.parseInt(parts[2].trim()),
                    parts[3].trim(),
                    Float.parseFloat(parts[4].trim()),
                    Float.parseFloat(parts[5].trim()),
                    Boolean.parseBoolean(parts[6].trim())
            );
        } catch (Exception e) {
            return false;
        }
    }

    // Calculate final price with tax
    public float calculateFinalPrice(Car car) {
        return car.getBasePrice() * (1 + taxRate);
    }

    // Load cars from a file
    public void loadFromFile(String filePath) {
        try (Scanner scanner = new Scanner(new File(filePath))) {
            while (scanner.hasNextLine()) {
                String[] parts = scanner.nextLine().split(",");
                if (parts.length == 7) {
                    addCar(new Car(
                            parts[0].trim(),
                            parts[1].trim(),
                            Integer.parseInt(parts[2].trim()),
                            parts[3].trim(),
                            Float.parseFloat(parts[4].trim()),
                            Float.parseFloat(parts[5].trim()),
                            Boolean.parseBoolean(parts[6].trim())
                    ));
                }
            }
        } catch (Exception e) {
            System.out.println("Error loading file: " + e.getMessage());
        }
    }

    // Get most expensive car with tax
    public void showMostExpensiveWithTax() {
        if (carList.isEmpty()) {
            System.out.println("No cars in inventory.");
            return;
        }
        Car max = Collections.max(carList, Comparator.comparing(Car::getBasePrice));
        System.out.println("Most expensive car: " + max.getDetails());
        System.out.println("Price with tax: $" + calculateFinalPrice(max));
    }
}

