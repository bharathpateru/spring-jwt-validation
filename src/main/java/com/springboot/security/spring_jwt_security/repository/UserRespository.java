package com.springboot.security.spring_jwt_security.repository;

import com.springboot.security.spring_jwt_security.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRespository  extends JpaRepository<Users, Integer> {
    Users findByUsername(String username);
}
