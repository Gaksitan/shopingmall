package com.green.viewServer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Bean // 메서드에 @Bean 을 붙이면 메서드의 반환자료가 Bean 으로 등록됨 (하나만 존재)
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		http.csrf(auth -> auth.disable());
		
		http.httpBasic(auth -> auth.disable());
		
		http.authorizeHttpRequests(auth -> auth.anyRequest().permitAll());
		
		return http.build();
	}
	
	
	
	
}
