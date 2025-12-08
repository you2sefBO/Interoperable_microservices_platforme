package com.smartcity.evenements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller; 

import java.util.List;

@Controller
public class EvenementController {

    @Autowired
    private EvenementService service;

    @QueryMapping
    public List<Evenement> evenements() {
        return service.findAllEvenements();
    }

    @QueryMapping
    public List<Incident> incidents() {
        return service.findAllIncidents();
    }

    @QueryMapping
    public Evenement evenement(@Argument String id) {
        return service.findEvenementById(id).orElse(null);
    }
}