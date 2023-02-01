package com.example.EcivilApi.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class ActeD {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String nom;
    private String prenom;
    private String daten;
    private String dated;
    private String profession;
    private String numvolet;
    private String lieunaiss;
    private String lieudeces;
    @JsonIgnore
    @ManyToOne
    private Demande mademande;

    @ManyToOne
    private Structure mastructure;

    @ManyToOne
    private Utilisateurs user;




}
