/**
 * DatabaseManager handles all SQL database operations,
 * including connecting to the database and performs CRUD actions.
 * This class does the database handling from the GUI and main logic.
 */
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {
    /** Path to the SQL database. */
    private String databasePath;
    /**
     * Constructs a DatabaseManager with the provided database path.
     *
     * @param databasePath the path to the SQL database
     */
    public DatabaseManager(String databasePath) {
        this.databasePath = databasePath;
    }
    /**
     * Attempts to connect to the SQL database.
     *
     * @return an active connection to the database
     * @throws SQLException if a database access error occurs
     */
    private Connection connect() throws SQLException {
        return DriverManager.getConnection("jdbc:sqlite:" + databasePath);
    }
    /**
     * Loads all cars from the database.
     *
     * @return a List of Car objects populated from the database
     * @throws SQLException if a database error occurs
     */
    public List<Car> loadCars() throws SQLException {
        List<Car> cars = new ArrayList<>();
        String sql = "SELECT * FROM cars";

        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Car car = new Car(
                        rs.getString("make"),
                        rs.getString("model"),
                        rs.getInt("year"),
                        rs.getString("fuelType"),
                        rs.getDouble("topSpeed"),
                        rs.getDouble("price"),
                        rs.getBoolean("isElectric")
                );
                car.setId(rs.getInt("id"));
                cars.add(car);
            }
        }

        return cars;
    }
    /**
     * Adds a new Car record to the database.
     *
     * @param car the Car object to be inserted
     * @throws SQLException if a database insert error
     */
    public void addCar(Car car) throws SQLException {
        String sql = "INSERT INTO cars(make, model, year, fuelType, topSpeed, price, isElectric) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, car.getMake());
            pstmt.setString(2, car.getModel());
            pstmt.setInt(3, car.getYear());
            pstmt.setString(4, car.getFuelType());
            pstmt.setDouble(5, car.getTopSpeed());
            pstmt.setDouble(6, car.getPrice());
            pstmt.setBoolean(7, car.isElectric());
            pstmt.executeUpdate();
        }
    }
    /**
     * Updates an existing Car in the database.
     *
     * @param car the Car object containing updated values
     * @throws SQLException if a database update error occurs
     */
    public void updateCar(Car car) throws SQLException {
        String sql = "UPDATE cars SET make=?, model=?, year=?, fuelType=?, topSpeed=?, price=?, isElectric=? WHERE id=?";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, car.getMake());
            pstmt.setString(2, car.getModel());
            pstmt.setInt(3, car.getYear());
            pstmt.setString(4, car.getFuelType());
            pstmt.setDouble(5, car.getTopSpeed());
            pstmt.setDouble(6, car.getPrice());
            pstmt.setBoolean(7, car.isElectric());
            pstmt.setInt(8, car.getId());
            pstmt.executeUpdate();
        }
    }
    /**
     * Deletes a Car record from the database by its ID.
     *
     * @param carId the unique ID of the Car to delete
     * @throws SQLException if a database deletion error occurs
     */
    public void deleteCar(int carId) throws SQLException {
        String sql = "DELETE FROM cars WHERE id=?";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, carId);
            pstmt.executeUpdate();
        }
    }

    public Car getMostExpensiveCar() throws SQLException {
        String sql = "SELECT * FROM cars ORDER BY price DESC LIMIT 1";
        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            if (rs.next()) {
                Car car = new Car(
                        rs.getString("make"),
                        rs.getString("model"),
                        rs.getInt("year"),
                        rs.getString("fuelType"),
                        rs.getDouble("topSpeed"),
                        rs.getDouble("price"),
                        rs.getBoolean("isElectric")
                );
                car.setId(rs.getInt("id"));
                return car;
            }
        }
        return null;
    }
}
