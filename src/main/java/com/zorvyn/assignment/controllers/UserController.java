package com.zorvyn.assignment.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zorvyn.assignment.models.User;
import com.zorvyn.assignment.services.UserServices;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserServices userServices;

    @PostMapping("/create")
    public User createUser(@RequestBody User user){
        return userServices.createUser(user);
    }
    @GetMapping("/all")
    public List<User> getallusers(){
        return userServices.getAllUsers();
    }
    @GetMapping("/{id}")
    public User getUserbyid(@PathVariable Long id){
        return userServices.getUserById(id);
    }
}
