package com.example.EcivilApi.repository;

import com.example.EcivilApi.models.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface NotificationRepository extends JpaRepository<Notification,Long> {

    Notification findByTitre(String titre);

    List<Notification> findAllByOrderByIdDesc();
}
