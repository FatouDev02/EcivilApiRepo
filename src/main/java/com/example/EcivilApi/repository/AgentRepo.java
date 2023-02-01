package com.example.EcivilApi.repository;

import com.example.EcivilApi.models.Agents;
import com.example.EcivilApi.models.Structure;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgentRepo extends JpaRepository<Agents,Long> {
    Agents findByStructure(Structure structure);
}
