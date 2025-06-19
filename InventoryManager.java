public class InventoryManager {
    private String username;
    private String password;

    public InventoryManager(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Simple login
    public boolean login(String u, String p) {
        return username.equals(u) && password.equals(p);
    }

    // Placeholder for managing cars
    public boolean manageCars() {
        return true;
    }
}


