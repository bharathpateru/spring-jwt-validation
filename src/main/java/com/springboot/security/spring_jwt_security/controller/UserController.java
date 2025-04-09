package com.springboot.security.spring_jwt_security.controller;

import com.springboot.security.spring_jwt_security.model.Users;
import com.springboot.security.spring_jwt_security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/register")
    public ResponseEntity<Users> registerUser(@RequestBody Users user){
        return userService.registerUser(user);
    }

    @GetMapping("/getUsers")
    public ResponseEntity<List<Users>> getAllUsers(){
        return userService.getAllUsers();
    }

    @PostMapping("/login")
    public String login(@RequestBody Users user){
        return userService.verifyUser(user);
    }


}
