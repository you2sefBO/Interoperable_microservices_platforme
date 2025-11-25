package com.smartcity.gateway;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;


@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
            // Route 1 : Mobilité
            .route("route-mobilite", r -> r.path("/api/mobilite/**")
                .uri("http://service-mobilite:5001"))
            
            // Route 2 : Orchestrateur
            .route("route-orchestrateur", r -> r.path("/api/planifier-trajet/**")
                .uri("http://service-orchestrateur:5005"))

            // Route 3 : Événements (GraphQL) - On met les deux variantes pour être sûr
            .route("route-evenements", r -> r.path("/graphql", "/graphql/**", "/graphiql")
                .uri("http://service-evenements:5004"))

            // Route 4 : Qualité Air (SOAP)
            .route("route-qualiteair", r -> r.path("/ws/**")
                .uri("http://service-qualite-air:5002"))
            
            .build();
    }
    
    @Bean
    public CorsWebFilter corsWebFilter() {
        CorsConfiguration corsConfig = new CorsConfiguration();
        corsConfig.setAllowedOrigins(Arrays.asList("*")); // Autorise tout le monde
        corsConfig.setMaxAge(8000L);
        corsConfig.addAllowedMethod("*"); // GET, POST, PUT, etc.
        corsConfig.addAllowedHeader("*");

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfig);

        return new CorsWebFilter(source);
    }
}