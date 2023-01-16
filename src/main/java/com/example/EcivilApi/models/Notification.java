package com.example.EcivilApi.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;



@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity

public class Notification {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Lob
    private  String description;
    private  String titre;
    private Date datenotif;

    @OneToOne
    private Demande demande;
}
