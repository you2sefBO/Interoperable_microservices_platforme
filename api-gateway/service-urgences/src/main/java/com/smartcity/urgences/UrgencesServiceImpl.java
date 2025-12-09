package com.smartcity.urgences;

import com.smartcity.urgences.grpc.*;
import net.devh.boot.grpc.server.service.GrpcService; 
import io.grpc.stub.StreamObserver;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@GrpcService 
public class UrgencesServiceImpl extends UrgencesServiceGrpc.UrgencesServiceImplBase {

    @Autowired
    private AlerteRepository repository;

    @Override
    public void getAlertesParZone(ZoneRequest request, StreamObserver<ListeAlertes> responseObserver) {
        String zoneDemandee = request.getZone();
        if (zoneDemandee == null) zoneDemandee = "";

        List<AlerteEntity> entities = repository.findByZoneContainingIgnoreCase(zoneDemandee);

        ListeAlertes.Builder responseBuilder = ListeAlertes.newBuilder();

        for (AlerteEntity entity : entities) {
            Alerte alerteProto = Alerte.newBuilder()
                    .setId(entity.getId())
                    .setNiveau(entity.getNiveau())
                    .setDescription(entity.getDescription())
                    .setZone(entity.getZone())
                    .build();
            
            responseBuilder.addAlertes(alerteProto);
        }

        responseObserver.onNext(responseBuilder.build());
        responseObserver.onCompleted();
    }
}