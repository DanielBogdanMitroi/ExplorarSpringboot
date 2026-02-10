package com.example.accessingdatamysql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping(path="/api/users")
public class MainController {
    @Autowired
    private UserRepository userRepository;

    // CREATE - Add a new user
    @PostMapping(path="/add")
    public @ResponseBody ResponseEntity<String> addNewUser (@RequestParam String name, @RequestParam String email) {
        User n = new User();
        n.setName(name);
        n.setEmail(email);
        userRepository.save(n);
        return ResponseEntity.status(HttpStatus.CREATED).body("User created successfully");
    }

    // READ - Get all users
    @GetMapping(path="/all")
    public @ResponseBody Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    // READ - Get a user by ID
    @GetMapping(path="/{id}")
    public @ResponseBody ResponseEntity<?> getUserById(@PathVariable Integer id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }

    // UPDATE - Update a user by ID
    @PutMapping(path="/{id}")
    public @ResponseBody ResponseEntity<String> updateUser(@PathVariable Integer id, 
                                                           @RequestParam(required = false) String name, 
                                                           @RequestParam(required = false) String email) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (name != null) {
                user.setName(name);
            }
            if (email != null) {
                user.setEmail(email);
            }
            userRepository.save(user);
            return ResponseEntity.ok("User updated successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }

    // DELETE - Delete a user by ID
    @DeleteMapping(path="/{id}")
    public @ResponseBody ResponseEntity<String> deleteUser(@PathVariable Integer id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            userRepository.deleteById(id);
            return ResponseEntity.ok("User deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }
}
