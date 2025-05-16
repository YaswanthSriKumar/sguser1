package com.example.sgGatway.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.sgGatway.service.LoginService;

import reactor.core.publisher.Mono;

@RestController
@CrossOrigin("http://localhost:3000/")
public class Controller {

	@Autowired
	LoginService loginService;
	@GetMapping("/getmsg")
	public String msg()
	{
		return "working";
	}
	
	@PostMapping("/login/{username}/{Password}")
	//Mono<ResponseEntity<String>>
	public Mono<ResponseEntity<String>> login(@PathVariable String username,@PathVariable String Password)
	{
		System.out.println("username"+username);
		System.out.println("password"+Password);
		System.out.println("got into controller");
		return loginService.login(username, Password);
//		return "it is working";
	}
	@GetMapping("/validateToken")
	public boolean String()
	{
		return true;
	}
	
}
