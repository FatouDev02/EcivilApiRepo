package com.example.EcivilApi.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

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
    @JsonIgnore
    @ManyToOne
    private Demande mademande;

    @ManyToOne
    private Structure mastructure;

    @ManyToOne
    private Utilisateurs user;


}
