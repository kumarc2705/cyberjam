package com.cyberjam.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true); // Important if you need cookies or authorization headers
        config.setAllowedOrigins(Arrays.asList("http://localhost:8080", "null")); // Replace with your frontend URL
        config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS")); // Allowed HTTP methods
        config.setAllowedHeaders(Arrays.asList("*")); // Allowed HTTP headers
        source.registerCorsConfiguration("/**", config); // Apply to all paths
        return new CorsFilter(source);
    }
}