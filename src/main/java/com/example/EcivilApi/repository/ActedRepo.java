package com.example.EcivilApi.repository;

import com.example.EcivilApi.models.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ActedRepo extends JpaRepository<ActeD,Long> {
    ActeD findByNumvolet(String numvolet);
    List<ActeD> findByMastructure(Structure mastructure);
    List<ActeD> findByUser(Utilisateurs user);

}
