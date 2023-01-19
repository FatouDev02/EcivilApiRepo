package com.example.EcivilApi.repository;

import com.example.EcivilApi.models.Mairie;
import com.example.EcivilApi.models.Tribunal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Tribrepo extends JpaRepository<Tribunal,Long> {
    Tribunal findByNom(String Nom);

}
