package com.example.EcivilApi.services;

import com.example.EcivilApi.models.Role;
import com.example.EcivilApi.models.Utilisateurs;

import java.util.List;

public interface UtilisateurService {

    Utilisateurs creer(Utilisateurs utilisateur);

    Role ajoutrole(Role role);

    Utilisateurs update(Utilisateurs utilisateur);

    void delete(Long id);

    Utilisateurs getById(Long id);

    List<Utilisateurs> getAll();

   // List<Utilisateurs> RetrouverParRole(Role role);

    Long TotalUsers();

    Utilisateurs modifierRole(Utilisateurs utilisateur);

    Utilisateurs trouverParUsernameAndPass(String username, String password);
}
