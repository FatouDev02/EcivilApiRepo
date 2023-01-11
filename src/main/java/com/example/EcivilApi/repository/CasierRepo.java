package com.example.EcivilApi.repository;

import com.example.EcivilApi.models.CasierJudiciaire;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CasierRepo extends JpaRepository<CasierJudiciaire,Long> {
}
