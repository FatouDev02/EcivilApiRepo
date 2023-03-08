package com.example.EcivilApi.repository;

import com.example.EcivilApi.models.Actem;
import com.example.EcivilApi.models.Acten;
import com.example.EcivilApi.models.Structure;
import com.example.EcivilApi.models.Utilisateurs;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ActemRepo extends JpaRepository<Actem,Long> {
    List<Actem> findByMastructure(Structure mastructure);

    List<Actem> findByUser(Utilisateurs user);
    List<Actem> findAllByMastructure(Structure mastructure);


}
