package com.example.EcivilApi.controller;

import com.example.EcivilApi.configuration.ResponseMessage;
import com.example.EcivilApi.models.Nationnalite;
import com.example.EcivilApi.models.Residence;
import com.example.EcivilApi.models.Structure;
import com.example.EcivilApi.models.Utilisateurs;
import com.example.EcivilApi.repository.NationnaliteRepo;
import com.example.EcivilApi.repository.Residncerepo;
import com.example.EcivilApi.repository.UtilisateurRepository;
import com.example.EcivilApi.services.Demandeservice;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/ecivil/casier")
@CrossOrigin(origins = "http://localhost:8100/", maxAge = 3600,allowCredentials="true")
public class NationnalitéController {
    @Autowired
    NationnaliteRepo nationnaliteRepo ;

    @Autowired
    UtilisateurRepository utilisateurRepository;
    @Autowired
    Demandeservice demandeservice;



    @PostMapping("/add/{mastructure}/{utilisateurs}")
    public Object createnationnalite(

            @RequestParam(value = "nationnalite",required = true) String nationnalite,
            @PathVariable Structure mastructure, @PathVariable Utilisateurs utilisateurs
    ) throws JsonProcessingException {
        Nationnalite nationnalite1   =new JsonMapper().readValue(nationnalite,Nationnalite.class);


        // Acten c = actenRepo.findByNumvolet(numvolet);
        Utilisateurs u=utilisateurRepository.findById(utilisateurs.getId()).get();
        //Demande demande1=demandeRepository.findById(demande.getId()).get();
        if(u == null){
            return ResponseMessage.generateResponse("error", HttpStatus.OK, "user problemme");

        }
        else{

            nationnalite1.setMastructure(mastructure);
            nationnalite1.setUser(u);
            return demandeservice.creernationlt(nationnalite1);

        }
    }

}
