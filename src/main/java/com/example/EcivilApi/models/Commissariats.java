package com.example.EcivilApi.models;
import lombok.Data;

import javax.persistence.*;


@Entity
@Data
public class Commissariats {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String nom;
    private String longitude;
    private String latitude;
    //Plusieurs commissariats pour une structure
    @ManyToOne
    @JoinColumn(name = "typestructure")
    private Structure typestructure;



}
