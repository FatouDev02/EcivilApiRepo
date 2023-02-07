package com.example.EcivilApi.controller;

import com.example.EcivilApi.configuration.ResponseMessage;
import com.example.EcivilApi.models.*;
import com.example.EcivilApi.repository.ActedRepo;
import com.example.EcivilApi.repository.ActemRepo;
import com.example.EcivilApi.repository.UtilisateurRepository;
import com.example.EcivilApi.services.Demandeservice;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/ecivil/acted")
@CrossOrigin(origins = "http://localhost:8100/", maxAge = 3600,allowCredentials="true")
public class ActedController {


    @Autowired
    UtilisateurRepository utilisateurRepository;

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
    @GetMapping("/list")
    public List<ActeD> listacted(){
        return  actedRepo.findAll();

    }
    @GetMapping("/getacte/{acted}")
    public ActeD getacet(@PathVariable ActeD acteD){
        return  actedRepo.findById(acteD.getId()).get();

    }
    @GetMapping("/listactedbystruct/{structure}")
    public  Object  allacted(@PathVariable Structure structure ){
        return actedRepo.findByMastructure(structure);
    }
    ////////////////////////    //////////////////////// valider   ////////////////////////    ////////////////////////

}
