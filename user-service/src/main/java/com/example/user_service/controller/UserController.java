/*
package com.example.user_service.controller;

public class UserController {
}
*/

//package com.example.user_service.controller;

/*
import com.example.user_service.entity.User;
import com.example.user_service.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping
    public User saveUser(@RequestBody User user) {
        return service.saveUser(user);
    }

    @GetMapping
    public List<User> getUsers() {
        return service.getAllUsers();
    }
}

 */

package com.example.user_service.controller;

import com.example.user_service.dto.LoginRequest;
import com.example.user_service.dto.UserRequest;
import com.example.user_service.dto.UserResponse;
import com.example.user_service.service.UserService;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping
    public UserResponse saveUser(@Valid @RequestBody UserRequest request) {
        return service.saveUser(request);
    }

    @GetMapping
    public List<UserResponse> getUsers() {
        return service.getAllUsers();
    }

    @PostMapping("/login")
    public String login(
            @RequestBody LoginRequest request) {

        return service.login(request);
    }

    @GetMapping("/profile")
    public String profile() {
        return "Protected Profile API";
    }






}