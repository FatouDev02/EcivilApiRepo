package com.example.EcivilApi.repository;

import com.example.EcivilApi.models.Agents;
import com.example.EcivilApi.models.Notification;
import com.example.EcivilApi.models.Structure;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AgentRepo extends JpaRepository<Agents,Long> {
    List<Agents> findByStructure(Structure structure);
    List<Notification> findByNotification(Agents agents);

}
