import java.util.*;
import java.io.*;

public class LuxuryCarDMS {
    private List<Car> carList = new ArrayList<>();
    private float taxRate;
    private int nextId = 1;

    // Constructor
    public LuxuryCarDMS(float taxRate) {
        this.taxRate = taxRate;
    }

    public List<Car> loadCarsFromFile(String filename) {
        List<Car> loadedCars = new ArrayList<>();
        try (Scanner fileScanner = new Scanner(new File(filename))) {
            while (fileScanner.hasNextLine()) {
                String[] data = fileScanner.nextLine().split(",");
                if (data.length == 7) {
                    Car car = new Car(data[0], data[1], Integer.parseInt(data[2]),
                            data[3], Float.parseFloat(data[4]),
                            Float.parseFloat(data[5]), Boolean.parseBoolean(data[6]));
                    car.setId(nextId++);
                    carList.add(car);
                    loadedCars.add(car);
                }
            }
        } catch (Exception e) {
            System.out.println("Error loading file: " + e.getMessage());
        }
        return loadedCars;
    }

    // Add a new car to the system
    public boolean addCar(Car car) {
        car.setId(nextId++);
        return carList.add(car);
    }

    // View all cars
    public List<Car> viewCars() {
        return carList;
    }

    // Remove a car from the system
    public boolean removeCarById(int id) {
        return carList.removeIf(car -> car.getId() == id);
    }

    // Update an existing car's details
    public boolean updateCarInfo(int id, Car updatedData) {
        for (Car car : carList) {
            if (car.getId() == id) {
                return car.setDetails(
                        updatedData.getMake(), updatedData.getModel(), updatedData.getYear(),
                        updatedData.getEngineType(), updatedData.getTopSpeed(),
                        updatedData.getBasePrice(), updatedData.getIsElectric());
            }
        }
        return false;
    }
    // Get most expensive car with tax
    public String showMostExpensiveWithTax() {
        if (carList.isEmpty()) return "No cars available.";

        Car mostExpensive = Collections.max(carList, Comparator.comparing(Car::getBasePrice));
        float finalPrice = mostExpensive.getBasePrice() * (1 + taxRate);
        return String.format("Most Expensive Car: %s\nPrice with tax: $%.2f",
                mostExpensive.getDetails(), finalPrice);
    }
    // Return the most expensive car
    public Car getMostExpensiveCar() {
        if (carList.isEmpty()) return null;

        Car max = carList.get(0);
        for (Car c : carList) {
            if (c.getBasePrice() > max.getBasePrice()) {
                max = c;
            }
        }
        return max;
    }

    // Calculate price with tax
    public float calculatePriceWithTax(Car car) {
        return car.getBasePrice() * (1 + taxRate);
    }

    public Car getCarById(int id) {
        for (Car car : carList) {
            if (car.getId() == id) return car;
        }
        return null;
    }
}


