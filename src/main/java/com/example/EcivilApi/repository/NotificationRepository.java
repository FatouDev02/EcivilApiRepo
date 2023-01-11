package com.example.EcivilApi.repository;

import com.example.EcivilApi.models.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification,Long> {

    Notification findByTitre(String titre);

    List<Notification> findAllByOrderByIdDesc();
}
