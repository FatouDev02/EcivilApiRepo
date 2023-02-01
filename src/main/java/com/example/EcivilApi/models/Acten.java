package com.example.EcivilApi.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Acten {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String nom;
    private String prenom;
    private String nompere;
    private String nommere;
    private String profpere;
    private String profmere;
    private String numvolet;
    private String datedenaissance;
    private String lieudenaissance;
    private String genre;
    private String etatdemande;

@JsonIgnore
    @ManyToOne
    private Demande mademande;

    @ManyToOne
    private Structure mastructure;

    @ManyToOne
    private Utilisateurs user;


/*
    @ManyToOne
    private Typestructure typestructure;*/


}
