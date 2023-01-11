package com.example.EcivilApi.repository;

import com.example.EcivilApi.models.ActeD;
import com.example.EcivilApi.models.Role;
import com.example.EcivilApi.models.Utilisateurs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UtilisateurRepository extends JpaRepository<Utilisateurs,Long> {
    Utilisateurs findByEmail(String email);
    Utilisateurs findByUsername(String login);

    Utilisateurs findByUsernameAndPassword(String login, String password);


    List<Utilisateurs> findByRole(Role role);

    List<Utilisateurs> findByActive(Boolean active);

    @Query(value = "SELECT COUNT(utilisateur.id) FROM utilisateur", nativeQuery = true)
    public Long Total();
}
