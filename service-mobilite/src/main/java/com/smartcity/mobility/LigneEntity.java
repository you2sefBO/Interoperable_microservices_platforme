package com.smartcity.mobility;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "lignes")
public class LigneEntity {
    @Id
    private String id;
    private String nom;
    private String description;

    public LigneEntity() {}
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    
    public String getescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
}