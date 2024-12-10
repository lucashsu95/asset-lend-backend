package com.example.asset_lend_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.asset_lend_backend.model.User;

public interface UserRepository extends JpaRepository<User,Long> {
    
}
