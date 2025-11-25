package com.smartcity.urgences;

import com.smartcity.urgences.grpc.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class UrgencesClient implements CommandLineRunner {

    @Override
    public void run(String... args) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50051)
                .usePlaintext()
                .build();

        UrgencesServiceGrpc.UrgencesServiceBlockingStub stub =
                UrgencesServiceGrpc.newBlockingStub(channel);

        AlerteRequest request = AlerteRequest.newBuilder()
                .setType("Incendie")
                .setLocation("Centre-ville Tunis")
                .setNiveauGravite(4)
                .build();

        AlerteResponse response = stub.envoyerAlerte(request);
        System.out.println("Réponse du serveur : " + response.getStatus());
        System.out.println("ID alerte : " + response.getIdAlerte());

        channel.shutdown();
    }
}

