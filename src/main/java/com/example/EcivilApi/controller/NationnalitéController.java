package com.example.EcivilApi.controller;

import com.example.EcivilApi.configuration.ResponseMessage;
import com.example.EcivilApi.configuration.SaveImage;
import com.example.EcivilApi.models.*;
import com.example.EcivilApi.repository.*;
import com.example.EcivilApi.services.Demandeservice;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("/ecivil/nationnalite")
@CrossOrigin(origins = "http://localhost:8100/", maxAge = 3600,allowCredentials="true")
public class NationnalitéController {
    @Autowired
    NationnaliteRepo nationnaliteRepo ;

    @Autowired
    UtilisateurRepository utilisateurRepository;
    @Autowired
    Demandeservice demandeservice;
    @Autowired
    NotificationRepository notificationRepository;
    @Autowired
    AgentRepo agentRepository;
    @Autowired
    Notifrdvuserrepo notifrdvuserrepo;


    @PostMapping("/validationnat/{id}")
    public  Object  valider(@PathVariable Long id){
        Nationnalite actem= nationnaliteRepo.findById(id).get();
        actem.setEtatdemande("Demande Validée");
        //envoie un message à user
        Notifrdvuser notifica= new Notifrdvuser();
        notifica.setDatenotification(new Date());
        // Date dateLimite = new Date(System.currentTimeMillis() + (2 * 24 * 60 * 60 * 1000));
        LocalDate dateLimitee = LocalDate.now().plusDays(2);


        notifica.setNdescription(" Votre demande a été prise en compte veuillez récuperer le document a cette date: " + dateLimitee);
        Notifrdvuser notificationn= notifrdvuserrepo.save(notifica);
        actem.getUser().getNotifrdvusers().add(notificationn);
        return demandeservice.updatenationlt(id,actem);

    }
    @PostMapping("/add/{mastructure}/{utilisateurs}/{lieuderesidence}")
    public Object createnationnalite(

            @RequestParam(value = "nationnalite",required = true) String nationnalite,
            @PathVariable Structure mastructure, @PathVariable Utilisateurs utilisateurs,
                        @PathVariable String lieuderesidence,
            @RequestParam(value = "file", required = false) MultipartFile file


    ) throws JsonProcessingException {
        Nationnalite nationnalite1   =new JsonMapper().readValue(nationnalite,Nationnalite.class);
        // Acten c = actenRepo.findByNumvolet(numvolet);
        Utilisateurs u=utilisateurRepository.findById(utilisateurs.getId()).get();
        Notification notification=new Notification();
        notification.setDatenotif(new Date());
        notification.setDescription(utilisateurs.getPrenom()+" "+utilisateurs.getNom() +"a fait une demande d'acte de Naissance: " );
        Notification notification1= notificationRepository.save(notification);
        List<Agents> agentsList =  agentRepository.findByStructure(mastructure);
        for (Agents agents : agentsList){
            agents.getNotification().add(notification1);
            agentRepository.save(agents);
        }
        //Demande demande1=demandeRepository.findById(demande.getId()).get();
        if (file != null) {
            nationnalite1.setPhotoacten((SaveImage.save("residence",file,nationnalite1.getNom())));
            if(u == null){
                return ResponseMessage.generateResponse("error", HttpStatus.OK, "user problemme");
            }
            else{
                nationnalite1.setMastructure(mastructure);
                nationnalite1.setUser(u);
                return demandeservice.creernationlt(nationnalite1,lieuderesidence,nationnalite1.getMastructure().getNom());

            }


        } else {
            return ResponseMessage.generateResponse("error", HttpStatus.OK, "Fichier vide");
        }

    }

    @GetMapping("/listnatbystruct/{structure}")
    public  Object  allnat(@PathVariable Structure structure ){
        List<Nationnalite> actenList=nationnaliteRepo.findByMastructure(structure);
        List<Nationnalite> newact=new ArrayList<>();

        for (Nationnalite al:actenList){

            if(al.getEtatdemande()== null){
                newact.add(al);

            }
        }
        return newact;
    }

    @GetMapping("/count/{structure}")
    public  Object  countnat(@PathVariable Structure structure ){
        return nationnaliteRepo.findByMastructure(structure);
    }
    @GetMapping("/list/{user}")
    public Object listnat( @PathVariable Utilisateurs user){
        return  nationnaliteRepo.findByUser(user);

    }
    @GetMapping("/getacte/{nationnalite}")
    public Nationnalite getacet(@PathVariable Nationnalite nationnalite){
        return  nationnaliteRepo.findById(nationnalite.getId()).get();

    }

    ////////////////////////    //////////////////////// valider   ////////////////////////    ////////////////////////


}
