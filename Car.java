public class Car {
    // Car attributes
    private int id;
    private String make, model, engineType;
    private int year;
    private float topSpeed, basePrice;
    private boolean isElectric;

    // Constructor
    public Car(String make, String model, int year, String engineType,
               float topSpeed, float basePrice, boolean isElectric) {
        this.make = make;
        this.model = model;
        this.year = year;
        this.engineType = engineType;
        this.topSpeed = topSpeed;
        this.basePrice = basePrice;
        this.isElectric = isElectric;
    }

    // Update car details
    public boolean setDetails(String make, String model, int year, String engineType,
                              float topSpeed, float basePrice, boolean isElectric) {
        this.make = make;
        this.model = model;
        this.year = year;
        this.engineType = engineType;
        this.topSpeed = topSpeed;
        this.basePrice = basePrice;
        this.isElectric = isElectric;
        return true;
    }
    // Get car details
    public String getDetails() {
        return String.format("[%d] %s %s (%d) - %s, %.1f mph, $%.2f, %s",
                id, make, model, year, engineType, topSpeed, basePrice,
                isElectric ? "Electric" : "Gasoline");
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getMake() { return make; }
    public String getModel() { return model; }
    public int getYear() { return year; }
    public String getEngineType() { return engineType; }
    public float getTopSpeed() { return topSpeed; }
    public float getBasePrice() { return basePrice; }
    public boolean getIsElectric() { return isElectric; }
}
