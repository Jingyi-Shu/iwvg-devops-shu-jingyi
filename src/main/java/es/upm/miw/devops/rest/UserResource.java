package es.upm.miw.devops.rest;

import es.upm.miw.devops.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(UserResource.USERS)
public class UserResource {
    public static final String USERS = "/users";

    private final UserService userService;

    @Autowired
    public UserResource(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public Object read(@PathVariable String id) {
        return this.userService.read(id);
    }
}