package com.example.EcivilApi.services;


import com.example.EcivilApi.models.*;

import java.util.List;

public interface StructureService {

    // Création d'une structure
    Object creer(Structure structure);
    Object creertype(Typestructure typestructure);
    Object creertrib(Tribunal tribunal,String Nom);
    Object creercomm(Commissariats commissariats,String Nom);
    Typestructure getbytype(String type);
    void deletestruct(long id);
    List<Structure> structbytype();


    Structure findByAgents(Agents agents);
Object generateUserPresenceList(Structure structure);

    // Mettre à jour de la structure par sion id
    Structure update(Long id, Structure structure);

    // recuperer l'ensemble des Structures
    List<Typestructure> lister();
    List<Structure> listertypestruct();

    // Retrouver la Structure à travers son id
    Structure GetById(Long id);


}
