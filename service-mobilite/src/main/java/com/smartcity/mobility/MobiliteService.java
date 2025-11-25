package com.smartcity.mobility;

//import com.smartcity.mobility.Horaire;
//import com.smartcity.mobility.Trafic;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class MobiliteService {

    private static final Map<String, List<Horaire>> horairesData = Map.of(
            "ligne_1", List.of(
                    new Horaire("Centre Ville", "10:00"),
                    new Horaire("Gare", "10:15")
            ),
            "ligne_2", List.of(
                    new Horaire("Hôpital", "10:05"),
                    new Horaire("Musée", "10:20")
            )
    );

    private static final Trafic traficData = new Trafic("Fluide", List.of());

    public Trafic getTraficInfo() {
        return traficData;
    }

    public Optional<List<Horaire>> getHorairesForLigne(String ligneId) {
        return Optional.ofNullable(horairesData.get(ligneId));
    }
}