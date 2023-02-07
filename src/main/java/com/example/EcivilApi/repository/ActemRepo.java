package com.example.EcivilApi.repository;

import com.example.EcivilApi.models.Actem;
import com.example.EcivilApi.models.Acten;
import com.example.EcivilApi.models.Structure;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ActemRepo extends JpaRepository<Actem,Long> {
    List<Actem> findByMastructure(Structure mastructure);

}
