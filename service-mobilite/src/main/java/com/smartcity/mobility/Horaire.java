package com.smartcity.mobility;


public class Horaire {
    private String arret;
    private String passage;

    public Horaire() {
    }

    public Horaire(String arret, String passage) {
        this.arret = arret;
        this.passage = passage;
    }

    public String getArret() { return arret; }
    public void setArret(String arret) { this.arret = arret; }
    public String getPassage() { return passage; }
    public void setPassage(String passage) { this.passage = passage; }
}