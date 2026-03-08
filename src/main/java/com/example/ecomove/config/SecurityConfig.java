package com.example.ecomove.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private JwtAuthFilter jwtAuthFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http) throws Exception {

        http
                // Désactiver CSRF (on utilise JWT)
                .csrf(csrf -> csrf.disable())

                // Configurer les autorisations
                .authorizeHttpRequests(auth -> auth

                        // Endpoints publics sans token
                        .requestMatchers(
                                "/api/v1/auth/**")
                        .permitAll()

                        // Swagger sans authentification
                        .requestMatchers(
                                "/swagger-ui/**",
                                "/v3/api-docs/**")
                        .permitAll()

                        // Recherche trajets publique
                        .requestMatchers(
                                HttpMethod.GET,
                                "/api/v1/trips",
                                "/api/v1/trips/search")
                        .permitAll()

                        // Tout le reste nécessite JWT
                        .anyRequest().authenticated()
                )

                // STATELESS : pas de session HTTP
                // car on utilise JWT
                .sessionManagement(session ->
                        session.sessionCreationPolicy(
                                SessionCreationPolicy.STATELESS)
                )

                // Ajouter notre filtre JWT
                .addFilterBefore(
                        jwtAuthFilter,
                        UsernamePasswordAuthenticationFilter
                                .class);

        return http.build();
    }
}