package org.example.zad5;

import io.micrometer.common.util.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private final UserDummyRepository userRepository;

    public UserController(UserDummyRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/users")
    List<User> all() {
        System.out.println("Find all users");
        return userRepository.findAll();
    }

    @GetMapping("/users/{id}")
    ResponseEntity<User> one(@PathVariable Long id) {
        System.out.println("Find one user with id: " + id);
        User user = userRepository.findById(id);
        if (user != null) {
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

    }

    @PostMapping("/users")
    ResponseEntity<User> newEmployee(@RequestBody User newUser) {
        System.out.println("Create new user: " + newUser);
        if (StringUtils.isEmpty(newUser.getName()) || StringUtils.isEmpty(newUser.getLastName())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(userRepository.save(newUser));

    }

    @PutMapping("/users/{id}")
    ResponseEntity<User> replaceEmployee(@RequestBody User newUser, @PathVariable Long id) {
        System.out.println("Update user with id: " + id);
        newUser.setId(id);
        User userToUpdate = userRepository.findById(id);
        if (userToUpdate != null) {
            return ResponseEntity.ok(userRepository.save(newUser));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

    }

    @DeleteMapping("/users/{id}")
    ResponseEntity<String> deleteEmployee(@PathVariable Long id) {
        System.out.println("Delete user with id: " + id);
        User userToDelete = userRepository.findById(id);
        if (userToDelete != null) {
            userRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}