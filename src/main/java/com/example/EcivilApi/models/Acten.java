package com.example.EcivilApi.models;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

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
    private Date datedenaissance;
    private String lieudenaissance;
    private String genre;
    private String etatdemande;
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
