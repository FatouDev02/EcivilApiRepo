package com.example.EcivilApi.models;


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

    @ManyToOne
    private Demande mademande;

/*
    @ManyToOne
    private Typestructure typestructure;*/


}
