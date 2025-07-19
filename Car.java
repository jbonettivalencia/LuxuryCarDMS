/**
 * Represents a luxury car in the inventory system.
 * Holds all car details.
 */
public class Car {
    private int id;
    private String make;
    private String model;
    private int year;
    private String fuelType;
    private double topSpeed;
    private double price;
    private boolean isElectric;
    /**
     * Constructs a new Car object.
     * @param make The make of the car.
     * @param model The model of the car.
     * @param year The production year.
     * @param fuelType The fuel type.
     * @param topSpeed The top speed in mph.
     * @param price The price in USD.
     * @param isElectric if the car is electric or not.
     */
    public Car(String make, String model, int year, String fuelType, double topSpeed, double price, boolean isElectric) {
        this.make = make;
        this.model = model;
        this.year = year;
        this.fuelType = fuelType;
        this.topSpeed = topSpeed;
        this.price = price;
        this.isElectric = isElectric;
    }
    /** @return The car's database ID. */
    public int getId() { return id; }
    /** @param id Set the database ID. */
    public void setId(int id) { this.id = id; }

    public String getMake() { return make; }
    public String getModel() { return model; }
    public int getYear() { return year; }
    public String getFuelType() { return fuelType; }
    public double getTopSpeed() { return topSpeed; }
    public double getPrice() { return price; }
    public boolean isElectric() { return isElectric; }

    public void setMake(String make) { this.make = make; }
    public void setModel(String model) { this.model = model; }
    public void setYear(int year) { this.year = year; }
    public void setFuelType(String fuelType) { this.fuelType = fuelType; }
    public void setTopSpeed(double topSpeed) { this.topSpeed = topSpeed; }
    public void setPrice(double price) { this.price = price; }
    public void setElectric(boolean electric) { isElectric = electric; }

    public String getDetails() {
        return String.format(
                "ID: %d | %s %s | Year: %d | Fuel: %s | Top Speed: %.1f mph | Price: $%.2f | Electric: %s",
                id, make, model, year, fuelType, topSpeed, price, isElectric ? "Yes" : "No"
        );
    }

}
