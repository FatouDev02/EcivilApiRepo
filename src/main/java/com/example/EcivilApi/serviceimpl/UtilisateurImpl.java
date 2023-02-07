package com.example.EcivilApi.serviceimpl;

import com.example.EcivilApi.configuration.EmailConstructor;
import com.example.EcivilApi.models.*;
import com.example.EcivilApi.repository.AgentRepo;
import com.example.EcivilApi.repository.Rolerepository;
import com.example.EcivilApi.repository.UtilisateurRepository;
import com.example.EcivilApi.services.UtilisateurService;
import jdk.jshell.execution.Util;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.loadtime.Agent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class UtilisateurImpl implements UtilisateurService {

    @Autowired
    UtilisateurRepository repos;
    @Autowired
    Rolerepository rolerepository;
    @Autowired
    EmailConstructor emailConstructor;
    @Autowired
    JavaMailSender mailSender;
    @Autowired
    AgentRepo agentRepo;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public Utilisateurs creer(Utilisateurs utilisateur) {
        log.info("Ajout de l'utilisateur  {} ", utilisateur.getUsername());
        //a l'enregistrement on recupère le passwor et le l'encode

        // TODO Auto-generated method stub
        //utilisateur.setPassword(passwordEncoder().encode(utilisateur.getPassword()));
        Optional<Role> u=rolerepository.findByName(ERole.Agent);
        //s'il se connecte en tant qu'agent "vous voulez vous connecter
        // en tant qu'agentveuiller suivre ce lien pour definir votre strucuture"
        Utilisateurs find=this.repos.save(utilisateur);


        Set<Role>  roleSet=find.getRoles();
        for (Role role: roleSet){
            if(role.equals(u.get())){
                utilisateur.setStatut("Agentnonvalider");
                //envoies message pour suivre un processs
                mailSender.send(emailConstructor.constructagent(find));
                System.out.println("//////////////////////////////////agent");
            }else {
//sinon juste un lien pour se connecetr
                mailSender.send(emailConstructor.constructNewUserEmail(find));
                System.out.println("//////////////////////////////////userrrr");

            }
        }

        // on le valider apres avec la methode validerrrrr
        return find;

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

//methode pour admin
    @Override
    public Object validerinscriptionentantquerole(Long id) {
        Agents a = agentRepo.findById(id).get();

        String mat = a.getNom().substring(0, 1) + a.getPrenom().substring(0, 1)
                + "@sebenw2022";
        a.setMatricule(mat);
        a.setStatut("AgentValider");
        agentRepo.save(a);
        mailSender.send(emailConstructor.valideagent(a,a.getStructure()));



        return a;
    }

    @Override
    public Object demandebyagent(Long id,Structure structure) {
        System.out.println("//////////////////////////////////identifiant"+id);
        Agents a = agentRepo.findById(id).get();
        a.setStructure(structure);
        agentRepo.save(a);
        //envoies message pour suivre un processs
        System.out.println("//////////////////////////////////agent");
        return a;
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
