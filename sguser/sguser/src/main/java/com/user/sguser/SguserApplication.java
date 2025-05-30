package com.user.sguser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class SguserApplication {

	public static void main(String[] args) {
		SpringApplication.run(SguserApplication.class, args);
	}

}
