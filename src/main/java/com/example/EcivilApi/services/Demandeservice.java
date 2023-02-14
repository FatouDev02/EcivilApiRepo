package com.example.EcivilApi.services;

import com.example.EcivilApi.models.*;

import java.util.List;

public interface Demandeservice {
    // Création
   // Object creerdemande(Demande demande,String type);

    Object creerdemande(Demande demande,Long idtypestruct);

    Object creeracten(Acten acten, String numvolet);
    Object creeractem(Actem actem);
    Object creeracted(ActeD acteD,String numvolet);
    Object creercasier(CasierJudiciaire casierJudiciaire,String lieudenaissance,String nomstruct);
    Object creerresidence(Residence residence,String lieuderesidence,String nomstruct);
    Object creernationlt(Nationnalite nationnalite,String lieuderesidence,String nomstruct);

    Acten valider(Long idPostulant);

    // Mise à jour
    Demande update(Long id, Utilisateurs personne);
    Object updateacten(Long id,Acten acten);
    Actem updateactem(Long id,Actem actem);
    ActeD updateacted(Long id,ActeD acteD);
    CasierJudiciaire updatecasier(Long id,CasierJudiciaire casierJudiciaire);
    Residence updateresidence(Long id,Residence residence);
    Nationnalite updatenationlt(Long id,Nationnalite nationnalite);

    // recuperer l'ensemble des personnes
    Object getAll();
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
    String attribuerstruct(long iddem, long idstruct);
    Demande findbytype(String type);





}
