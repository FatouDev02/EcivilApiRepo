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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    private  String description;
    private  String titre;
    private Date datenotif;

@OneToOne
    @JoinColumn(name = "iddemande", referencedColumnName = "id")
    private Demande demande;
     /*   @ManyToOne
    @JoinColumn(name = "acten_id")
    private Acten acten;*/


}
