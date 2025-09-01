package com.springboot.security.spring_jwt_security.controller;

import com.springboot.security.spring_jwt_security.model.Users;
import com.springboot.security.spring_jwt_security.service.JwtService;
import com.springboot.security.spring_jwt_security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    private JwtService jwtService;

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

    @PostMapping("/refresh")
    public ResponseEntity<?> refreshToken(@RequestBody Map<String, String> request) {
        String refreshToken = request.get("refreshToken");
        if (jwtService.validateToken(refreshToken)) {
            String username = jwtService.extractUsername(refreshToken);
            String newAccessToken = jwtService.generateToken(username);
            return ResponseEntity.ok(Map.of("accessToken", newAccessToken));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid refresh token");
    }


}
