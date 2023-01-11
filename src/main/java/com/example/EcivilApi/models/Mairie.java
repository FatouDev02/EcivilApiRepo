package com.example.EcivilApi.models;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Mairie  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String nom;
    private String longitude;
    private String latitude;
    //Plusieurs Mairies pour une structure
    @ManyToOne
    @JoinColumn(name = "typestructure")
    private Structure typestructure;




}
