package com.rmznprhn.myApi.controller;

import com.rmznprhn.myApi.entity.User;
import com.rmznprhn.myApi.entity.enums.Role;
import com.rmznprhn.myApi.service.impl.UserServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserServiceImpl userService;

    public UserController(UserServiceImpl userService) {

        this.userService = userService;
    }

    @GetMapping
    ResponseEntity<Page<User>>getUsers(@RequestParam(defaultValue ="0") Integer page,
                                       @RequestParam(defaultValue = "10") Integer pageSize) {
        return ResponseEntity.ok(userService.getAll(PageRequest.of(page, pageSize, Sort.by("id"))));
    }
    @GetMapping("/by-role")
    ResponseEntity<List<User>> getUsers(@RequestParam Role role) {
        return ResponseEntity.ok(userService.getUserByRole(role));
    }
    @GetMapping("/{id}")
    ResponseEntity<User> getUser(@PathVariable Integer id) {

        return ResponseEntity.ok(userService.getById(id));
    }
    @PostMapping
    ResponseEntity<User> createUser(@RequestBody User user) {

        return ResponseEntity.ok(userService.save(user));
    }
    @DeleteMapping("/{id}")
    ResponseEntity<User> deleteUser(@PathVariable Integer id) {
        userService.delete(id);
        return ResponseEntity.ok().build();
    }
}
