package es.upm.miw.devops.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final DatabaseSeeder databaseSeeder;

    @Autowired
    public UserService(DatabaseSeeder databaseSeeder) {
        this.databaseSeeder = databaseSeeder;
    }

    public User read(String id) {
        return this.databaseSeeder.getUsers().stream()
                .filter(user -> user.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User id not found: " + id));
    }

    // Task 2️⃣: 搜索接口（根据 billable 筛选，参数为 null 时返回全部）
    public List<UserDto> search(Boolean billable) {
        return this.databaseSeeder.getUsers().stream()
                .filter(user -> billable == null || user.isBillable().equals(billable))
                .map(UserDto::new)
                .collect(Collectors.toList());
    }

    // 3️⃣ Task: DELETE /user/{id}
    public void delete(String id) {
        User user = this.read(id);
        this.databaseSeeder.getUsers().remove(user);
    }

    // 6️⃣ Task: Update active status
    public User updateActive(String id, Boolean active) {
        User user = this.read(id);
        user.setActive(active);
        return user;
    }
}