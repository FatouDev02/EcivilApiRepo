package com.example.EcivilApi.controller;

import com.example.EcivilApi.configuration.ResponseMessage;
import com.example.EcivilApi.models.Acten;
import com.example.EcivilApi.repository.ActenRepo;
import com.example.EcivilApi.services.Demandeservice;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/ecivil/acten")
@CrossOrigin(origins = "http://localhost:8100/", maxAge = 3600,allowCredentials="true")
public class ActenController {
    @Autowired
    Demandeservice demandeservice;
    @Autowired
    ActenRepo actenRepo;

    @PostMapping("/add/{numvolet}")
    public Object createacten(

            @RequestParam(value = "acten",required = true) String acten,
            @PathVariable String numvolet
           ) throws JsonProcessingException {
        Acten acten1 = new JsonMapper().readValue(acten, Acten.class);
        Acten c = actenRepo.findByNumvolet(numvolet);
        if (c != null){
            return ResponseMessage.generateResponse("error", HttpStatus.OK, "problemenumvolet");
            // return populationService.ajout(habitant1);
        }
        else {
            return demandeservice.creeracten(acten1,numvolet);

        }



    }

}
