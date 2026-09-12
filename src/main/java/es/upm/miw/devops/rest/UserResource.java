package es.upm.miw.devops.rest;

import es.upm.miw.devops.user.User;
import es.upm.miw.devops.user.UserDto;
import es.upm.miw.devops.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(UserResource.USERS)
public class UserResource {
    public static final String USERS = "/user";

    private final UserService userService;

    @Autowired
    public UserResource(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public User read(@PathVariable String id) {
        return this.userService.read(id);
    }

    // Task 2️⃣: GET /user/search?billable=true
    @GetMapping("/search")
    public List<UserDto> search(@RequestParam(required = false) Boolean billable) {
        return this.userService.search(billable);
    }

    // 3️⃣ Task: DELETE /user/{id}
    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        this.userService.delete(id);
    }

    // 6️⃣ Task: PUT /user/{id}/active
    @PutMapping("/{id}/active")
    public User updateActive(@PathVariable String id, @RequestBody UserActiveDto userActiveDto) {
        return this.userService.updateActive(id, userActiveDto.getActive());
    }
}