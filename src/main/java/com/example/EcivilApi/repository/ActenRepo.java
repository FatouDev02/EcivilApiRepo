package com.example.EcivilApi.repository;

import com.example.EcivilApi.models.Acten;
import com.example.EcivilApi.models.Structure;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ActenRepo extends JpaRepository<Acten,Long> {
    Acten findByNumvolet(String numvolet);

    List<Acten> findByMastructure(Structure mastructure);
}
