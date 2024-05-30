package com.ptithcm.identity_service.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import jakarta.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity
public class AuthConfig {
	
	@Bean
	UserDetailsService userDetailsService() {
		return new CustomUsersDetailsService();
	}
	
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/api/v1/auth/**").permitAll()
                .anyRequest().authenticated()
            )
            .logout(logout->logout
            		.logoutUrl("/api/v1/auth/logout")
            		.logoutSuccessHandler(logoutSuccessHandler())
            		.invalidateHttpSession(true)
            		.clearAuthentication(true)
            		.deleteCookies("JSESSIONID")
            ).sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        return http.build();
    }
    @Bean
    LogoutSuccessHandler logoutSuccessHandler() {
        return (request, response, authentication) -> {
            SecurityContextHolder.clearContext();
            response.setStatus(HttpServletResponse.SC_OK);
     };
    }
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
	
    @Bean
    AuthenticationProvider authenticationProvider(){
       DaoAuthenticationProvider authenticationProvider=new DaoAuthenticationProvider();
       authenticationProvider.setUserDetailsService(userDetailsService());
       authenticationProvider.setPasswordEncoder(passwordEncoder());
       return authenticationProvider;
    }
}
