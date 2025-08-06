package com.js.sb_rest_mvc_jpa.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import com.js.sb_rest_mvc_jpa.repository.User;
import com.js.sb_rest_mvc_jpa.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository repository) {
        this.userRepository = repository;
    }

    public String getUserName(Long id) {
    		Optional<User> u = userRepository.findById(id);
    		User user = u.get();
        return user != null ? user.getName() : "Unknown";
    }
    
    public User createUser(User user) {
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User findByEmail(String email){
        return userRepository.findByEmail(email);
    }
    
    public User updateUser(Long id, User user) {
        User updated = userRepository.save(user);
        return updated;
    }
}
