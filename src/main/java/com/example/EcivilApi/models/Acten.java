package com.example.EcivilApi.models;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Acten {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String nomh;
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
}
