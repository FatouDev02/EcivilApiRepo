package com.example.EcivilApi.controller;

import com.example.EcivilApi.configuration.ResponseMessage;
import com.example.EcivilApi.models.Actem;
import com.example.EcivilApi.models.CasierJudiciaire;
import com.example.EcivilApi.models.Structure;
import com.example.EcivilApi.models.Utilisateurs;
import com.example.EcivilApi.repository.CasierRepo;
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
public class CasierController {

    @Autowired
    CasierRepo casierRepo;

    @Autowired
    UtilisateurRepository utilisateurRepository;
    @Autowired
    Demandeservice demandeservice;
    //a la creation d'un acte de naissance on donne on donne l'id de la demande or une demande est liéé à,un strucure
    //donc acten est liée a la structure dont la demande est liée
    @PostMapping("/add/{casstructure}/{utilisateurs}")
    public Object createcasier(

            @RequestParam(value = "casier",required = true) String casier,
            @PathVariable Structure casstructure, @PathVariable Utilisateurs utilisateurs
    ) throws JsonProcessingException {
        CasierJudiciaire casierJudiciaire1 =new JsonMapper().readValue(casier,CasierJudiciaire.class);


        // Acten c = actenRepo.findByNumvolet(numvolet);
        Utilisateurs u=utilisateurRepository.findById(utilisateurs.getId()).get();
        //Demande demande1=demandeRepository.findById(demande.getId()).get();
        if(u == null){
            return ResponseMessage.generateResponse("error", HttpStatus.OK, "user problemme");

        }
        else{
            // acten1.setMademande(demande1);
            //acten1.setMademande(demande);

            casierJudiciaire1.setMastructure(casstructure);
            casierJudiciaire1.setUser(u);
            return demandeservice.creercasier(casierJudiciaire1);

        }
    }


}
