package com.example.EcivilApi.services;


import com.example.EcivilApi.models.Structure;

import java.util.List;

public interface StructureService {

    // Création d'une structure
    Structure creer(Structure structure);


    // Mettre à jour de la structure par sion id
    Structure update(Long id, Structure structure);

    // recuperer l'ensemble des Structures
    List<Structure> getAll();

    // Retrouver la Structure à travers son id
    Structure GetById(Long id);


}
