package com.footballleague.footballleague.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**") // Adjust the mapping pattern as needed
                .allowedOrigins("http://localhost:3000") // Allow requests from this origin
                .allowedMethods("GET", "POST", "PUT", "DELETE") // Allow these HTTP methods
                .allowedHeaders("*")
                .allowCredentials(true) // Allow credentials (e.g., cookies, authorization headers)
                .exposedHeaders("Authorization") // Expose additional headers if needed
                .maxAge(3600); // Cache preflight requests for 1 hour
    }
}
