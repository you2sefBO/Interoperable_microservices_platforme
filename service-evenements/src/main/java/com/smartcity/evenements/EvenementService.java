package com.smartcity.evenements;


import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EvenementService {

    private final List<Evenement> evenements = new ArrayList<>(List.of(
            new Evenement("e0", "Fête de la musique 2024", "2024-06-21", "Toute la ville"),
            new Evenement("e00", "Brocante d'Automne", "2024-10-15", "Place du Marché"),

            new Evenement("e1", "Concert de Jazz", "2025-12-01", "Place centrale"),
            new Evenement("e2", "Marathon International", "2026-03-20", "Grand parc"),
            new Evenement("e3", "Salon de l'Innovation", "2025-11-25", "Palais des Congrès"),
            new Evenement("e4", "Marché de Noël", "2025-12-20", "Parvis de la Cathédrale"),
            new Evenement("e5", "Cinéma en plein air", "2026-06-15", "Plage Sud")
    ));

    private final List<Incident> incidents = List.of(
            new Incident("i1", "Nid de poule", 3, "Rue de la République"),
            new Incident("i2", "Fuite d'eau", 5, "Avenue de la Gare")
    );

    public List<Evenement> findAllEvenements() {
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        return evenements.stream()
                .filter(e -> {
                    try {
                        LocalDate eventDate = LocalDate.parse(e.date(), formatter);
                        return eventDate.isAfter(today) || eventDate.isEqual(today);
                    } catch (Exception ex) {
                        return false;
                    }
                })
                .collect(Collectors.toList());
    }

    public List<Incident> findAllIncidents() {
        return incidents;
    }

    public Optional<Evenement> findEvenementById(String id) {
        return evenements.stream()
                .filter(e -> e.id().equals(id))
                .findFirst();
    }
}
