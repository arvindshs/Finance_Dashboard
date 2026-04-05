package com.zorvyn.assignment.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zorvyn.assignment.config.Config;
// import com.zorvyn.assignment.config.Config;
import com.zorvyn.assignment.config.LoginRequest;
import com.zorvyn.assignment.models.User;
import com.zorvyn.assignment.models.enummodels.Roles;
import com.zorvyn.assignment.models.enummodels.Status;
import com.zorvyn.assignment.repository.UserRepo;
@Service
public class UserServices {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private JWTServices jwtServices;
    @Autowired
    private Config config;
    @Deprecated
    public String login(LoginRequest loginRequest){
        User user = userRepo.findByEmail(loginRequest.getEmail())
            .orElseThrow(() -> new RuntimeException("User not found"));
        
        if (!config.passwordEncoder().matches(loginRequest.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        return jwtServices.generateToken(user.getEmail(), user.getRole().name());
    }
    public User createUser(User user) {
        user.setPassword(config.passwordEncoder().encode(user.getPassword()));
        user.setStatus(Status.ACTIVE);
        return userRepo.save(user);
    }

    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    public User getUserById(Long id) {
        return userRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
   
    public void deleteUser(Long id) {
        if (!userRepo.existsById(id)) {
            throw new RuntimeException("User not found");
        }
        userRepo.deleteById(id);
    }

    public User updateUserRole(Long id, String role) {
        User user = userRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setRole(Roles.valueOf(role));
        return userRepo.save(user);
    }
}
