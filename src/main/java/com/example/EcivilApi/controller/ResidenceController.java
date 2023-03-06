package com.example.EcivilApi.controller;

import com.example.EcivilApi.configuration.ResponseMessage;
import com.example.EcivilApi.configuration.SaveImage;
import com.example.EcivilApi.models.*;
import com.example.EcivilApi.repository.AgentRepo;
import com.example.EcivilApi.repository.NotificationRepository;
import com.example.EcivilApi.repository.Residncerepo;
import com.example.EcivilApi.repository.UtilisateurRepository;
import com.example.EcivilApi.services.Demandeservice;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("/ecivil/residence")
@CrossOrigin(origins = "http://localhost:8100/", maxAge = 3600,allowCredentials="true")
public class ResidenceController {


    @Autowired
    Residncerepo residncerepo;
    @Autowired
    NotificationRepository notificationRepository;
    @Autowired
    AgentRepo agentRepository;

    @Autowired
    UtilisateurRepository utilisateurRepository;
    @Autowired
    Demandeservice demandeservice;

    @PostMapping("/add/{resstructure}/{utilisateurs}/{lieuderesidence}")
    public Object createresidence(

            @RequestParam(value = "residence",required = true) String residence,

            @PathVariable Structure resstructure,
            @PathVariable Utilisateurs utilisateurs,
            @PathVariable String lieuderesidence,
                        @RequestParam(value = "file", required = false) MultipartFile file


    ) throws JsonProcessingException {
        Residence residence1  =new JsonMapper().readValue(residence,Residence.class);


        // Acten c = actenRepo.findByNumvolet(numvolet);
        Utilisateurs u=utilisateurRepository.findById(utilisateurs.getId()).get();
        Notification notification=new Notification();
        notification.setDatenotif(new Date());
        notification.setDescription(utilisateurs.getPrenom()+" "+utilisateurs.getNom() +"a fait une demande d'acte de Naissance: " );
        Notification notification1= notificationRepository.save(notification);
        List<Agents> agentsList =  agentRepository.findByStructure(resstructure);
        for (Agents agents : agentsList){
            agents.getNotification().add(notification1);
            agentRepository.save(agents);
        }
        //Demande demande1=demandeRepository.findById(demande.getId()).get();
        if(file!=null){
            residence1.setPhotoacten(SaveImage.save("residence",file,residence1.getNom()));
            if(u == null){
                return ResponseMessage.generateResponse("error", HttpStatus.OK, "user problemme");
            }
            else{
                residence1.setMastructure(resstructure);
                residence1.setUser(u);
                return demandeservice.creerresidence(residence1,lieuderesidence,residence1.getMastructure().getNom());

            }
        }
        else {
            return ResponseMessage.generateResponse("error", HttpStatus.OK, "Fichier vide");
        }

    }

    @GetMapping("/listresbystruct/{structure}")
    public  Object  allres(@PathVariable Structure structure ){
        return residncerepo.findByMastructure(structure);
    }

    @GetMapping("/list/{user}")
    public Object listres( @PathVariable Utilisateurs user){
        return  residncerepo.findByUser(user);

    }
    @GetMapping("/getacte/{residence}")
    public Residence getacet(@PathVariable Residence residence){
        return  residncerepo.findById(residence.getId()).get();

    }

    ////////////////////////    //////////////////////// valider   ////////////////////////    ////////////////////////


}
