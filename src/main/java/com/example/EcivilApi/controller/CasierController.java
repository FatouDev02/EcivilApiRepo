package com.example.EcivilApi.controller;

import com.example.EcivilApi.configuration.ResponseMessage;
import com.example.EcivilApi.configuration.SaveImage;
import com.example.EcivilApi.models.*;
import com.example.EcivilApi.repository.AgentRepo;
import com.example.EcivilApi.repository.CasierRepo;
import com.example.EcivilApi.repository.NotificationRepository;
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
@RequestMapping("/ecivil/casier")
@CrossOrigin(origins = {"http://localhost:8100/", "http://localhost:8101/"}, maxAge = 3600,allowCredentials="true")
public class CasierController {

    @Autowired
    CasierRepo casierRepo;
    @Autowired
    NotificationRepository notificationRepository;
    @Autowired
    AgentRepo agentRepository;

    @Autowired
    UtilisateurRepository utilisateurRepository;
    @Autowired
    Demandeservice demandeservice;
    //a la creation d'un acte de naissance on donne on donne l'id de la demande or une demande est liéé à,un strucure
    //donc acten est liée a la structure dont la demande est liée
    @PostMapping("/add/{casstructure}/{utilisateurs}/{lieudenaissance}")
    public Object createcasier(

            @RequestParam(value = "casier",required = true) String casier,
            @PathVariable Structure casstructure, @PathVariable Utilisateurs utilisateurs,
            @PathVariable String lieudenaissance,
            @RequestParam(value = "file", required = false) MultipartFile file
    ) throws JsonProcessingException {
        CasierJudiciaire casierJudiciaire1 =new JsonMapper().readValue(casier,CasierJudiciaire.class);


        // Acten c = actenRepo.findByNumvolet(numvolet);
        Utilisateurs u=utilisateurRepository.findById(utilisateurs.getId()).get();
        //Demande demande1=demandeRepository.findById(demande.getId()).get();
        Notification notification=new Notification();
        notification.setDatenotif(new Date());
        notification.setDescription(utilisateurs.getPrenom()+" "+utilisateurs.getNom() +"a fait une demande d'acte de décès: " );
        Notification notification1= notificationRepository.save(notification);
        List<Agents> agentsList =  agentRepository.findByStructure(casstructure);
        for (Agents agents : agentsList){
            agents.getNotification().add(notification1);
            agentRepository.save(agents);
        }
        if (file != null){
            casierJudiciaire1.setPhotoacten(SaveImage.save("casier",file,casierJudiciaire1.getNom()));

            if(u == null){
                return ResponseMessage.generateResponse("error", HttpStatus.OK, "user problemme");

            }
            else{
                casierJudiciaire1.setMastructure(casstructure);
                casierJudiciaire1.setUser(u);
                System.out.println("/////////////////////////struct:"+ casierJudiciaire1.getMastructure().getNom());
                //  if (casierJudiciaire1.getLieudenaissance() == utilisateurs.get)
                //si le lieu de naissance == lieu de la structure recherche de mot clé
               // casierJudiciaire1.setLieudenaissance(lieudenaissance);
                return demandeservice.creercasier(casierJudiciaire1,lieudenaissance,casierJudiciaire1.getMastructure().getNom());

            }
        }
        else {
            return ResponseMessage.generateResponse("error", HttpStatus.OK, "Fichier vide");
        }



    }

    @GetMapping("/listcasierbystruct/{structure}")
    public  Object  allcas(@PathVariable Structure structure ){

        return casierRepo.findByMastructure(structure);
    }








    @GetMapping("/list/{user}")
    public Object listcas( @PathVariable Utilisateurs user){
        return  casierRepo.findByUser(user);

    }
    @GetMapping("/getacte/{casierJudiciaire}")
    public CasierJudiciaire getacet(@PathVariable CasierJudiciaire casierJudiciaire){
        return  casierRepo.findById(casierJudiciaire.getId()).get();

    }

    ////////////////////////    //////////////////////// valider   ////////////////////////    ////////////////////////

}
