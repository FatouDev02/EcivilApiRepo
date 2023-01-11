package com.example.EcivilApi.models;


import jakarta.persistence.*;
import lombok.Data;

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
@ManyToOne
    private Demande mademande;


}
