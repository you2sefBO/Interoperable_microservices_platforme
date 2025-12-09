package com.smartcity.mobility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/mobilite")
public class MobiliteController {

    @Autowired
    private MobiliteService mobiliteService; 

    @GetMapping("/lignes/{ligneId}/horaires")
    public List<Horaire> getHoraires(@PathVariable String ligneId) {
        return mobiliteService.getHorairesForLigne(ligneId);
    }
    
    @GetMapping("/trafic")
    public Trafic getTrafic() {
        return mobiliteService.getTraficInfo();
    }
}