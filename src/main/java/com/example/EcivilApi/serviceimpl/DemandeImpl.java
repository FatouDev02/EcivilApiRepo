package com.example.EcivilApi.serviceimpl;

import com.example.EcivilApi.configuration.ResponseMessage;
import com.example.EcivilApi.models.*;
import com.example.EcivilApi.repository.*;
import com.example.EcivilApi.services.Demandeservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class DemandeImpl  implements Demandeservice {
    @Autowired
    ActenRepo actenRepo;
    @Autowired
    ActemRepo actemRepo;
    @Autowired
    ActedRepo actedRepo;
    @Autowired
    CasierRepo casierRepo;
    @Autowired
    NationnaliteRepo nationnaliteRepo;
    @Autowired
    Residncerepo residncerepo;
    @Autowired
    DemandeRepository demandeRepository;
    @Autowired
    StructureRepository structureRepository;
    @Autowired
    Typestructrepo typestructrepo;
    @Override
    public Object creerdemande(Demande demande,Long idtypestruct) {
        /*if (demandeRepository.findByType(type) !=null){
            return ResponseMessage.generateResponse("error", HttpStatus.OK, " numvolet existe deja !");

        }else{
            demande.setType(type);
            return demandeRepository.save(demande);

        }*/
        Typestructure typestructure=typestructrepo.findById(idtypestruct).get();
        demande.setTypestructure(typestructure);
        demande.setDatededeclaration(new Date());
        return demandeRepository.save(demande);

    }

//////////////////////Création des types de demandes//////////////////////////////////
    public Object creeracten(Acten acten,String numvolet) {
        //si le numvolet existe
        if (actenRepo.findByNumvolet(numvolet) !=null){
            return ResponseMessage.generateResponse("error", HttpStatus.OK, " numvolet existe deja !");

        }else{
            acten.setNumvolet(numvolet);
            return actenRepo.save(acten);
        }

    }

    @Override
    public Object creeractem(Actem actem) {
        return actemRepo.save(actem);

    }

    public Object creeracted(ActeD acteD,String numvolet) {
        //si le numvolet existe
        if (actedRepo.findByNumvolet(numvolet) !=null){
            return ResponseMessage.generateResponse("error", HttpStatus.OK, " numvolet existe deja !");

        }else{
            acteD.setNumvolet(numvolet);
            return actedRepo.save(acteD);
        }

    }

    @Override
    public Object creercasier(CasierJudiciaire casierJudiciaire,String lieudenaissance,String nomstruct ) {
        Structure structure=structureRepository.findByNom(nomstruct);
        if (!casierJudiciaire.getLieudenaissance().equals(structure.getNom())){
            return ResponseMessage.generateResponse("error", HttpStatus.OK,
                    " le lieu de naissance doit correspondre au lieu de la structure !");

        }else{
            casierJudiciaire.setLieudenaissance(lieudenaissance);
            return casierRepo.save(casierJudiciaire);
        }
    }

    @Override
    public Object creerresidence(Residence residence,String lieuderesidence,String nomstruct ) {
        Structure structure=structureRepository.findByNom(nomstruct);
        if (!residence.getLieuderesidence().equals(structure.getNom())){
            return ResponseMessage.generateResponse("error", HttpStatus.OK,
                    " le lieu de residence doit correspondre au lieu de la structure !");

        }else{
            residence.setLieuderesidence(lieuderesidence);
            return residncerepo.save(residence);
        }
    }

    @Override
    public Object creernationlt(Nationnalite nationnalite,String lieuderesidence,String nomstruct) {
        Structure structure=structureRepository.findByNom(nomstruct);
        if (!nationnalite.getLieuderesidence().equals(structure.getNom())){
            return ResponseMessage.generateResponse("error", HttpStatus.OK,
                    " le lieu de residence doit correspondre au lieu de la structure !");

        }else{
            nationnalite.setLieuderesidence(lieuderesidence);
            return nationnaliteRepo.save(nationnalite);
        }

    }

    @Override
    public Acten valider(Acten acten) {
        Acten acten1=this.actenRepo.findById(acten.getId()).get();
        acten1.setEtatdemande("Valider");
        return actenRepo.save(acten1);
    }
    //////////////////////Fin  Création des types de demandes//////////////////////////////////

/*    public Acten valider(Long id) {
        Acten acten= actenRepo.findById(id).get();
        acten.setEtatdemande(true);
        return actenRepo.save(acten);
    }*/

    @Override
    public Demande update(Long id, Utilisateurs personne) {
        return null;
    }

    @Override
    public Object updateacten(Long id, Acten acten) {
        acten.setId(id);
        actenRepo.save(acten);
        return "dzgu";
    }

    @Override
    public Actem updateactem(Long id, Actem actem) {
        return null;
    }

    @Override
    public ActeD updateacted(Long id, ActeD acteD) {
        return null;
    }

    @Override
    public CasierJudiciaire updatecasier(Long id, CasierJudiciaire casierJudiciaire) {
        return null;
    }

    @Override
    public Residence updateresidence(Long id, Residence residence) {
        return null;
    }

    @Override
    public Nationnalite updatenationlt(Long id, Nationnalite nationnalite) {
        return null;
    }

    @Override
    public Object getAll() {
       List<Acten> a= actenRepo.findAll();
        List<Actem> b= actemRepo.findAll();
        List<CasierJudiciaire> d= casierRepo.findAll();
        return "tout";
    }

    @Override
    public List<Acten> getAllacten() {
        return null;
    }

    @Override
    public List<Actem> getAllactem() {
        return null;
    }

    @Override
    public List<ActeD> getAllacted() {
        return null;
    }

    @Override
    public List<CasierJudiciaire> getAllcasier() {
        return null;
    }

    @Override
    public List<Residence> getAllresidence() {
        return null;
    }

    @Override
    public List<Nationnalite> getAllnationlt() {
        return null;
    }

    @Override
    public Demande GetByid(Long id) {
        return demandeRepository.findById(id).get();
    }

    @Override
    public Acten GetActenById(Long id) {
        return null;
    }

    @Override
    public Actem GetActemById(Long id) {
        return null;
    }

    @Override
    public ActeD GetActeDById(Long id) {
        return null;
    }

    @Override
    public CasierJudiciaire GetCasierById(Long id) {
        return null;
    }

    @Override
    public Residence GetResidenceById(Long id) {
        return null;
    }

    @Override
    public Nationnalite GetNationnaliteById(Long id) {
        return null;
    }

    @Override
    public String attribuerstruct(long iddem, long idstruct) {
        Demande mademande=demandeRepository.findById(iddem).orElse(null);
        Structure mastructure=structureRepository.findById(idstruct).orElse(null);
        if (mastructure != null) {
            mademande.setStructure(mastructure);
            demandeRepository.save(mademande);
            return "Demande attribuée avec succès !";
        } else
            return "Cette Structure n'existe pas !";
    }

    @Override
    public Demande findbytype(String type) {
        return demandeRepository.findByType(type);
    }
}
