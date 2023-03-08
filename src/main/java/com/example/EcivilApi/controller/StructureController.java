package com.example.EcivilApi.controller;


import com.example.EcivilApi.models.*;
import com.example.EcivilApi.repository.*;
import com.example.EcivilApi.services.Demandeservice;
import com.example.EcivilApi.services.StructureService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/ecivil/struct")
@CrossOrigin(origins = {"http://localhost:8100/", "http://localhost:8101/"}, maxAge = 3600,allowCredentials="true")
public class StructureController {
@Autowired
    StructureService structureService;
@Autowired
    StructureRepository structrepository;
@Autowired
    Typestructrepo typestructrepo;
@Autowired
    DemandeRepository demandeRepository;
@Autowired
    Demandeservice demandeservice;
@Autowired
    AgentRepo agentRepo;
@Autowired
    UtilisateurRepository utilisateurRepository;
//Ajouter une struct avec longitude et latitude
    @PostMapping("/add/{typestructure}")
    public Object createstruct(

            // @RequestParam(value = "structure",required = true) Structure structure,

            @RequestParam(value = "structt2",required = true) String structt2, @PathVariable Typestructure typestructure
            ) throws JsonProcessingException {
    /*    if (structrepository.findByType(nom) != null){
            return ResponseMessage.generateResponse("error", HttpStatus.OK, "problemetypestructure");
            // return populationService.ajout(habitant1);
        }
        else {
            structure.setNom(nom);
            return structureService.creer(structure);

        }*/
        Structure structure1=new JsonMapper().readValue(structt2,Structure.class);
        structure1.setTypestructure(typestructure);
        return structureService.creer(structure1);
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
    public Typestructure get(@PathVariable Typestructure typestructure){
        return typestructrepo.findById(typestructure.getId()).get();
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


    @DeleteMapping("/delete/{id}")
    public String  del(@PathVariable Long id){

        structrepository.deleteById(id);
        return "Structure supprimé";
    }
    @PutMapping("/update/{id}")
    public Structure update(@RequestParam(value = "struct",required = true) String struct, @PathVariable Long id) throws  JsonProcessingException{
        Structure structure1=new JsonMapper().readValue(struct,Structure.class);
        return structrepository.findById(id).map(
                newstruct ->{
                    if(structure1.getNom()== null || structure1.getNom().trim().isEmpty() ) {
                        newstruct.setNom(newstruct.getNom());

                    }else {
                        newstruct.setNom(structure1.getNom());
                    }
                    /////
                    if(structure1.getLongitude()== null || structure1.getLongitude().trim().isEmpty() ) {
                        newstruct.setLongitude(newstruct.getLongitude());

                    }else {
                        newstruct.setLongitude(structure1.getLongitude());
                    }
                    /////
                    if(structure1.getLatitude()== null || structure1.getLatitude().trim().isEmpty() ) {
                        newstruct.setLatitude(newstruct.getLatitude());

                    }else {
                        newstruct.setLatitude(structure1.getLatitude());
                    }


                return structureService.update(id,newstruct);
            }).orElseThrow(() -> new RuntimeException("Structure non trouvéé"));

    }




    //recuperer un agent par id
    @GetMapping("/getagent/{agents}")
    public Agents getagent(@PathVariable Agents agents){
        return agentRepo.findById(agents.getId()).get();
    }

    @GetMapping("/getuser/{utilisateurs}")
    public Utilisateurs getaguser(@PathVariable Utilisateurs utilisateurs){
        return utilisateurRepository.findById(utilisateurs.getId()).get();
    }

    @GetMapping("/listtype")
    public List<Typestructure> l(){

        return this.structureService.lister();
    }
    @GetMapping("/list")
    public List<Structure> list(){

        return this.structureService.listertypestruct();
    }
    @GetMapping("/rdv/{structure}/{number}")
    public Object rdv(@PathVariable  Structure structure,@PathVariable Long number){

        return structureService.generateUserPresenceList(structure,number);
    }
    @GetMapping("/rdvactem/{structure}/{number}")
    public Object rdvactem(@PathVariable  Structure structure,@PathVariable Long number){

        return demandeservice.generateUserPresenceactem(structure,number);
    }
    @GetMapping("/rdvacted/{structure}/{number}")
    public Object rdvacted(@PathVariable  Structure structure,@PathVariable Long number){

        return  demandeservice.generateUserPresenceacted(structure,number);
    }
    @GetMapping("/rdvcas/{structure}/{number}")
    public Object rdvcas(@PathVariable  Structure structure,@PathVariable Long number){

        return  demandeservice.generateUserPresenceactcas(structure,number);
    }
    @GetMapping("/rdvres/{structure}/{number}")
    public Object rdvres(@PathVariable  Structure structure,@PathVariable Long number){

        return demandeservice.generateUserPresenceactres(structure,number);
    }
    @GetMapping("/rdvnat/{structure}/{number}")
    public Object rdvnat(@PathVariable  Structure structure,@PathVariable Long number){

        return  demandeservice.generateUserPresenceactnat(structure,number);
    }


    @GetMapping("/getbyagent/{agents}")
    public Structure findByAgents(@PathVariable Agents agents){
        return  structureService.findByAgents(agents);

    }

    @GetMapping("/getstruct/{structure}")
    public Structure findByid(@PathVariable Structure structure){
        return  structureService.GetById(structure.getId());

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

