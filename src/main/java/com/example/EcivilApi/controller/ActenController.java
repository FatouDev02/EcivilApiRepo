package com.example.EcivilApi.controller;

import com.example.EcivilApi.configuration.ResponseMessage;
import com.example.EcivilApi.models.*;
import com.example.EcivilApi.repository.*;
import com.example.EcivilApi.services.Demandeservice;
import com.example.EcivilApi.services.StructureService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
    UtilisateurRepository utilisateurRepository;
    @Autowired
    DemandeRepository demandeRepository;
    @Autowired
    NotificationRepository notificationRepository;
    @Autowired
    AgentRepo agentRepository;
    @Autowired
    StructureRepository structureRepository;
    //a la creation d'un acte de naissance on donne on donne l'id de la demande or une demande est liéé à,un strucure
    //donc acten est liée a la structure dont la demande est liée
    @PostMapping("/add/{numvolet}/{structure}/{utilisateurs}")
    public Object createacten(

            @RequestParam(value = "acten",required = true) String acten,
            @PathVariable String numvolet,@PathVariable Structure structure,@PathVariable Utilisateurs utilisateurs
            ) throws JsonProcessingException {
        Acten acten1 = new JsonMapper().readValue(acten, Acten.class);
        Acten c = actenRepo.findByNumvolet(numvolet);
        Notification notification=new Notification();
        notification.setDatenotif(new Date());
        notification.setDescription("efghjklfdsqdfghj");
        Notification notification1= notificationRepository.save(notification);
        List<Agents> agentsList =  agentRepository.findByStructure(structure);

        for (Agents agents : agentsList){
            agents.getNotification().add(notification1);
            agentRepository.save(agents);
        }


        Utilisateurs u=utilisateurRepository.findById(utilisateurs.getId()).get();
        //Demande demande1=demandeRepository.findById(demande.getId()).get();


        if (c != null ){
            return ResponseMessage.generateResponse("error", HttpStatus.OK, "problemenumvolet");
        }else if(u == null){
            return ResponseMessage.generateResponse("error", HttpStatus.OK, "user problemme");

        }
        else{
           // acten1.setMademande(demande1);
            //acten1.setMademande(demande);

            acten1.setMastructure(structure);

            acten1.setUser(u);

            return demandeservice.creeracten(acten1,numvolet);

        }

    }



    ///valider ou modifier un acte de naissace
    @PutMapping("/valider/{id}")
    public Object update(@RequestParam(value = "acten",required = true) String acten,@PathVariable Long id) throws JsonProcessingException{
        Acten acten1 = new JsonMapper().readValue(acten, Acten.class);
        return actenRepo.findById(id).map(
                newacten ->{
                    if(acten1.getNom()== null || acten1.getNom().trim().isEmpty() ) {
                        newacten.setNom(newacten.getNom());

                    }else {
                        newacten.setNom(acten1.getNom());
                    }
                    /////
                    if(acten1.getPrenom()== null || acten1.getPrenom().trim().isEmpty() ) {
                        newacten.setPrenom(newacten.getPrenom());

                    }else {
                        newacten.setPrenom(acten1.getPrenom());
                    }
                    ///////
                    if(acten1.getNommere()== null || acten1.getNommere().trim().isEmpty() ) {
                        newacten.setNommere(newacten.getNommere());

                    }else {
                        newacten.setNommere(acten1.getNommere());
                    }
                    ///////

                    if(acten1.getNompere()== null || acten1.getNompere().trim().isEmpty() ) {
                        newacten.setNompere(newacten.getNompere());

                    }else {
                        newacten.setNompere(acten1.getNompere());
                    }
                    //////////


                    if(acten1.getProfpere()== null || acten1.getProfpere().trim().isEmpty() ) {
                        newacten.setProfpere(newacten.getProfpere());

                    }else {
                        newacten.setProfpere(acten1.getProfpere());
                    }
                    /////
                    if(acten1.getProfmere()== null || acten1.getProfmere().trim().isEmpty() ) {
                        newacten.setProfmere(newacten.getProfmere());

                    }else {
                        newacten.setProfmere(acten1.getProfmere());
                    }
                    ////
                    if(acten1.getNumvolet()== null || acten1.getNumvolet().trim().isEmpty() ) {
                        newacten.setNumvolet(newacten.getNumvolet());

                    }else {
                        newacten.setNumvolet(acten1.getNumvolet());
                    }
                    /////


                    if(acten1.getDatedenaissance()== null || acten1.getDatedenaissance().trim().isEmpty() ) {
                        newacten.setDatedenaissance(newacten.getDatedenaissance());

                    }else {
                        newacten.setDatedenaissance(acten1.getDatedenaissance());
                    }
                    //
                    if(acten1.getGenre()== null || acten1.getGenre().trim().isEmpty() ) {
                        newacten.setGenre(newacten.getGenre());

                    }
                    else {
                        newacten.setGenre(acten1.getGenre());
                    }
                    ////////

                    if(acten1.getEtatdemande()== null || acten1.getEtatdemande().trim().isEmpty() ) {
                        newacten.setEtatdemande(newacten.getEtatdemande());
                    }else {
                        newacten.setEtatdemande(acten1.getEtatdemande());
                    }
                    //////
                    return demandeservice.updateacten(id,newacten);
                }
        ).orElseThrow(() -> new RuntimeException("ACte non trouvéé"));


    }
    @GetMapping("/listactenbystruct/{structure}")
    public  Object  allacten(@PathVariable Structure structure ){
        return actenRepo.findByMastructure(structure);
    }

    @GetMapping("/list")
    public List<Acten> listacten(){
        return  actenRepo.findAll();

    }
    @GetMapping("/getacte/{acten}")
    public Acten getacet(@PathVariable Acten acten){
        return  actenRepo.findById(acten.getId()).get();

    }

    ////////////////////////    //////////////////////// valider   ////////////////////////    ////////////////////////









    @PostMapping("/validerrr/{acten}")
    public Demande getdem(@PathVariable Acten acten){
        return  demandeRepository.findById(acten.getId()).get();

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
    @GetMapping("/listdemande")
    public List<Demande> listdem(){
        return  demandeRepository.findAll();

    }


}
