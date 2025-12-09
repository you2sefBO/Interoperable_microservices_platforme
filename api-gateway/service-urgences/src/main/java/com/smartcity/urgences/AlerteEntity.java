package com.smartcity.urgences;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "alertes")
public class AlerteEntity {
    @Id
    private String id;
    private String zone;
    private String niveau;
    private String description;

    public AlerteEntity() {}
    
    public String getId() { return id; }
    public String getZone() { return zone; }
    public String getNiveau() { return niveau; }
    public String getDescription() { return description; }
}