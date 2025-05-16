package com.example.sgGatway.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.context.NoOpServerSecurityContextRepository;

import com.example.sgGatway.filter.JwtAuthenticationFilter;



@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

	
	@Bean
	public SecurityWebFilterChain securityFilterChain(ServerHttpSecurity http) throws Exception
	{
		
		http.csrf(customizer-> customizer.disable())
		.cors(ServerHttpSecurity.CorsSpec::disable);
		 // Optionally disable the default security context repository if you handle the context manually.
        http.securityContextRepository(NoOpServerSecurityContextRepository.getInstance());
        
        http.authorizeExchange(exchange -> exchange
                .pathMatchers("/dashbords/**","/try").authenticated()
                .anyExchange().permitAll()
        );
        http.addFilterBefore(jwtAuthenticationFilter(), SecurityWebFiltersOrder.AUTHENTICATION);
		
		return http.build();
	}
	 @Bean
	    public JwtAuthenticationFilter jwtAuthenticationFilter() {
	        return new JwtAuthenticationFilter();
	    }
	 
}
