package com.example.EcivilApi.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "structure")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Structure {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String nom;
    private String longitude;
    private String latitude;

    //plsrs struct appartiennent a un type de struct
    @ManyToOne
    private Typestructure typestructure;
    /*@JsonIgnore
    @OneToMany
    List<Mairie> mairieList = new ArrayList<>();
    @OneToMany
    List<Tribunal> tribunalList = new ArrayList<>();
    @OneToMany
    List<Commissariats> commissariatsList = new ArrayList<>();*/

    public Structure(Long id, String nom, String longitude,String latitude) {
        this.id = id;
        this.nom = nom;
        this.longitude = longitude;
        this.latitude = latitude;


    }


}
