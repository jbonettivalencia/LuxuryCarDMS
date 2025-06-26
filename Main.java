import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LuxuryCarDMS dms = new LuxuryCarDMS(0.10f); // 10% tax
        // Display the menu
        while (true) {
            System.out.println("\nLuxury Car DMS Menu");
            System.out.println("1. Load cars from file");
            System.out.println("2. Display all cars");
            System.out.println("3. Add a new car");
            System.out.println("4. Update a car");
            System.out.println("5. Remove a car");
            System.out.println("6. Show most expensive car with tax");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.print("Enter file path: ");
                    String filePath = scanner.nextLine();
                    try {
                        List<Car> loaded = dms.loadCarsFromFile(filePath);
                        System.out.println("Loaded " + loaded.size() + " cars from file.");
                    } catch (Exception e) {
                        System.out.println("Error: File not found.");
                    }
                    break;

                case "2":
                    List<Car> cars = dms.viewCars();
                    if (cars.isEmpty()) {
                        System.out.println("No cars in inventory.");
                    } else {
                        for (Car c : cars) {
                            System.out.println(c.getDetails());
                        }
                    }
                    break;

                case "3":
                    try {
                        System.out.print("Enter make: ");
                        String make = scanner.nextLine();
                        System.out.print("Enter model: ");
                        String model = scanner.nextLine();
                        System.out.print("Enter year: ");
                        int year = Integer.parseInt(scanner.nextLine());
                        System.out.print("Enter engine type: ");
                        String engine = scanner.nextLine();
                        System.out.print("Enter top speed (mph): ");
                        float speed = Float.parseFloat(scanner.nextLine());
                        System.out.print("Enter base price: ");
                        float price = Float.parseFloat(scanner.nextLine());
                        System.out.print("Is it electric (true/false): ");
                        boolean isElectric = Boolean.parseBoolean(scanner.nextLine());

                        Car newCar = new Car(make, model, year, engine, speed, price, isElectric);
                        dms.addCar(newCar);
                        System.out.println("Car added successfully.");
                    } catch (Exception e) {
                        System.out.println("Invalid input. Please try again.");
                    }
                    break;

                case "4":
                    try {
                        System.out.print("Enter ID of car to update: ");
                        int updateId = Integer.parseInt(scanner.nextLine());
                        System.out.print("Enter new make: ");
                        String newMake = scanner.nextLine();
                        System.out.print("Enter new model: ");
                        String newModel = scanner.nextLine();
                        System.out.print("Enter new year: ");
                        int newYear = Integer.parseInt(scanner.nextLine());
                        System.out.print("Enter new engine type: ");
                        String newEngine = scanner.nextLine();
                        System.out.print("Enter new top speed: ");
                        float newSpeed = Float.parseFloat(scanner.nextLine());
                        System.out.print("Enter new base price: ");
                        float newPrice = Float.parseFloat(scanner.nextLine());
                        System.out.print("Is it electric (true/false): ");
                        boolean newIsElectric = Boolean.parseBoolean(scanner.nextLine());

                        Car updatedData = new Car(newMake, newModel, newYear, newEngine, newSpeed, newPrice, newIsElectric);
                        boolean updated = dms.updateCarInfo(updateId, updatedData);
                        System.out.println(updated ? "Car updated." : "Car with given ID not found.");
                    } catch (Exception e) {
                        System.out.println("Invalid input. Update failed.");
                    }
                    break;

                case "5":
                    try {
                        System.out.print("Enter ID of car to remove: ");
                        int removeId = Integer.parseInt(scanner.nextLine());
                        boolean removed = dms.removeCarById(removeId);
                        System.out.println(removed ? "Car removed." : "Car with given ID not found.");
                    } catch (Exception e) {
                        System.out.println("Invalid input. Removal failed.");
                    }
                    break;

                case "6":
                    System.out.println(dms.showMostExpensiveWithTax());
                    break;

                case "7":
                    System.out.println("Exiting...");
                    return;

                default:
                    System.out.println("Invalid option.");
            }
        }
    }
}

