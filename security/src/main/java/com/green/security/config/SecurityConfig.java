package com.green.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Bean // 메서드에 붙게되고, 메서드의 반환타입이 클래스인거에 붙는다.
	public BCryptPasswordEncoder bCryptPasswordEncoder() { // 원래는 Service 에서 사용
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		http.authorizeHttpRequests((auth) -> auth // -> 람다 표현식
				.requestMatchers("/", "/registForm", "/registProc").permitAll() // 제한해제(permitAll) 전부허용
				.requestMatchers("/members/**").hasAnyRole("ADMIN", "MEMBER") // ROLE_ 는 자동으로 앞에 붙여주게 메서드가 처리되있음.
				.requestMatchers("/admin/**").hasRole("ADMIN")
				.anyRequest().authenticated() // 위에서 설정한 애들빼고 나머지는 인증을 거쳐야한다.(anyRequest)
				
				);
		
		http.formLogin((auth) -> auth
				.loginPage("/login") // 로그인 폼 지정 - 쓰지 않으면 Spring Security가 제공하는 로그인 폼 사용
				.loginProcessingUrl("/loginProc") // 로그인 폼 지정 후 폼 파라미터 보내는 경로지정 - 컨트롤러에 직접 만들지 않는다.(Spring Security가 알아서 처리함)
				.defaultSuccessUrl("/success") // 목적지(주소)없이 로그인 성공 했을 때 가는 페이지
				.permitAll()
				);
		
		http.csrf(AbstractHttpConfigurer::disable); // csrf 기능 끄기
		
		return http.build();
	}
	
	
}
