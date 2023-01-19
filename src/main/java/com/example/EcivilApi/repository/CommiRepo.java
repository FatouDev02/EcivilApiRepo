package com.example.EcivilApi.repository;

import com.example.EcivilApi.models.Commissariats;
import com.example.EcivilApi.models.Mairie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommiRepo extends JpaRepository<Commissariats,Long> {
    Commissariats findByNom(String Nom);

}
