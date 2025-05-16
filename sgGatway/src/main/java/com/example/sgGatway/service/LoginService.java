package com.example.sgGatway.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

@Service
public class LoginService {

	
	public Mono<ResponseEntity<String>> login( String username,String password)
	{
		if(username.equals("leela123") && password.equals("@leela123"))
		{
			System.out.println("valied");
			 WebClient webClient = WebClient.create("http://localhost:8088/TOKENSERVICE"); // Replace with actual URL
			  
		        // Call the POST endpoint using WebClient
		        return webClient.post()
		                .uri("/getToken/"+username+"/ADMIN")  // The path to the POST endpoint on the external service
		                .retrieve()                            // Sends the request and prepares for the response
		                .toEntity(String.class);  
		}
		return Mono.just(ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body("Invalid user"));
		
	}
}
