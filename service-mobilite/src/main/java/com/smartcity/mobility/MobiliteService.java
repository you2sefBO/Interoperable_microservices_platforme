package com.smartcity.mobility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MobiliteService {

    @Autowired
    private HoraireRepository horaireRepository;

    public List<Horaire> getHorairesForLigne(String ligneId) {
        List<HoraireEntity> entities = horaireRepository.findByLigneId(ligneId);

        return entities.stream()
                .map(entity -> new Horaire(entity.getArret(), entity.getHeurePassage()))
                .collect(Collectors.toList());
    }

    public Trafic getTraficInfo() {
        return new Trafic("Fluide (Donnée temps réel simulée)", List.of());
    }
}