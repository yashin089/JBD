package com.example.jbd.controller;

import com.example.jbd.repository.User;
import com.example.jbd.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path = "api/users")
public class UserController {

    private final UserService userService;

    @GetMapping
    public List<User> findAll() {
        return userService.findAll();
    }

    @PostMapping
    public User create(@RequestBody User user) {
        return userService.create(user);
    }

    @DeleteMapping(path = "{id}")
    public void delete(@PathVariable Long id) {
        userService.delete(id);
    }

    @PutMapping(path = "{id}")
    public void update(
            @PathVariable Long id,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email) {
        userService.update(id, name, email);
    }
}
