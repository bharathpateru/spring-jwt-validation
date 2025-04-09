package com.springboot.security.spring_jwt_security.service;

import com.springboot.security.spring_jwt_security.model.Users;
import com.springboot.security.spring_jwt_security.repository.UserRespository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRespository userRespository;

    Logger log = LoggerFactory.getLogger(getClass());

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Users user = userRespository.findByUsername(username);

        if(user==null){
            log.error("User Not Found");
            throw new UsernameNotFoundException("User Not Found"+username);
        }

        return new UserPrincipal(user);
    }
}
