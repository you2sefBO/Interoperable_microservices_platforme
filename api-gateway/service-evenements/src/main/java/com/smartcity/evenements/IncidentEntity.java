package com.smartcity.evenements;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "incidents")
public class IncidentEntity {

    @Id
    private String id;
    private String description;
    private int gravite;
    private String localisation;

    public IncidentEntity() {}

    public IncidentEntity(String id, String description, int gravite, String localisation) {
        this.id = id;
        this.description = description;
        this.gravite = gravite;
        this.localisation = localisation;
    }

    public String getId() { return id; }
    public String getDescription() { return description; }
    public int getGravite() { return gravite; }
    public String getLocalisation() { return localisation; }
}