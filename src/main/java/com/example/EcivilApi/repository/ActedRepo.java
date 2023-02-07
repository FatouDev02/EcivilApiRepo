package com.example.EcivilApi.repository;

import com.example.EcivilApi.models.ActeD;
import com.example.EcivilApi.models.Acten;
import com.example.EcivilApi.models.Structure;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ActedRepo extends JpaRepository<ActeD,Long> {
    ActeD findByNumvolet(String numvolet);
    List<ActeD> findByMastructure(Structure mastructure);

}
