/*
package com.example.user_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth ->
                        auth.anyRequest().permitAll()
                );

        return http.build();
    }
}

 */

package com.example.user_service.config;

import com.example.user_service.security.JwtFilter;

// import jakarta.ws.rs.HttpMethod;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.
        HttpSecurity;

import org.springframework.security.config.http.
        SessionCreationPolicy;

import org.springframework.security.crypto.bcrypt.
        BCryptPasswordEncoder;

import org.springframework.security.web.
        SecurityFilterChain;

import org.springframework.security.web.authentication.
        UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private final JwtFilter jwtFilter;

    public SecurityConfig(JwtFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable())

                .sessionManagement(session ->
                        session.sessionCreationPolicy(
                                SessionCreationPolicy.STATELESS
                        )
                )
/*
                .authorizeHttpRequests(auth ->
                        auth
                                .requestMatchers(
                                        "/users",
                                        "/users/login",
                                        "/users/**",
                                        "/error",
                                        "/users/id/*"
                                )
                                .permitAll()

                                .anyRequest()
                                .authenticated()
                )


 */

                .authorizeHttpRequests(auth -> auth

                        // Public APIs
                        .requestMatchers(
                                HttpMethod.POST,
                                "/users",
                                "/users/login"
                        ).permitAll()

                        // Swagger
                        .requestMatchers(
                                "/swagger-ui/**",
                                "/v3/api-docs/**"
                        ).permitAll()

                        // Logged-in users
                        .requestMatchers(
                                "/users/me"
                        ).authenticated()

                        // Only Admin - Get All Users
                        .requestMatchers(
                                HttpMethod.GET,
                                "/users"
                        ).hasRole("ADMIN")

                        // Only Admin - Get Users By id
                        .requestMatchers(
                                HttpMethod.GET,
                                "/users/{id}"
                        ).hasRole("ADMIN")



                        // Only Admin - Update Role
                        .requestMatchers(
                                HttpMethod.PUT,
                                "/users/*/role"
                        ).hasRole("ADMIN")

                        .anyRequest().authenticated()
                )


                .addFilterBefore(
                        jwtFilter,
                        UsernamePasswordAuthenticationFilter.class
                );

        return http.build();
    }
}