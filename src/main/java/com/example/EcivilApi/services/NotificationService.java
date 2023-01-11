package com.example.EcivilApi.services;

import com.example.EcivilApi.models.Notification;

import java.util.List;

public interface NotificationService {

    Notification creer(Notification notification);

    Notification update(Long id,Notification notification);

    void delete(Long id);

    Notification getById(Long id);

    Notification getByTitre(String titre);

    List<Notification> getAll();
}
