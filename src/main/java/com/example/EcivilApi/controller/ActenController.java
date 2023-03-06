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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/ecivil/acten")
@CrossOrigin(origins = {"http://localhost:8100/", "http://localhost:8101/"}, maxAge = 3600,allowCredentials="true")
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
    @Autowired
    Notifrdvuserrepo notifrdvuserrepo;
    //a la creation d'un acte de naissance on donne on donne l'id de la demande or une demande est liéé à,un strucure
    //donc acten est liée a la structure dont la demande est liée
    @PostMapping("/add/{structure}/{utilisateurs}")
    public Object createacten(

            @RequestParam(value = "acten",required = true) String acten,
            @PathVariable Structure structure,@PathVariable Utilisateurs utilisateurs
            ) throws JsonProcessingException {
        Acten acten1 = new JsonMapper().readValue(acten, Acten.class);
        Acten c = actenRepo.findByNumvolet(acten1.getNumvolet());
        Notification notification=new Notification();
        notification.setDatenotif(new Date());
        notification.setDescription(utilisateurs.getPrenom()+" "+utilisateurs.getNom() +" a fait une demande d'acte de Naissance: " );
        Notification notification1= notificationRepository.save(notification);
        List<Agents> agentsList =  agentRepository.findByStructure(structure);
        if(acten1.getDatedenaissance().after(new Date())){
            System.out.println("//////////////////////////////////////"+ acten1.getDatedenaissance());

            ResponseMessage message = new ResponseMessage("La date de naissance ne peut pas depassé aujourd'dhui",HttpStatus.BAD_REQUEST) ;
            //ResponseMessage.generateResponse("error", HttpStatus.BAD_REQUEST, " La date de naissance ne peut pas depassé aujourd'dhui");
            return message;
        }
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

            return demandeservice.creeracten(acten1,acten1.getNumvolet());

        }

    }



    ///valider ou modifier un acte de naissace
    @PutMapping("/modifier/{id}")
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


                    if(acten1.getDatedenaissance()== null) {
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


    ///valider ou modifier un acte de naissace
    @PutMapping("/update/{id}")
    public Object valider(@RequestParam(value = "acten",required = true) String acten,@PathVariable Long id) throws JsonProcessingException{
        Acten acten1 = new JsonMapper().readValue(acten, Acten.class);
        return actenRepo.findById(id).map(
                newacten ->{
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


                    if(acten1.getDatedenaissance()== null) {
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
    @PostMapping("/validation/{id}")
    public  Object  valider(@PathVariable Long id){
        /*Acten acten= actenRepo.findById(id).get();
        if(acten1.getEtatdemande()== null || acten1.getEtatdemande().trim().isEmpty() ) {
            acten.setEtatdemande(acten.getEtatdemande());
        }else {
            acten.setEtatdemande("Demande Validée");
        }
        return demandeservice.updateacten(id,acten);*/
        Acten acten= actenRepo.findById(id).get();
        acten.setEtatdemande("Demande Validée");
        //envoie un message à user
        Notifrdvuser notifica= new Notifrdvuser();
        notifica.setDatenotification(new Date());
        // Date dateLimite = new Date(System.currentTimeMillis() + (2 * 24 * 60 * 60 * 1000));
        LocalDate dateLimitee = LocalDate.now().plusDays(2);


        notifica.setNdescription(" Votre demande a été prise en compte veuillez récuperer le document a cette date: " + dateLimitee);
        Notifrdvuser notificationn= notifrdvuserrepo.save(notifica);
        acten.getUser().getNotifrdvusers().add(notificationn);
        return demandeservice.updateacten(id,acten);

    }



    @GetMapping("/listactenbystruct/{structure}")
    public  Object  allacten(@PathVariable Structure structure ){
     List<Acten> actenList=actenRepo.findByMastructure(structure);
        List<Acten> newact=new ArrayList<>();

        for (Acten al:actenList){
            if(al.getEtatdemande()==null){
               newact.add(al);
            }
        }
                return newact;
    }

    @GetMapping("/list/{user}")
    public Object listacten( @PathVariable Utilisateurs user){
        return  actenRepo.findByUser(user);

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
