package com.example.EcivilApi.serviceimpl;

import com.example.EcivilApi.models.ERole;
import com.example.EcivilApi.models.Role;
import com.example.EcivilApi.models.Utilisateurs;
import com.example.EcivilApi.repository.Rolerepository;
import com.example.EcivilApi.repository.UtilisateurRepository;
import com.example.EcivilApi.services.UtilisateurService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class UtilisateurImpl implements UtilisateurService {

    @Autowired
    UtilisateurRepository repos;
    @Autowired
    Rolerepository rolerepository;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public Utilisateurs creer(Utilisateurs utilisateur) {
        log.info("Ajout de l'utilisateur  {} ", utilisateur.getUsername());
        //a l'enregistrement on recupère le passwor et le l'encode

        // TODO Auto-generated method stub
        utilisateur.setPassword(passwordEncoder().encode(utilisateur.getPassword()));
        return repos.save(utilisateur);
    }

    @Override
    public Role ajoutrole(Role role) {
        log.info("Ajout d'un role  {} dans la Bdd", role.getName());
        return rolerepository.save(role);
    }

    @Override
    public Optional<Role> getrole(ERole name) {
        return rolerepository.findByName(name);
    }


    @Override
    public Object update(Long id,Utilisateurs utilisateur) {
        // TODO Auto-generated method stub
        Utilisateurs user = this.getById(utilisateur.getId());
        System.out.println(user.getPassword());

        utilisateur.setRoles(user.getRoles());

        if (utilisateur.getPassword() == null || utilisateur.getPassword() == "") {

            utilisateur.setPassword(user.getPassword());

        } else if (utilisateur.getPassword() != null) {
            System.out.println("non null");

            utilisateur.setPassword(passwordEncoder().encode(utilisateur.getPassword()));

        }
        utilisateur.setId(id);
        return repos.save(utilisateur);
    }

    @Override
    public void delete(Long id) {
        // TODO Auto-generated method stub
        repos.deleteById(id);
    }

    @Override
    public Utilisateurs getByEmail(String email) {
        return repos.findByEmail(email);
    }

    @Override
    public Utilisateurs getById(Long id) {
        // TODO Auto-generated method stub
        return repos.findById(id).get();
    }

    @Override
    public List<Utilisateurs> getAll() {
        // TODO Auto-generated method stub
        return repos.findAll();
    }



  /*  @Override
    public List<Utilisateurs> RetrouverParRole(Role role) {
        // TODO Auto-generated method stub
        return repos.findByRole(role);
    }*/

    @Override
    public Long TotalUsers() {
        return repos.Total();
    }



    @Override
    public Utilisateurs modifierRole(Utilisateurs utilisateur) {
        // TODO Auto-generated method stub

        Utilisateurs user = this.getById(utilisateur.getId());
        System.out.println(user.getPassword());

        utilisateur.setRoles(user.getRoles());

        utilisateur.setPassword(user.getPassword());

        return repos.save(utilisateur);
    }


    @Override
    public Utilisateurs trouverParUsernameAndPass(String username, String password) {
        // TODO Auto-generated method stub
        return repos.findByUsernameAndPassword(username,password);
    }
}
