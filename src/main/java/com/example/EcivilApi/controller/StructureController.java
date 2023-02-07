package com.example.EcivilApi.controller;


import com.example.EcivilApi.models.*;
import com.example.EcivilApi.repository.DemandeRepository;
import com.example.EcivilApi.repository.StructureRepository;
import com.example.EcivilApi.repository.Typestructrepo;
import com.example.EcivilApi.services.StructureService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/ecivil/struct")
@CrossOrigin(origins = "http://localhost:8100/", maxAge = 3600,allowCredentials="true")
public class StructureController {
@Autowired
    StructureService structureService;
@Autowired
    StructureRepository structrepository;
@Autowired
    Typestructrepo typestructrepo;
@Autowired
    DemandeRepository demandeRepository;
//Ajouter une struct avec longitude et latitude
    @PostMapping("/add/{typestructure}")
    public Object createstruct(

            // @RequestParam(value = "structure",required = true) Structure structure,

            @RequestBody()Structure structure, @PathVariable Typestructure typestructure
            ) throws JsonProcessingException {
    /*    if (structrepository.findByType(nom) != null){
            return ResponseMessage.generateResponse("error", HttpStatus.OK, "problemetypestructure");
            // return populationService.ajout(habitant1);
        }
        else {
            structure.setNom(nom);
            return structureService.creer(structure);

        }*/
        structure.setTypestructure(typestructure);
        return structureService.creer(structure);
    }


    //Ajouter un type de struct avec type et description
    @PostMapping("/add")
    public Object creertype(
            @RequestParam(value = "structt",required = true) String structt) throws JsonProcessingException {
        Typestructure typestructure1=new JsonMapper().readValue(structt,Typestructure.class);
        return structureService.creertype(typestructure1);
    }
    //recuperer un type
    @GetMapping("/gettype/{typestructure}")
    public Optional<Typestructure> get(@PathVariable Typestructure typestructure){
        return this.typestructrepo.findById(typestructure.getId());
    }
    //Structure par type (id)
    @GetMapping("/liststructbytype/{typestructure}")
    public List<Structure> structbytype( @PathVariable Typestructure typestructure){
        Typestructure find=typestructrepo.findById(typestructure.getId()).get();
        List<Structure> newstruct=new ArrayList<>();
        List<Structure> allstruct= structrepository.findAll();
        for(Structure s: allstruct){
            try{
                if(s.getTypestructure().getId().equals(find.getId())){
                    newstruct.add(s);
                }
            }catch (Exception e){

            }

        }

        return newstruct;
    }
    @GetMapping("/liststructbytypebyagent/{typestructure}/{utilisateurs}")
    public List<Structure> structbytypeparunagent(@PathVariable Typestructure typestructure,
                                                @PathVariable Utilisateurs utilisateurs){
        Typestructure find=typestructrepo.findById(typestructure.getId()).get();
        List<Structure> newstruct=new ArrayList<>();
        List<Structure> allstruct= structrepository.findAll();
        for(Structure s: allstruct){
            try{
                if(s.getTypestructure().getId().equals(find.getId())){
                    newstruct.add(s);
                }
            }catch (Exception e){

            }

        }


        return newstruct;
    }

    //Structure par type (id)
    @GetMapping("/listdemandetypestruc/{typestructure}")
    public List<Demande> demandebytypestruct( @PathVariable Typestructure typestructure){
        Typestructure find=typestructrepo.findById(typestructure.getId()).get();
        List<Demande> newdem=new ArrayList<>();
        List<Demande> alldem= demandeRepository.findAll();
        for(Demande s: alldem){
            try{
                if(s.getTypestructure().getId().equals(find.getId())){
                    newdem.add(s);
                }
            }catch (Exception e){

            }

        }

        return newdem;
    }


    @DeleteMapping("/delete/{typestructure}/{structure}")
    public String  del(Typestructure typestructure,Structure structure){

        this.structureService.deletestruct(structure.getId());
        return "Structure supprimé";
    }

    @GetMapping("/listtype")
    public List<Typestructure> l(){

        return this.structureService.lister();
    }
    @GetMapping("/list")
    public List<Structure> list(){

        return this.structureService.listertypestruct();
    }

    @GetMapping("/getbyagent/{agents}")
    public Structure findByAgents(@PathVariable Agents agents){
        return  structureService.findByAgents(agents);

    }

//tout les structures d'un type de struct par id de la strcut principale
   /* @GetMapping("/list/{id}")
    public List<Region> listeparrpays(@PathVariable  Long id){
        Pays pfind=paysRepository.findById(id).get();
        List<Region> newregion = new ArrayList<>();
        List<Region> allregion=regionRepository.findAll();
        for (Region r: allregion ){
            try{
                if(r.getPays().getId().equals(pfind.getId())){
                    newregion.add(r);
                }
            }catch (Exception e){

            }
        }
        return newregion;
    }*/


}

