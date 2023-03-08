package com.example.EcivilApi.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

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
    private Date datemariage;

    private String etatdemande;
    @ManyToOne
    private Demande mademande;
  /*  @JsonIgnore
    @ManyToOne
    private Demande mademande;

    @ManyToOne
    private Structure mastructure;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Utilisateurs user;*/



    @ManyToOne
    private Structure mastructure;

    @ManyToOne
    private Utilisateurs user;


}
