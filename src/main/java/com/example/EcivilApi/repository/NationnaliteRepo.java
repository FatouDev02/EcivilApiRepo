package com.example.EcivilApi.repository;

import com.example.EcivilApi.models.Actem;
import com.example.EcivilApi.models.Nationnalite;
import com.example.EcivilApi.models.Structure;
import com.example.EcivilApi.models.Utilisateurs;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NationnaliteRepo extends JpaRepository<Nationnalite,Long> {
    List<Actem> findByMastructure(Structure mastructure);
    List<Nationnalite> findByUser(Utilisateurs user);

}
