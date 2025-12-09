package com.smartcity.orchestrateur;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.web.client.RestTemplate;
import org.springframework.ws.client.core.WebServiceTemplate;

// IMPORTANT : Imports des classes générées
import com.smartcity.orchestrateur.soap.client.GetAQIRequest;
import com.smartcity.orchestrateur.soap.client.GetAQIResponse;
import com.smartcity.orchestrateur.soap.client.ObjectFactory;

@Configuration
public class AppConfig {

    // Injection de l'URL depuis application.properties
    // (Permet à Docker de changer l'URL via une variable d'environnement)
    @Value("${app.url.qualiteair}")
    private String urlQualiteAir;
    
    @Value("${app.url.evenements}") 
    private String urlEvenements;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        // Configuration explicite pour éviter les erreurs JAXB
        marshaller.setClassesToBeBound(
                GetAQIRequest.class, 
                GetAQIResponse.class, 
                ObjectFactory.class
        );
        return marshaller;
    }

    @Bean
    public WebServiceTemplate webServiceTemplate(Jaxb2Marshaller marshaller) {
        WebServiceTemplate template = new WebServiceTemplate();
        template.setMarshaller(marshaller);
        template.setUnmarshaller(marshaller);
        // Utilisation de l'URL injectée (dynamique)
        template.setDefaultUri(urlQualiteAir);
        return template;
    }
}