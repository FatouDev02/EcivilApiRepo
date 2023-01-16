package com.example.EcivilApi.models;
import lombok.Data;

import javax.persistence.Entity;

@Entity
@Data
public class Agents extends Utilisateurs {
    private String matricule;

}
