package com.csit321g6.gilaspilipinas.learngo.repository;

import com.csit321g6.gilaspilipinas.learngo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);   // Spring generates the query from the method name
}