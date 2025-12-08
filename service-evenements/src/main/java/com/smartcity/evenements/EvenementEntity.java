package com.smartcity.evenements;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;

@Entity
@Table(name = "evenements")
public class EvenementEntity {
    @Id
    private String id;
    private String titre;
    
    @Column(name = "date_event")
    private String date; 
    
    private String lieu;

    public EvenementEntity() {}

    public EvenementEntity(String id, String titre, String date, String lieu) {
        this.id = id; this.titre = titre; this.date = date; this.lieu = lieu;
    }

    public String getId() { return id; }
    public String getTitre() { return titre; }
    public String getDate() { return date; }
    public String getLieu() { return lieu; }
}