package com.example.EcivilApi.repository;

import com.example.EcivilApi.models.Actem;
import com.example.EcivilApi.models.Residence;
import com.example.EcivilApi.models.Structure;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Residncerepo extends JpaRepository<Residence,Long> {
    List<Residence> findByMastructure(Structure mastructure);

}
