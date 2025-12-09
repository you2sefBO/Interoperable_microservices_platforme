package com.smartcity.air;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QualiteAirService {

    @Autowired
    private MesureRepository repository;

    public int getAqi(String zone) {
        return repository.findTopByZoneContainingIgnoreCaseOrderByIdDesc(zone)
                .map(MesureEntity::getAqi)
                .orElse(50);
    }
}