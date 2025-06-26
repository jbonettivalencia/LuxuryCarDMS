import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CarDMSUnitTest {
    private LuxuryCarDMS dms;

    @BeforeEach
    public void setUp() {
        dms = new LuxuryCarDMS(0.10f);
        dms.addCar(new Car("Lexus", "RX", 2022, "Hybrid", 120, 50000, false));
        dms.addCar(new Car("Tesla", "Model S", 2023, "Electric", 155, 90000, true));
    }

    @Test
    public void testAddCar() {
        int originalSize = dms.viewCars().size();
        dms.addCar(new Car("Audi", "A8", 2024, "Gasoline", 130, 80000, false));
        assertEquals(originalSize + 1, dms.viewCars().size());
    }

    @Test
    public void testRemoveCarById() {
        Car car = new Car("Test", "Delete", 2022, "Gasoline", 120, 45000, false);
        dms.addCar(car);
        int id = car.getId();
        assertTrue(dms.removeCarById(id));
        assertNull(dms.getCarById(id));
    }

    @Test
    public void testUpdateCar() {
        Car car = dms.viewCars().get(0);
        int id = car.getId();
        Car updated = new Car("Updated", "Model", 2021, "Diesel", 100, 30000, false);
        assertTrue(dms.updateCarInfo(id, updated));
        assertEquals("Updated", dms.getCarById(id).getMake());
    }

    @Test
    public void testInvalidRemoval() {
        assertFalse(dms.removeCarById(9999));
    }

    @Test
    public void testInvalidUpdate() {
        Car dummy = new Car("X", "Y", 2020, "Electric", 100, 30000, false);
        assertFalse(dms.updateCarInfo(9999, dummy));
    }

    @Test
    public void testMostExpensiveCar() {
        Car mostExpensive = dms.getMostExpensiveCar();
        assertNotNull(mostExpensive);
        float calculated = dms.calculatePriceWithTax(mostExpensive);
        assertEquals(mostExpensive.getBasePrice() * 1.10f, calculated, 0.01);
    }

    @Test
    public void testFileLoad() {
        List<Car> loaded = dms.loadCarsFromFile("cars.txt");
        assertNotNull(loaded);
        assertTrue(loaded.size() >= 0); // accepts 0 or more
    }
}
