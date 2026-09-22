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

    // 5️⃣ Task: GET /user/{id} Service Test
    @Test
    void testReadUser() {
        User user = this.userService.read("1");
        assertNotNull(user);
        assertEquals("John", user.getFirstName());
    }

    @Test
    void testReadUserNotFound() {
        assertThrows(ResponseStatusException.class, () -> this.userService.read("999"));
    }

    @Test
    void testUpdateActive() {
        User user = this.userService.updateActive("2", true);
        assertTrue(user.getActive());
    }

    // 8️⃣ Task: Search Billable Service Test
    @Test
    void testSearchBillableUsers() {
        assertNotNull(this.userService.search(true));
        assertNotNull(this.userService.search(null));
    }

    // 9 Task: PUT /user/{id} Service Test
    @Test
    void testUpdateUser() {
        UserDto userDto = new UserDto();
        userDto.setFirstName("JohnUpdated");
        userDto.setFamilyName("DoeUpdated");
        userDto.setEmail("john_updated@example.com");
        userDto.setActive(false);

        UserDto updatedUser = this.userService.update("1", userDto);
        assertEquals("JohnUpdated", updatedUser.getFirstName());
        assertEquals("DoeUpdated", updatedUser.getFamilyName());
        assertEquals("john_updated@example.com", updatedUser.getEmail());
        assertFalse(updatedUser.getActive());
    }
}