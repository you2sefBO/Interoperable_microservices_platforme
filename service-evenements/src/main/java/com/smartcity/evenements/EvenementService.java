package com.smartcity.evenements;


import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EvenementService {

    private final List<Evenement> evenements = List.of(
            new Evenement("e1", "Concert de la ville", "2025-12-01", "Place centrale"),
            new Evenement("e2", "Marathon", "2025-11-20", "Grand parc")
    );

    private final List<Incident> incidents = List.of(
            new Incident("i1", "Nid de poule", 3, "Rue de la République"),
            new Incident("i2", "Fuite d'eau", 5, "Avenue de la Gare")
    );

    public List<Evenement> findAllEvenements() {
        return evenements;
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
