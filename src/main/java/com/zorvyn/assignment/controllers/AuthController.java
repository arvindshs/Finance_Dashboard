package com.zorvyn.assignment.controllers;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zorvyn.assignment.config.LoginRequest;
import com.zorvyn.assignment.services.UserServices;


@RestController
@RequestMapping("/auth")
public class AuthController {
     @Autowired
    private UserServices userService;
    @Deprecated
    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request) {
        return userService.login(request);
    }
}
