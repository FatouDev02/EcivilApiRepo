package com.example.EcivilApi.serviceimpl;

import com.example.EcivilApi.configuration.ResponseMessage;
import com.example.EcivilApi.models.*;
import com.example.EcivilApi.repository.*;
import com.example.EcivilApi.services.StructureService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Slf4j
public class StructureImpl implements StructureService {
    @Autowired
    StructureRepository structureRepository;
    @Autowired
    Mairierepo mairierepo;
    @Autowired
    Tribrepo tribrepo;
    @Autowired
    CommiRepo commiRepo;


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



    /* @Override
    public Object creermairie(Mairie mairie,String nom,String typestruct) {
        //si le numvolet existe
        if (mairierepo.findByNom(nom) !=null){
            return ResponseMessage.generateResponse("error", HttpStatus.OK, " nom existant  déja existe deja !");

        }else{
            mairie.setNom(nom);
            return mairierepo.save(mairie);
        }
    }
*/
    @Override
    public Object creertrib(Tribunal tribunal,String Nom) {
        return null;
    }

    @Override
    public Object creercomm(Commissariats commissariats,String Nom) {
        return null;
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
    public List<Typestructure> lister() {
        return typestructrepo.findAll();
    }

    @Override
    public List<Structure> listertypestruct() {
        return structureRepository.findAll();
    }


    @Override
    public Structure update(Long id, Structure structure) {
        return null;
    }
    @Override
    public Structure GetById(Long id) {
        return structureRepository.findById(id).get();
    }
}
