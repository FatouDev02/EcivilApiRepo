package com.example.EcivilApi.models;


import lombok.Data;

import javax.persistence.*;

@Entity
@Data

public class Actem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String nomh;
    private String nomf;
    private String proh;
    private String prof;
    private String temoinh;
    private String temoinf;
    private String datenh;
    private String datenf;
    private String datemariage;
    @ManyToOne
    private Demande mademande;




}
