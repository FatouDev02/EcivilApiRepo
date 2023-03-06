package com.example.EcivilApi.repository;

import com.example.EcivilApi.models.Actem;
import com.example.EcivilApi.models.Acten;
import com.example.EcivilApi.models.Structure;
import com.example.EcivilApi.models.Utilisateurs;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ActenRepo extends JpaRepository<Acten,Long> {
    Acten findByNumvolet(String numvolet);
    List<Acten> findByUser(Utilisateurs user);

    List<Acten> findByMastructure(Structure mastructure);
    List<Acten> findAllByMastructure(Structure mastructure);
}
