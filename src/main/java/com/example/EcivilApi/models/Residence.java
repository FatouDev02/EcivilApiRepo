package com.example.EcivilApi.models;
import lombok.Data;

import javax.persistence.*;

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
