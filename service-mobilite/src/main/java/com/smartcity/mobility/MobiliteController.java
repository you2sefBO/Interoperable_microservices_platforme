package com.smartcity.mobility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/mobilite") 
public class MobiliteController {

    @Autowired
    private MobiliteService mobiliteService; 

    
    @GetMapping("/trafic")
    public ResponseEntity<Trafic> getTrafic() {
        return ResponseEntity.ok(mobiliteService.getTraficInfo());
    }

    
    @GetMapping("/lignes/{ligneId}/horaires")
    public List<Horaire> getHoraires(@PathVariable String ligneId) {
        switch (ligneId) {
            case "ligne_1": 
                return Arrays.asList(
                    new Horaire("Place Centrale", "10:00"),
                    new Horaire("Bibliothèque", "10:10"),
                    new Horaire("Gare Ferroviaire", "10:20")
                );
            case "ligne_2":
                return Arrays.asList(
                    new Horaire("Aéroport Terminal 1", "11:00"),
                    new Horaire("Zone Hôtelière", "11:15"),
                    new Horaire("Grand Stade", "11:45")
                );
            case "ligne_3": 
                return Arrays.asList(
                    new Horaire("Université Sciences", "08:30"),
                    new Horaire("Cité U", "08:40"),
                    new Horaire("Plage Sud", "09:15")
                );
            default:
                return Arrays.asList(
                    new Horaire("Arrêt Inconnu", "--:--")
                );
        }
    }
}
