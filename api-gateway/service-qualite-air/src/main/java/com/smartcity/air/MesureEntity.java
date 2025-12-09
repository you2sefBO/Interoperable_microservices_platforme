package com.smartcity.air;

import jakarta.persistence.*;

@Entity
@Table(name = "mesures")
public class MesureEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String zone;
    private int aqi;

    public MesureEntity() {}

    public String getZone() { return zone; }
    public int getAqi() { return aqi; }
}