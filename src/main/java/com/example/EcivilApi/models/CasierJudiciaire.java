package com.example.EcivilApi.models;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class CasierJudiciaire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String Nom;
    private String prenom;
    private String photoacten;
    private String lieudenaissance;
    @ManyToOne
    private Demande mademande;
}
