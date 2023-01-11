package com.example.EcivilApi.repository;

import com.example.EcivilApi.models.ActeD;
import com.example.EcivilApi.models.Demande;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DemandeRepository extends JpaRepository<Demande,Long> {
}
