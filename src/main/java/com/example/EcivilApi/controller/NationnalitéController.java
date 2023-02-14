package com.example.EcivilApi.controller;

import com.example.EcivilApi.configuration.ResponseMessage;
import com.example.EcivilApi.configuration.SaveImage;
import com.example.EcivilApi.models.*;
import com.example.EcivilApi.repository.NationnaliteRepo;
import com.example.EcivilApi.repository.Residncerepo;
import com.example.EcivilApi.repository.UtilisateurRepository;
import com.example.EcivilApi.services.Demandeservice;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
        return nationnaliteRepo.findByMastructure(structure);
    }

    @GetMapping("/listnat")
    public List<Nationnalite> listnat(){
        return  nationnaliteRepo.findAll();

    }
    @GetMapping("/getacte/{acten}")
    public Nationnalite getacet(@PathVariable Nationnalite nationnalite){
        return  nationnaliteRepo.findById(nationnalite.getId()).get();

    }

    ////////////////////////    //////////////////////// valider   ////////////////////////    ////////////////////////


}
