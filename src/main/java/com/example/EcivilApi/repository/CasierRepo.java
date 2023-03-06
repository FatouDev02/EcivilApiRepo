package com.example.EcivilApi.repository;

import com.example.EcivilApi.models.Actem;
import com.example.EcivilApi.models.CasierJudiciaire;
import com.example.EcivilApi.models.Structure;
import com.example.EcivilApi.models.Utilisateurs;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CasierRepo extends JpaRepository<CasierJudiciaire,Long> {
    List<CasierJudiciaire> findByMastructure(Structure mastructure);
    List<CasierJudiciaire> findByUser(Utilisateurs user);

}
