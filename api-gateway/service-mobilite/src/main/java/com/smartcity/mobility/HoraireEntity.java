package com.smartcity.mobility;

import jakarta.persistence.*;

@Entity
@Table(name = "horaires")
public class HoraireEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    private String arret;
    
    @Column(name = "heure_passage")
    private String heurePassage;
    
    @Column(name = "ligne_id")
    private String ligneId;

    public HoraireEntity() {}
    
    public String getArret() { return arret; }
    public String getHeurePassage() { return heurePassage; }
}