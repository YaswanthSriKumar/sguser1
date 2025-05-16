package com.example.sgGatway.filter;

import java.util.Collections;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

public class JwtAuthenticationFilter implements WebFilter {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        // Bypass OPTIONS requests (preflight)
    	
        if (exchange.getRequest().getMethod() == HttpMethod.OPTIONS) {
            return chain.filter(exchange);
        }
        
        String path = exchange.getRequest().getURI().getPath();
        System.out.println("got int filters fro path: "+path);
        // If the request is not for a secure endpoint, allow it to pass through.
        if (!path.startsWith("/SGSERVICES/getServicesss") && !path.startsWith("/validateToken")) {
            return chain.filter(exchange);
        }
        
        // Log incoming headers for debugging
        exchange.getRequest().getHeaders().forEach((key, value) -> System.out.println(key + " : " + value));
        String authHeader = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
        System.out.println("got the token---" + authHeader);
        
        // Check if the header is present and formatted properly
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            // Remove "Bearer " prefix
            String token = authHeader.substring(7);
            WebClient webClient = WebClient.create("http://localhost:8088/TOKENSERVICE"); // Replace with actual URL
              
            // Call external JWT validation endpoint using WebClient
            return webClient.get()
                    .uri("/validatetoken")
                    .headers(httpHeaders -> httpHeaders.set("Authorization", authHeader))
                    .retrieve() 
                    .bodyToMono(Boolean.class) // Expected Boolean (true if valid, false otherwise)
                    .flatMap(isValid -> {
                        if (Boolean.TRUE.equals(isValid)) {
                            // Create a basic authentication object (customize as needed)
                            Authentication auth =
                                    new UsernamePasswordAuthenticationToken("user", null, Collections.emptyList());
                            return chain.filter(exchange)
                                    .contextWrite(ReactiveSecurityContextHolder.withAuthentication(auth));
                        } else {
                            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                            return exchange.getResponse().setComplete();
                        }
                    })
                    // On error during validation, return unauthorized
                    .onErrorResume(throwable -> {
                        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                        return exchange.getResponse().setComplete();
                    });
        }
        // If no valid Authorization header is found, reject the request
        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        return exchange.getResponse().setComplete();
    }
}
