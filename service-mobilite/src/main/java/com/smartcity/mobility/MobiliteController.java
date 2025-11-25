package com.smartcity.mobility;


//import com.smartcity.mobility.Horaire;
//import com.smartcity.mobility.Trafic;
//import com.smartcity.mobility.MobiliteService;
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

    
    @GetMapping("/lignes/{id}/horaires")
    public ResponseEntity<List<Horaire>> getHoraires(@PathVariable String id) {
        
        Optional<List<Horaire>> horaires = mobiliteService.getHorairesForLigne(id);

        return horaires
                .map(ResponseEntity::ok) 
                .orElse(ResponseEntity.notFound().build());
    }
}