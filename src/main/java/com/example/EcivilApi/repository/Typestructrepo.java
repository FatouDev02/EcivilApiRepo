package com.example.EcivilApi.repository;

import com.example.EcivilApi.models.Typestructure;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Typestructrepo extends JpaRepository<Typestructure,Long> {
    Typestructure findByType (String type);
}
