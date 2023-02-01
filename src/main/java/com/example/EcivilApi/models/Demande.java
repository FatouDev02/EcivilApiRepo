package com.example.EcivilApi.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Demande {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String type;
    private String name;
    private Date datededeclaration= new Date();


    @JsonIgnore
    @OneToOne(mappedBy = "demande", cascade = CascadeType.ALL)
    private Notification notification;

    @ManyToOne
    private Typestructure typestructure;
    @ManyToOne
    private Structure structure;

    public Demande(Long id, String type, String name, Date datededeclaration) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.datededeclaration = datededeclaration;
    }
}
