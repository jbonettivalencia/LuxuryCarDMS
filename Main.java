import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LuxuryCarDMS dms = new LuxuryCarDMS(0.1f); // 10% tax

        while (true) {
            // Display the menu
            System.out.println("\nLuxury Car DMS Menu:");
            System.out.println("1. Load cars from file");
            System.out.println("2. Display all cars");
            System.out.println("3. Add a new car");
            System.out.println("4. Update a car");
            System.out.println("5. Remove a car");
            System.out.println("6. Show most expensive car with tax");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");

            String input = scanner.nextLine();
            switch (input) {
                case "1":
                    System.out.print("Enter file path: ");
                    dms.loadFromFile(scanner.nextLine());
                    break;
                case "2":
                    for (Car car : dms.viewCars()) {
                        System.out.println(car.getDetails());
                    }
                    break;
                case "3":
                    try {
                        System.out.print("Enter make, model, year, engine type, top speed, base price, is electric (true/false): ");
                        String[] parts = scanner.nextLine().split(",");
                        if (parts.length != 7) throw new Exception();
                        Car car = new Car(parts[0].trim(), parts[1].trim(), Integer.parseInt(parts[2].trim()), parts[3].trim(),
                                Float.parseFloat(parts[4].trim()), Float.parseFloat(parts[5].trim()), Boolean.parseBoolean(parts[6].trim()));
                        dms.addCar(car);
                        System.out.println("Car added successfully.");
                    } catch (Exception e) {
                        System.out.println("Invalid input. Try again.");
                    }
                    break;
                case "4":
                    System.out.print("Enter make of car to update: ");
                    String makeToUpdate = scanner.nextLine();
                    for (Car car : dms.viewCars()) {
                        if (car.getMake().equalsIgnoreCase(makeToUpdate)) {
                            System.out.print("Enter updated details (make, model, year, engine type, top speed, base price, is electric): ");
                            if (dms.updateCarInfo(car, scanner.nextLine()))
                                System.out.println("Car updated.");
                            else
                                System.out.println("Update failed.");
                        }
                    }
                    break;
                case "5":
                    System.out.print("Enter make of car to remove: ");
                    String makeToRemove = scanner.nextLine();
                    dms.viewCars().removeIf(car -> car.getMake().equalsIgnoreCase(makeToRemove));
                    System.out.println("Car removed if it existed.");
                    break;
                case "6":
                    dms.showMostExpensiveWithTax();
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