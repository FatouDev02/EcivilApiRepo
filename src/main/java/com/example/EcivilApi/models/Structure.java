package com.example.EcivilApi.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "structure")
@Data
public class Structure {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String type;
    private String description;

    @JsonIgnore
    private String longitude;
    @JsonIgnore
    private String latitude;

    @JsonIgnore

    @OneToMany
    List<Mairie> mairieList = new ArrayList<>();
    @OneToMany
    List<Tribunal> tribunalList = new ArrayList<>();
    @OneToMany
    List<Commissariats> commissariatsList = new ArrayList<>();


}
