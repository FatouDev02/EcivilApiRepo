package com.example.EcivilApi.controller;

import com.example.EcivilApi.configuration.ResponseMessage;
import com.example.EcivilApi.models.*;
import com.example.EcivilApi.repository.*;
import com.example.EcivilApi.services.Demandeservice;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("/ecivil/acted")

@CrossOrigin(origins = {"http://localhost:8100/", "http://localhost:8101/"}, maxAge = 3600,allowCredentials="true")
public class ActedController {


    @Autowired
    UtilisateurRepository utilisateurRepository;
    @Autowired
    NotificationRepository notificationRepository;
    @Autowired
    AgentRepo agentRepository;

    @Autowired
    ActedRepo actedRepo;
    @Autowired
    Demandeservice demandeservice;

    //a la creation d'un acte de naissance on donne on donne l'id de la demande or une demande est liéé à,un strucure
    //donc acten est liée a la structure dont la demande est liée
    @PostMapping("/add/{numvolet}/{structure}/{utilisateurs}")
    public Object createacted(

            @RequestParam(value = "acted",required = true) String acted,String numvolet,
            @PathVariable Structure structure, @PathVariable Utilisateurs utilisateurs
    ) throws JsonProcessingException {

        ActeD acted1 =new JsonMapper().readValue(acted,ActeD.class);

        ActeD c = actedRepo.findByNumvolet(numvolet);

        Utilisateurs u=utilisateurRepository.findById(utilisateurs.getId()).get();
        Notification notification=new Notification();
        notification.setDatenotif(new Date());
        notification.setDescription(utilisateurs.getPrenom()+" "+utilisateurs.getNom() +"a fait une demande d'acte de décès: " );
        Notification notification1= notificationRepository.save(notification);
        List<Agents> agentsList =  agentRepository.findByStructure(structure);
        for (Agents agents : agentsList){
            agents.getNotification().add(notification1);
            agentRepository.save(agents);
        }
        //Demande demande1=demandeRepository.findById(demande.getId()).get();
        if (c != null ){
            return ResponseMessage.generateResponse("error", HttpStatus.OK, "problemenumvolet");
        }
        else if(u == null){
            return ResponseMessage.generateResponse("error", HttpStatus.OK, "user inexistant");

        }

        else{

            // acten1.setMademande(demande1);
            //acten1.setMademande(demande);

            acted1.setMastructure(structure);
            acted1.setUser(u);
            return demandeservice.creeracted(acted1,numvolet);
        }

    }
    @GetMapping("/list/{user}")
    public Object listacted( @PathVariable Utilisateurs user){
        return  actedRepo.findByUser(user);

    }

    @GetMapping("/getacte/{acted}")
    public ActeD getacet(@PathVariable ActeD acted){
        return  actedRepo.findById(acted.getId()).get();

    }
    @GetMapping("/listactedbystruct/{structure}")
    public  Object  allacted(@PathVariable Structure structure ){
        return actedRepo.findByMastructure(structure);
    }
    ////////////////////////    //////////////////////// valider   ////////////////////////    ////////////////////////

}
