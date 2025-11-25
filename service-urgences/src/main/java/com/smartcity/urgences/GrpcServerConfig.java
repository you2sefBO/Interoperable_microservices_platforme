package com.smartcity.urgences;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Component;

@Component
public class GrpcServerConfig {

    private Server server;

    @PostConstruct
    public void startServer() throws Exception {
        int port = 50051;
        server = ServerBuilder.forPort(port)
                .addService(new UrgencesServiceImpl())
                .build()
                .start();
        System.out.println("Serveur gRPC Urgences démarré sur le port " + port);
    }

    @PreDestroy
    public void stopServer() {
        if (server != null) {
            server.shutdown();
            System.out.println("Serveur gRPC Urgences arrêté proprement.");
        }
    }
}
