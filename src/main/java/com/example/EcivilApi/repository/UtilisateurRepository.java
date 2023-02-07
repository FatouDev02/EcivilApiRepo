package com.example.EcivilApi.repository;

import com.example.EcivilApi.models.ActeD;
import com.example.EcivilApi.models.ERole;
import com.example.EcivilApi.models.Role;
import com.example.EcivilApi.models.Utilisateurs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateurs,Long> {
    Utilisateurs findByEmail(String email);
    Utilisateurs findByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
   // Role findByRole(ERole name);
    Utilisateurs findByUsernameAndPassword(String username, String password);


    //List<Utilisateurs> findByRole(Role roles);

   // List<Utilisateurs> findByActive(Boolean active);

    @Query(value = "SELECT COUNT(utilisateur.id) FROM utilisateur", nativeQuery = true)
    public Long Total();
}
