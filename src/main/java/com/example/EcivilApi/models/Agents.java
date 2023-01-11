package com.example.EcivilApi.models;


import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class Agents extends Utilisateurs {
    private String matricule;

}
