package com.example.DetectionCarAppMadi_javaService.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.DetectionCarAppMadi_javaService.Entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    
} 