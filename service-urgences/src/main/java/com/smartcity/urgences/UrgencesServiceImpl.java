package com.smartcity.urgences;

import io.grpc.stub.StreamObserver;
import org.springframework.stereotype.Service;

import com.smartcity.urgences.grpc.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Service
public class UrgencesServiceImpl extends UrgencesServiceGrpc.UrgencesServiceImplBase {

    @Override
    public void envoyerAlerte(AlerteRequest request, StreamObserver<AlerteResponse> responseObserver) {
        System.out.println("Alerte reçue : " + request.getType() + 
                           " à " + request.getLocation() +
                           " | Gravité = " + request.getNiveauGravite());

        String id = UUID.randomUUID().toString();
        AlerteResponse response = AlerteResponse.newBuilder()
                .setIdAlerte(id)
                .setStatus("Alerte enregistrée à " + 
                    LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
    
    @Override
    public void getAlertesParZone(ZoneRequest request, StreamObserver<ListeAlertes> responseObserver) {
        String zone = request.getZone();
        ListeAlertes.Builder responseBuilder = ListeAlertes.newBuilder();

        if ("Centre".equalsIgnoreCase(zone)) {
            responseBuilder.addAlertes(Alerte.newBuilder()
                    .setId(UUID.randomUUID().toString())
                    .setNiveau("WARN")
                    .setDescription("Manifestation en cours place centrale")
                    .setZone("Centre")
                    .build());
        } else if ("Nord".equalsIgnoreCase(zone)) {
            responseBuilder.addAlertes(Alerte.newBuilder()
                    .setId(UUID.randomUUID().toString())
                    .setNiveau("CRITICAL")
                    .setDescription("Fuite de gaz secteur industriel")
                    .setZone("Nord")
                    .build());
        } else {
            responseBuilder.addAlertes(Alerte.newBuilder()
                    .setId(UUID.randomUUID().toString())
                    .setNiveau("INFO")
                    .setDescription("Aucune alerte majeure")
                    .setZone(zone)
                    .build());
        }

        responseObserver.onNext(responseBuilder.build());
        responseObserver.onCompleted();
    }
}
