public class Car {
    // Car attributes
    private String make;
    private String model;
    private int year;
    private String engineType;
    private float topSpeed;
    private float basePrice;
    private boolean isElectric;

    // Constructor
    public Car(String make, String model, int year, String engineType, float topSpeed, float basePrice, boolean isElectric) {
        this.make = make;
        this.model = model;
        this.year = year;
        this.engineType = engineType;
        this.topSpeed = topSpeed;
        this.basePrice = basePrice;
        this.isElectric = isElectric;
    }

    // Get car details as string
    public String getDetails() {
        return make + ", " + model + ", " + year + ", " + engineType + ", " + topSpeed + "mph, $" + basePrice + (isElectric ? ", Electric" : ", Gasoline");
    }

    // Update car details
    public boolean setDetails(String make, String model, int year, String engineType, float topSpeed, float basePrice, boolean isElectric) {
        this.make = make;
        this.model = model;
        this.year = year;
        this.engineType = engineType;
        this.topSpeed = topSpeed;
        this.basePrice = basePrice;
        this.isElectric = isElectric;
        return true;
    }

    // Getters for validation and updates
    public String getMake() { return make; }
    public String getModel() { return model; }
    public int getYear() { return year; }
    public String getEngineType() { return engineType; }
    public float getTopSpeed() { return topSpeed; }
    public float getBasePrice() { return basePrice; }
    public boolean getIsElectric() { return isElectric; }
}

