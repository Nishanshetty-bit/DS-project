import java.util.HashMap;

public class CriminalDatabase {
    private HashMap<String, Criminal> database;

    public CriminalDatabase() {
        this.database = new HashMap<>();
    }

    // Adds a criminal to the database
    public void addCriminal(Criminal criminal) {
        if (criminal == null || database.containsKey(criminal.getId())) {
            throw new IllegalArgumentException("Criminal cannot be null and ID must be unique.");
        }
        database.put(criminal.getId(), criminal);
    }

    // Retrieves a criminal by ID
    public Criminal getCriminal(String id) {
        if (id == null || !database.containsKey(id)) {
            throw new IllegalArgumentException("Criminal ID cannot be null and must exist in the database.");
        }
        return database.get(id);
    }

    // Optional: Method to remove a criminal from the database
    public void removeCriminal(String id) {
        if (id == null || !database.containsKey(id)) {
            throw new IllegalArgumentException("Criminal ID cannot be null and must exist in the database.");
        }
        database.remove(id);
    }

    // Optional: Method to check if a criminal exists
    public boolean exists(String id) {
        return database.containsKey(id);
    }
}
