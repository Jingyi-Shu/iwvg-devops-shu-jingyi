package es.upm.miw.devops.user;

import es.upm.miw.devops.rest.exceptionshandler.ForbiddenException;
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

    // 9 Task: Update user data (PUT /users/{id})
    public UserDto update(String id, UserDto userDto) {
        User user = this.read(id);
        user.setFirstName(userDto.getFirstName());
        user.setFamilyName(userDto.getFamilyName());
        user.setEmail(userDto.getEmail());
        user.setActive(userDto.getActive());
        return new UserDto(user);
    }

    // 10 Task: PATCH /user body:[{id,active}]
    //public void updateActiveList(List<UserActiveDto> userActiveDtoList) {
    //    userActiveDtoList.forEach(userActiveDto -> {
    //        User user = this.read(userActiveDto.getId());
    //        user.setActive(userActiveDto.getActive());
    //    });
    //}

    // 7. Bug fix: 禁止禁用 ID 为 "1" 的主用户
    public void updateActiveList(List<UserActiveDto> userActiveDtoList) {
        userActiveDtoList.forEach(userActiveDto -> {
            if ("1".equals(userActiveDto.getId()) && Boolean.FALSE.equals(userActiveDto.getActive())) {
                throw new ForbiddenException("Cannot deactivate primary user: " + userActiveDto.getId());
            }
            User user = this.read(userActiveDto.getId());
            user.setActive(userActiveDto.getActive());
        });
    }
}