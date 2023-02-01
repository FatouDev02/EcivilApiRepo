package com.example.EcivilApi.payload.response;

import java.util.List;

public class UserInfoResponse {
    private Long id;
    private String username;
    private String email;
    private String nom;
    private String prenom;
    private List<String> roles;

    public UserInfoResponse(Long id, String username, String email,String nom,String prenom, List<String> roles) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.nom=nom;
        this.prenom=prenom;
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<String> getRoles() {
        return roles;
    }
}
