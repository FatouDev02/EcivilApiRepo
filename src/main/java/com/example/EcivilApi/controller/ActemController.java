package com.example.EcivilApi.controller;

import com.example.EcivilApi.configuration.ResponseMessage;
import com.example.EcivilApi.models.*;
import com.example.EcivilApi.repository.ActemRepo;
import com.example.EcivilApi.repository.AgentRepo;
import com.example.EcivilApi.repository.NotificationRepository;
import com.example.EcivilApi.repository.UtilisateurRepository;
import com.example.EcivilApi.services.Demandeservice;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("/ecivil/actem")
@CrossOrigin(origins ={"http://localhost:8100/", "http://localhost:8101/"}, maxAge = 3600,allowCredentials="true")
public class ActemController {

@Autowired
    UtilisateurRepository utilisateurRepository;
@Autowired
    NotificationRepository notificationRepository;

@Autowired
    ActemRepo actemRepo;
@Autowired
    Demandeservice demandeservice;
    @Autowired
    AgentRepo agentRepository;
    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        return mapper;
    }
    //a la creation d'un acte de naissance on donne on donne l'id de la demande or une demande est liéé à,un strucure
    //donc acten est liée a la structure dont la demande est liée
    @PostMapping("/add/{structure}/{utilisateurs}")
    public Object createactem(

            @RequestParam(value = "actem",required = true) String actem,
            @PathVariable Structure structure, @PathVariable Utilisateurs utilisateurs
    ) throws JsonProcessingException {
        Actem actem1 =new JsonMapper().readValue(actem,Actem.class);
/*if(ChronoUnit.DAYS.between((Temporal) new Date(), (Temporal) actem1.getDatemariage()) < 15){
    return ResponseMessage.generateResponse("error", HttpStatus.BAD_REQUEST, "Le mariage doit etre programmé 15 jours envance");

}*/
        Notification notification=new Notification();
        notification.setDatenotif(new Date());
        notification.setDescription(utilisateurs.getPrenom()+" "+utilisateurs.getNom() +"a fait une demande d'acte de Mariage: " );
         Notification notification1= notificationRepository.save(notification);
        List<Agents> agentsList =  agentRepository.findByStructure(structure);

        for (Agents agents : agentsList){
            agents.getNotification().add(notification1);
            agentRepository.save(agents);
        }

       // Acten c = actenRepo.findByNumvolet(numvolet);
        Utilisateurs u=utilisateurRepository.findById(utilisateurs.getId()).get();

        //Demande demande1=demandeRepository.findById(demande.getId()).get();
        if(u == null){
            return ResponseMessage.generateResponse("error", HttpStatus.OK, "user problemme");

        }
        else{
            // acten1.setMademande(demande1);
            //acten1.setMademande(demande);

            actem1.setMastructure(structure);
            actem1.setUser(u);

            return demandeservice.creeractem(actem1);

        }
    }



    @GetMapping("/getacte/{actem}")
    public Actem getacet(@PathVariable Actem actem){
        return  actemRepo.findById(actem.getId()).get();

    }

    @GetMapping("/listactembystruct/{structure}")
    public  Object  allactem(@PathVariable Structure structure ){
        return actemRepo.findByMastructure(structure);
    }
    @GetMapping("/list/{user}")
    public Object listactem( @PathVariable Utilisateurs user){
        return  actemRepo.findByUser(user);

    }
    ////////////////////////    //////////////////////// valider   ////////////////////////    ////////////////////////

}
