package es.upm.miw.devops.rest;

import es.upm.miw.devops.user.User;
import es.upm.miw.devops.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(UserResource.USERS)
public class UserResource {
    public static final String USERS = "/user";

    private final UserService userService;

    @Autowired
    public UserResource(UserService userService) {
        this.userService = userService;
    }

    // 1️⃣ Task: GET /user/{id} Endpoint
    @GetMapping("/{id}")
    public User read(@PathVariable String id) {
        return this.userService.read(id);
    }
}