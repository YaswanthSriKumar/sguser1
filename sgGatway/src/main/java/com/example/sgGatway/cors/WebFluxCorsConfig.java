package com.example.sgGatway.cors;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.cors.reactive.CorsWebFilter;

@Configuration
public class WebFluxCorsConfig {

	 @Bean
	    public CorsWebFilter corsWebFilter() {
	        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	        CorsConfiguration config = new CorsConfiguration();

	        config.addAllowedOrigin("http://localhost:3000"); // Allow frontend
	        config.addAllowedHeader("*"); // Allow all headers, including Authorization
	        config.addAllowedMethod("*"); // Allow GET, POST, OPTIONS, etc.
	        config.setAllowCredentials(true); // Allow authentication headers
	        config.addExposedHeader("Authorization"); // Ensure Authorization is accessible
	        config.addAllowedMethod("OPTIONS"); // Explicitly allow preflight requests
	        
	        source.registerCorsConfiguration("/**", config);
	        return new CorsWebFilter(source);
	    }
}
