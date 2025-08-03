package com.js.sb_rest_mvc_jpa.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.js.sb_rest_mvc_jpa.repository.User;
import com.js.sb_rest_mvc_jpa.repository.UserRepository;

import jakarta.persistence.OptimisticLockException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/{email}")
    public User getUserByEmail(@PathVariable String email) throws ResourceNotFoundException {
    		if( email != null) 
    			throw new ResourceNotFoundException("Email not entered");
        return userRepository.findByEmail(email);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
        User updated = userRepository.save(user);
        return ResponseEntity.ok(updated);
    }
    
    
  /*  @ExceptionHandler(ResourceNotFoundException.class)
    public String handleNotFound(ResourceNotFoundException ex, Model model)   
    {
        model.addAttribute("error", ex.getMessage());
        return "Error: " + ex.getMessage(); // Render error page
    } */

    
    
    /*@PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody UserUpdateDto userInput) {
        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        User existingUser = optionalUser.get();

        // Optional: check version match for optimistic locking
        if (!existingUser.getVersion().equals(userInput.getVersion())) {
            throw new OptimisticLockException("Version mismatch — record may have been modified by another user.");
        }

        // Update only allowed fields
        existingUser.setName(userInput.getName());
        existingUser.setEmail(userInput.getEmail());
        // Don't touch ID or manually change version

        User updated = userRepository.save(existingUser);

        return ResponseEntity.ok(updated);
    }*/
}
