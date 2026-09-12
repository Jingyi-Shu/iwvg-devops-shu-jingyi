package es.upm.miw.devops.user;

import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class DatabaseSeeder {
    private final List<User> users = new ArrayList<>();

    public DatabaseSeeder() {
        this.seed();
    }

    public void seed() {
        this.users.clear();
        this.users.add(new User("1", "John", "Doe", "john@example.com", "12345678A",
                "Main St 1", "Madrid", "Madrid", "28001", true));
        this.users.add(new User("2", "Jane", "Smith", "jane@example.com", "87654321B",
                null, "", "Madrid", "28002", false));
        this.users.add(new User("3", "Alice", "Wonder", "alice@example.com", "11223344C",
                "High St 5", "Barcelona", "Barcelona", "08001", true));
    }

    public List<User> getUsers() {
        return this.users;
    }
}