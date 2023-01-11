package com.example.EcivilApi.services;

import com.example.EcivilApi.models.*;

import java.util.List;

public interface Demandeservice {
    // Création
    Demande creerdemande(Demande demande);
    Acten creeracten(Acten acten);
    Actem creeractem(Actem actem);
    ActeD creeracted(ActeD acteD);
    CasierJudiciaire creercasier(CasierJudiciaire casierJudiciaire);
    Residence creerresidence(Residence residence);
    Nationnalite creernationlt(Nationnalite nationnalite);



    // Mise à jour
    Demande update(Long id, Utilisateurs personne);
    Acten updateacten(Long id,Acten acten);
    Actem updateactem(Long id,Actem actem);
    ActeD updateacted(Long id,ActeD acteD);
    CasierJudiciaire updatecasier(Long id,CasierJudiciaire casierJudiciaire);
    Residence updateresidence(Long id,Residence residence);
    Nationnalite updatenationlt(Long id,Nationnalite nationnalite);

    // recuperer l'ensemble des personnes
    List<Demande> getAll();
    List<Acten> getAllacten();
    List<Actem> getAllactem();
    List<ActeD> getAllacted();
    List<CasierJudiciaire> getAllcasier();
    List<Residence> getAllresidence();
    List<Nationnalite> getAllnationlt();
    // Retrouver  à travers l'id
    Demande GetByid(Long id);
    Acten GetActenById(Long id);
    Actem GetActemById(Long id);
    ActeD GetActeDById(Long id);
    CasierJudiciaire GetCasierById(Long id);
    Residence GetResidenceById(Long id);
    Nationnalite GetNationnaliteById(Long id);





}
