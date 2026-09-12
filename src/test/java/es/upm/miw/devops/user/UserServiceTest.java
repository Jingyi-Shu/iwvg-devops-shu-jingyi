package es.upm.miw.devops.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private DatabaseSeeder databaseSeeder;

    @BeforeEach
    void resetDatabase() {
        this.databaseSeeder.seed();
    }

    @Test
    void testDeleteUser() {
        this.userService.delete("1");
        assertThrows(ResponseStatusException.class, () -> this.userService.read("1"));
    }
}