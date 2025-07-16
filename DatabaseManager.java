import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {
    private String databasePath;

    public DatabaseManager(String databasePath) {
        this.databasePath = databasePath;
    }

    private Connection connect() throws SQLException {
        return DriverManager.getConnection("jdbc:sqlite:" + databasePath);
    }

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
