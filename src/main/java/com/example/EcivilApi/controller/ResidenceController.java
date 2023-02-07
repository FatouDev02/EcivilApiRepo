package com.example.EcivilApi.controller;

import com.example.EcivilApi.configuration.ResponseMessage;
import com.example.EcivilApi.models.*;
import com.example.EcivilApi.repository.Residncerepo;
import com.example.EcivilApi.repository.UtilisateurRepository;
import com.example.EcivilApi.services.Demandeservice;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/ecivil/residence")
@CrossOrigin(origins = "http://localhost:8100/", maxAge = 3600,allowCredentials="true")
public class ResidenceController {


    @Autowired
    Residncerepo residncerepo;

    @Autowired
    UtilisateurRepository utilisateurRepository;
    @Autowired
    Demandeservice demandeservice;

    @PostMapping("/add/{resstructure}/{utilisateurs}")
    public Object createresidence(

            @RequestParam(value = "residence",required = true) String residence,
            @PathVariable Structure resstructure, @PathVariable Utilisateurs utilisateurs
    ) throws JsonProcessingException {
        Residence residence1  =new JsonMapper().readValue(residence,Residence.class);


        // Acten c = actenRepo.findByNumvolet(numvolet);
        Utilisateurs u=utilisateurRepository.findById(utilisateurs.getId()).get();
        //Demande demande1=demandeRepository.findById(demande.getId()).get();
        if(u == null){
            return ResponseMessage.generateResponse("error", HttpStatus.OK, "user problemme");

        }
        else{

            residence1.setMastructure(resstructure);
            residence1.setUser(u);
            return demandeservice.creerresidence(residence1);

        }
    }
    @GetMapping("/listres")
    public List<Residence> listresid(){
        return  residncerepo.findAll();

    }
    @GetMapping("/getacte/{acten}")
    public Residence getacet(@PathVariable Residence residence){
        return  residncerepo.findById(residence.getId()).get();

    }

    ////////////////////////    //////////////////////// valider   ////////////////////////    ////////////////////////


}
