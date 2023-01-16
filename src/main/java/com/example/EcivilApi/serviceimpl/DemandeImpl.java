package com.example.EcivilApi.serviceimpl;

import com.example.EcivilApi.configuration.ResponseMessage;
import com.example.EcivilApi.models.*;
import com.example.EcivilApi.repository.*;
import com.example.EcivilApi.services.Demandeservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

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
    @Override
    public Demande creerdemande(Demande demande) {
        return null;
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

    @Override
    public Object creeracted(ActeD acteD) {
        return actedRepo.save(acteD);
    }

    @Override
    public Object creercasier(CasierJudiciaire casierJudiciaire) {
        return casierRepo.save(casierJudiciaire);
    }

    @Override
    public Object creerresidence(Residence residence) {
        return residncerepo.save(residence);
    }

    @Override
    public Object creernationlt(Nationnalite nationnalite) {
        return nationnaliteRepo.save(nationnalite);

    }
    //////////////////////Fin  Création des types de demandes//////////////////////////////////



    @Override
    public Demande update(Long id, Utilisateurs personne) {
        return null;
    }

    @Override
    public Acten updateacten(Long id, Acten acten) {
        return null;
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
    public List<Demande> getAll() {
        return null;
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
        return null;
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
}
