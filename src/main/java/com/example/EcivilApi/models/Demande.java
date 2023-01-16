package com.example.EcivilApi.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;


@Entity
@Data
public class Demande {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String type;
    private Date datededeclaration= new Date();

    @JsonIgnore
    @OneToOne(mappedBy = "demande", cascade = CascadeType.ALL)
    private Notification notification;

    @OneToOne
    private Acten acten;
    @OneToOne
    private Actem actem;
    @OneToOne
    private ActeD acteD;
    @OneToOne
    private CasierJudiciaire casierJudiciaire;
    @OneToOne
    private Residence residence;
    @OneToOne
    private Nationnalite nationnalite;
}
