package com.example.EcivilApi.models;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Tribunal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String nom;
    private String longitude;
    private String latitude;
    //Plusieurs Tribunaux pour une structure
    @ManyToOne
    @JoinColumn(name = "typestructure")
    private Structure typestructure;



}
