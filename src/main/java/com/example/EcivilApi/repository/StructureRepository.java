package com.example.EcivilApi.repository;

import com.example.EcivilApi.models.ActeD;
import com.example.EcivilApi.models.Structure;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StructureRepository extends JpaRepository<Structure,Long> {
}
