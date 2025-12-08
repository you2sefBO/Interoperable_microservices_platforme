package com.smartcity.evenements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EvenementService {

    @Autowired
    private EvenementRepository evenementRepository;

    @Autowired
    private IncidentRepository incidentRepository; 

    public List<Evenement> findAllEvenements() {
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        return evenementRepository.findAll().stream()
                .filter(e -> {
                    try {
                        LocalDate eventDate = LocalDate.parse(e.getDate(), formatter);
                        return eventDate.isAfter(today) || eventDate.isEqual(today);
                    } catch (Exception ex) {
                        return false;
                    }
                })
                .map(e -> new Evenement(e.getId(), e.getTitre(), e.getDate(), e.getLieu()))
                .collect(Collectors.toList());
    }

    public List<Incident> findAllIncidents() {
        return incidentRepository.findAll().stream()
                .map(entity -> new Incident(
                        entity.getId(), 
                        entity.getDescription(), 
                        entity.getGravite(), 
                        entity.getLocalisation()
                ))
                .collect(Collectors.toList());
    }

    public Optional<Evenement> findEvenementById(String id) {
        return evenementRepository.findById(id)
                .map(e -> new Evenement(e.getId(), e.getTitre(), e.getDate(), e.getLieu()));
    }
}