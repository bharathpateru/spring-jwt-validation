package com.springboot.security.spring_jwt_security.service;

import com.springboot.security.spring_jwt_security.model.Users;
import com.springboot.security.spring_jwt_security.repository.UserRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRespository userRespository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    JwtService jwtService;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    public ResponseEntity<Users> registerUser(@RequestBody Users user){
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        Users users = userRespository.save(user);
        return new ResponseEntity<>(users, HttpStatus.CREATED);
    }

    public ResponseEntity<List<Users>> getAllUsers(){
        List<Users> usersList = userRespository.findAll();
        return new ResponseEntity<>(usersList, HttpStatus.OK);
    }

    public String verifyUser(Users user) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        if(authentication.isAuthenticated())
            return jwtService.generateToken(user.getUsername());

        return "Fail";
    }
}
