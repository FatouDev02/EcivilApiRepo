package com.example.EcivilApi.repository;

import com.example.EcivilApi.models.Acten;
import com.example.EcivilApi.models.Mairie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Mairierepo extends JpaRepository<Mairie,Long> {
    Mairie findByNom(String Nom);
}
