package com.smartcity.orchestrateur;

import com.smartcity.orchestrateur.soap.client.GetAQIRequest;
import com.smartcity.orchestrateur.soap.client.GetAQIResponse;
import com.smartcity.orchestrateur.PlanificationResponse.AirInfo;
import com.smartcity.orchestrateur.PlanificationResponse.Horaire;
import com.smartcity.orchestrateur.PlanificationResponse.Evenement;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import com.smartcity.urgences.grpc.UrgencesServiceGrpc;
import com.smartcity.urgences.grpc.ZoneRequest;
import com.smartcity.urgences.grpc.ListeAlertes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.ws.client.core.WebServiceTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class OrchestrationService {

    private static final Logger logger = LoggerFactory.getLogger(OrchestrationService.class);

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private WebServiceTemplate soapTemplate;

    @Value("${app.url.mobilite}")
    private String urlMobilite;
    
    @Value("${app.url.evenements}")
    private String urlEvenements;
    
    @Value("${app.url.qualiteair}") 
    private String urlQualiteAir;

    @Value("${app.url.urgences.host}")
    private String grpcHost;

    @Value("${app.url.urgences.port}")
    private int grpcPort;

    public PlanificationResponse planifier(PlanificationRequest request) {
        List<String> recommendations = new ArrayList<>();
        
        AirInfo airInfo = callAirService(request.zone());
        if (airInfo != null && airInfo.aqi() > 50) {
            recommendations.add("Qualité de l'air moyenne. Préférez les transports fermés.");
        }
        
        List<Horaire> horaires = callMobiliteService(request.ligneId());
        
        List<Evenement> evenements = callEvenementsService();

        List<String> alertes = callUrgencesService(request.zone());
        
        return new PlanificationResponse(airInfo, horaires, evenements, alertes, recommendations);
    }

    private List<String> callUrgencesService(String zone) {
        ManagedChannel channel = null;
        try {
            channel = ManagedChannelBuilder.forAddress(grpcHost, grpcPort)
                    .usePlaintext()
                    .build();

            UrgencesServiceGrpc.UrgencesServiceBlockingStub stub = UrgencesServiceGrpc.newBlockingStub(channel);

            ZoneRequest grpcRequest = ZoneRequest.newBuilder().setZone(zone).build();
            ListeAlertes response = stub.getAlertesParZone(grpcRequest);

            return response.getAlertesList().stream()
                    .map(alerte -> "[URGENCE " + alerte.getNiveau() + "] " + alerte.getDescription())
                    .collect(Collectors.toList());

        } catch (Exception e) {
            logger.error("Erreur gRPC Urgences", e);
            return List.of(); 
        } finally {
            if (channel != null) {
                channel.shutdown();
            }
        }
    }


    private AirInfo callAirService(String zone) {
        try {
            GetAQIRequest request = new GetAQIRequest();
            request.setZone(zone);
            GetAQIResponse response = (GetAQIResponse) soapTemplate.marshalSendAndReceive(request);
            return new AirInfo(zone, response.getAqi());
        } catch (Exception e) {
            logger.error("Erreur SOAP pour la zone {}", zone, e);
            return new AirInfo(zone, -1);
        }
    }

    private List<Horaire> callMobiliteService(String ligneId) {
        try {
            String url = urlMobilite + "/api/mobilite/lignes/" + ligneId + "/horaires";
            Horaire[] response = restTemplate.getForObject(url, Horaire[].class);
            return (response != null) ? Arrays.asList(response) : List.of();
        } catch (Exception e) {
            logger.error("Erreur REST mobilite pour la ligne {}", ligneId, e);
            return List.of();
        }
    }

    @SuppressWarnings("unchecked")
    private List<Evenement> callEvenementsService() {
        try {
            String query = "{\"query\": \"{ evenements { id titre date } }\"}";
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> entity = new HttpEntity<>(query, headers);

            Map<String, Object> response = restTemplate.postForObject(urlEvenements, entity, Map.class);

            if (response != null && response.containsKey("data")) {
                Map<String, Object> data = (Map<String, Object>) response.get("data");
                if (data != null && data.containsKey("evenements")) {
                    List<Map<String, String>> list = (List<Map<String, String>>) data.get("evenements");
                    return list.stream()
                        .map(m -> new Evenement(m.get("id"), m.get("titre"), m.get("date")))
                        .collect(Collectors.toList());
                }
            }
            return List.of();
        } catch (Exception e) {
            logger.error("Erreur GraphQL Événements", e);
            return List.of();
        }
    }
}