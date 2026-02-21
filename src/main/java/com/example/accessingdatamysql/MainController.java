package com.example.accessingdatamysql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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

    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";

    // CREATE - Add a new user
    @PostMapping(path="/add")
    public @ResponseBody ResponseEntity<String> addNewUser(@RequestParam String name, @RequestParam String email) {
        if (name == null || name.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("El nombre es obligatorio y no puede estar vacío");
        }
        if (email == null || email.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("El email es obligatorio y no puede estar vacío");
        }
        if (name.trim().length() < 2) {
            return ResponseEntity.badRequest().body("El nombre debe tener al menos 2 caracteres");
        }
        if (name.trim().length() > 255 || email.trim().length() > 255) {
            return ResponseEntity.badRequest().body("Los campos no pueden exceder 255 caracteres");
        }
        if (!email.matches(EMAIL_REGEX)) {
            return ResponseEntity.badRequest().body("El formato del email no es válido");
        }
        if (userRepository.findByEmail(email.trim().toLowerCase()).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Ya existe un usuario con ese email");
        }
        try {
            User newUser = new User();
            newUser.setName(name.trim());
            newUser.setEmail(email.trim().toLowerCase());
            userRepository.save(newUser);
            return ResponseEntity.status(HttpStatus.CREATED).body("Usuario creado exitosamente");
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                .body("Error: Email duplicado o violación de integridad de datos");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error al crear el usuario");
        }
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
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("No se encontró ningún usuario con ID: " + id);
        }
    }

    // UPDATE - Update a user by ID
    @PutMapping(path="/{id}")
    public @ResponseBody ResponseEntity<String> updateUser(@PathVariable Integer id,
                                                           @RequestParam(required = false) String name,
                                                           @RequestParam(required = false) String email) {
        if ((name == null || name.trim().isEmpty()) && (email == null || email.trim().isEmpty())) {
            return ResponseEntity.badRequest()
                .body("Debe proporcionar al menos un campo válido para actualizar");
        }
        Optional<User> userOptional = userRepository.findById(id);
        if (!userOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("No se encontró ningún usuario con ID: " + id);
        }
        User user = userOptional.get();
        if (name != null && !name.trim().isEmpty()) {
            if (name.trim().length() < 2) {
                return ResponseEntity.badRequest().body("El nombre debe tener al menos 2 caracteres");
            }
            if (name.trim().length() > 255) {
                return ResponseEntity.badRequest().body("El nombre no puede exceder 255 caracteres");
            }
            user.setName(name.trim());
        }
        if (email != null && !email.trim().isEmpty()) {
            if (email.trim().length() > 255) {
                return ResponseEntity.badRequest().body("El email no puede exceder 255 caracteres");
            }
            if (!email.matches(EMAIL_REGEX)) {
                return ResponseEntity.badRequest().body("El formato del email no es válido");
            }
            String emailLower = email.trim().toLowerCase();
            Optional<User> existingUser = userRepository.findByEmail(emailLower);
            if (existingUser.isPresent() && !existingUser.get().getId().equals(id)) {
                return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Ya existe otro usuario con ese email");
            }
            user.setEmail(emailLower);
        }
        try {
            userRepository.save(user);
            return ResponseEntity.ok("Usuario actualizado exitosamente");
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                .body("Error: Email duplicado o violación de integridad de datos");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error al actualizar el usuario");
        }
    }

    // DELETE - Delete a user by ID
    @DeleteMapping(path="/{id}")
    public @ResponseBody ResponseEntity<String> deleteUser(@PathVariable Integer id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            userRepository.deleteById(id);
            return ResponseEntity.ok("Usuario eliminado exitosamente");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("No se encontró ningún usuario con ID: " + id);
        }
    }
}
