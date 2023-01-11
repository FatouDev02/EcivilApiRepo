package com.example.EcivilApi.repository;

import com.example.EcivilApi.models.ERole;
import com.example.EcivilApi.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface Rolerepository extends JpaRepository<Role,Long> {
    // Role findByName(String name);
    Optional<Role> findByName(ERole name);
}
