package com.example.EcivilApi.serviceimpl;

import com.example.EcivilApi.models.Notification;
import com.example.EcivilApi.repository.NotificationRepository;
import com.example.EcivilApi.services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class NotificationImpl implements NotificationService {
    @Autowired
    NotificationRepository notificationRepo;

    @Override
    public Notification creer(Notification notification) {
        // TODO Auto-generated method stub
        return notificationRepo.save(notification);
    }

    @Override
    public Notification update(Long id, Notification notification) {
        return null;
    }


    @Override
    public void delete(Long id) {
        // TODO Auto-generated method stub
        notificationRepo.deleteById(id);

    }

    @Override
    public Notification getById(Long id) {
        // TODO Auto-generated method stub
        return notificationRepo.findById(id).get();
    }

    @Override
    public Notification getByTitre(String titre) {
        // TODO Auto-generated method stub
        return notificationRepo.findByTitre(titre);
    }

    @Override
    public List<Notification> getAll() {
        // TODO Auto-generated method stub
        return notificationRepo.findAll();
    }
}
