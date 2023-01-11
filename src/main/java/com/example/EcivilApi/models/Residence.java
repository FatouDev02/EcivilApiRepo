package com.example.EcivilApi.models;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Residence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String nom;
    private String prenom;
    private String photoacten;
    private String lieuderesidence;
    @ManyToOne
    private Demande mademande;
}
