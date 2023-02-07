package com.example.EcivilApi.services;

import com.example.EcivilApi.models.*;

import java.util.List;
import java.util.Optional;

public interface UtilisateurService {

    Utilisateurs creer(Utilisateurs utilisateur);

    Role ajoutrole(Role role);
    Optional<Role> getrole(ERole name);


    Object update(Long id,Utilisateurs utilisateur);
    Object validerinscriptionentantquerole(Long id );
    Object demandebyagent(Long id,Structure structure);

    void delete(Long id);
    Utilisateurs getByEmail(String email);

    Utilisateurs getById(Long id);

    List<Utilisateurs> getAll();

   // List<Utilisateurs> RetrouverParRole(Role role);

    Long TotalUsers();

    Utilisateurs modifierRole(Utilisateurs utilisateur);

    Utilisateurs trouverParUsernameAndPass(String username, String password);
}
