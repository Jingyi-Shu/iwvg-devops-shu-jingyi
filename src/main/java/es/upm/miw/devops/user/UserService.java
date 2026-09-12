package es.upm.miw.devops.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserService {
    private final DatabaseSeeder databaseSeeder;

    @Autowired
    public UserService(DatabaseSeeder databaseSeeder) {
        this.databaseSeeder = databaseSeeder;
    }

    // 1️⃣ Task: GET /user/{id} Service Implementation
    public User read(String id) {
        return this.databaseSeeder.getUsers().stream()
                .filter(user -> user.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User id not found: " + id));
    }
}