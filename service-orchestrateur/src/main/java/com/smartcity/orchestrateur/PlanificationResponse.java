package com.smartcity.orchestrateur;

import java.util.List;

public record PlanificationResponse(
		AirInfo qualiteAir,
	    List<Horaire> transports,
	    List<Evenement> evenements,
	    List<String> alertes,
	    List<String> recommendations) 
{

 public record AirInfo(String zone, int aqi) {}
 public record Horaire(String arret, String passage) {}
 public record Evenement(String id, String titre, String date) {}
}