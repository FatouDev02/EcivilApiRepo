package com.example.EcivilApi.serviceimpl;

import com.example.EcivilApi.configuration.EmailConstructor;
import com.example.EcivilApi.configuration.ResponseMessage;
import com.example.EcivilApi.models.*;
import com.example.EcivilApi.repository.*;
import com.example.EcivilApi.services.StructureService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
@Slf4j
public class StructureImpl implements StructureService {
    @Autowired
    StructureRepository structureRepository;
    @Autowired
    Mairierepo mairierepo;
    @Autowired
ActenRepo actenRepo;
    @Autowired
    CommiRepo commiRepo;
    @Autowired
    EmailConstructor emailConstructor;
    @Autowired
    JavaMailSender mailSender;
@Autowired
    Typestructrepo typestructrepo;

    @Override
    public Object creer(Structure structure) {
        //si le type existe


        log.info("Ajout d'une strucure de type  {} ", structure.getNom());
        //a l'enregistrement on recupère le passwor et le l'encode
        if (structureRepository.findByNom(structure.getNom()) !=null) {
            return ResponseMessage.generateResponse("error", HttpStatus.OK, " Une Structure du" +
                    "meme nom  existe deja !");
        }
        else{
           // structure.setNom(structure.getNom());
            return structureRepository.save(structure);
        }
    }


    @Override
    public Object creertype(Typestructure typestructure) {
        //si le type existe

        log.info("Ajout d'une structure de type  {} ",typestructure.getType());
        if (typestructrepo.findByType(typestructure.getType()) !=null) {
            return ResponseMessage.generateResponse("error", HttpStatus.OK, " Une Structure du" +
                    "meme type  existe deja !");
        }
        else{
            typestructure.setType(typestructure.getType());
            return typestructrepo.save(typestructure);
        }
    }



    @Override
    public Typestructure getbytype(String type) {
        return typestructrepo.findByType(type);
    }

    @Override
    public void deletestruct(long id) {

        structureRepository.deleteById(id);
    }

    @Override
    public List<Structure> structbytype() {
        return null;
    }

    @Override
    public Structure findByAgents(Agents agents) {
        return structureRepository.findByAgents(agents);
    }

    @Override
   // @Scheduled(cron = "0 0 8 * * *")
    public Object generateUserPresenceList(Structure structure,Long nbre) {
       // users.clear();
        var totaluser=0;
        List<Utilisateurs> users = new ArrayList<>();
       //  Acten acten=new Acten();
      //   List<Acten> actenList= new ArrayList<>();

            for(Acten a: actenRepo.findAllByMastructure(structure)){
              //  if(u.getId().equals(a.getUser().getId()) ){
                  //  users.clear();
                if(a.getEtatdemande() != null){
                    users.add(a.getUser());
                    totaluser = users.size();
                   //  System.out.println("//////////////////////////////////ok utilisateurs111111"+totaluser);

                }


            }
        List<Utilisateurs> utilisateurs2=new ArrayList<>();

            if(nbre > totaluser){
                ResponseMessage messages=new ResponseMessage("Veuillez donner un nombre inférieur à  " + totaluser,false);
              //  System.out.println("//////////////////////////////////ok message" + messages.getContenue());
                return  messages;

            } else{
                for(int i=0; i<nbre;i++) {

                    utilisateurs2.add(users.get(i));

                }
                //  mailSender.send(emailConstructor.rdvusers(a.getUser()));

            }


        return  utilisateurs2;



    }

    @Override
    public List<Typestructure> lister() {
        return typestructrepo.findAll();
    }

    @Override
    public List<Structure> listertypestruct() {
        return structureRepository.findAll();
    }


    @Override
    public Structure update(Long id, Structure structure) {
        structure.setId(id);
        return structureRepository.save(structure);
    }
    @Override
    public Structure GetById(Long id) {
        return structureRepository.findById(id).get();
    }

}
