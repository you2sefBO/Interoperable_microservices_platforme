package com.smartcity.mobility;

import java.util.List;

public class Trafic {
    private String status;
    private List<String> perturbations;

    public Trafic(String status, List<String> perturbations) {
        this.status = status;
        this.perturbations = perturbations;
    }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public List<String> getPerturbations() { return perturbations; }
    public void setPerturbations(List<String> perturbations) { this.perturbations = perturbations; }
}