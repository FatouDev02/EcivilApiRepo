package com.example.EcivilApi.controller;

import com.example.EcivilApi.configuration.ResponseMessage;
import com.example.EcivilApi.models.Acten;
import com.example.EcivilApi.models.Demande;
import com.example.EcivilApi.models.Structure;
import com.example.EcivilApi.models.Typestructure;
import com.example.EcivilApi.repository.ActenRepo;
import com.example.EcivilApi.repository.DemandeRepository;
import com.example.EcivilApi.repository.StructureRepository;
import com.example.EcivilApi.services.Demandeservice;
import com.example.EcivilApi.services.StructureService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/ecivil/acten")
@CrossOrigin(origins = "http://localhost:8100/", maxAge = 3600,allowCredentials="true")
public class ActenController {
    @Autowired
    Demandeservice demandeservice;
    @Autowired
    StructureService structureService;
    @Autowired
    ActenRepo actenRepo;
    @Autowired
    DemandeRepository demandeRepository;
    @Autowired
    StructureRepository structureRepository;
    //a la creation d'un acte de naissance on donne on donne l'id de la demande or une demande est liéé à,un strucure
    //donc acten est liée a la structure dont la demande est liée
    @PostMapping("/add/{numvolet}/{demande}")
    public Object createacten(

            @RequestParam(value = "acten",required = true) String acten,
            @PathVariable String numvolet, @PathVariable Demande demande
            ) throws JsonProcessingException {
        Acten acten1 = new JsonMapper().readValue(acten, Acten.class);
        Acten c = actenRepo.findByNumvolet(numvolet);
        if (c != null){
            return ResponseMessage.generateResponse("error", HttpStatus.OK, "problemenumvolet");
        }
        else{
            acten1.setMademande(demande);
            return demandeservice.creeracten(acten1,numvolet);

        }



    }
    @GetMapping("/getdemandebyid/{demande}")
    public Demande getdem(@PathVariable Demande demande){
        return  demandeRepository.findById(demande.getId()).get();

    }
    @PostMapping("/attribuerdemandeastruct/{demande}/{structure}")
    public String liaison(@PathVariable Demande demande,@PathVariable Structure structure){
     /*   demandeservice.GetByid(demande.getId());
        structureService.GetById(structure.getId());*/

        return  demandeservice.attribuerstruct(demande.getId(),structure.getId());

    }
    //next ajouter l'ide  de la structure a la creation d'une demande



    @PostMapping("/addemande/{typestructure}/{structure}")
    public  Object createdemande(/*@RequestParam(value = "demande",required = true) String demande,*/
            @RequestBody Demande demande,
                                 @PathVariable Typestructure typestructure,
                                 @PathVariable Structure structure ) throws JsonProcessingException{
      //  Demande demande1 = new JsonMapper().readValue(demande, Demande.class);
        demande.setStructure(structure);
        demande.setTypestructure(typestructure);
        demande.setDatededeclaration(new Date());
        return demandeservice.creerdemande(demande,typestructure.getId());

    }

}
