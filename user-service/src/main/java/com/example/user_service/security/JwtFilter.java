package com.example.user_service.security;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.
        UsernamePasswordAuthenticationToken;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.
        SecurityContextHolder;

import org.springframework.security.web.authentication.
        WebAuthenticationDetailsSource;

import org.springframework.stereotype.Component;

import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

//import static com.example.user_service.enums.Role.ROLE_USER;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    public JwtFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)

            throws ServletException, IOException {

        String authHeader =
                request.getHeader("Authorization");

        if (authHeader == null
                || !authHeader.startsWith("Bearer ")) {

            filterChain.doFilter(request, response);
            return;
        }

        String token = authHeader.substring(7);

        if (!jwtUtil.validateToken(token)) {

            filterChain.doFilter(request, response);
            return;
        }

        String email = jwtUtil.extractEmail(token);
        String role =
                jwtUtil.extractRole(token);
        Integer id=jwtUtil.extractUserId(token);

        JwtUser jwtUser = new JwtUser(
                id,
                email,
                role
        );




        UsernamePasswordAuthenticationToken auth =
                new UsernamePasswordAuthenticationToken(
                        jwtUser,
                        null,
                      //  Collections.emptyList()
                        List.of(
                                new SimpleGrantedAuthority(role)
                        )

                );

        auth.setDetails(
                new WebAuthenticationDetailsSource()
                        .buildDetails(request)
        );

        SecurityContextHolder
                .getContext()
                .setAuthentication(auth);

        filterChain.doFilter(request, response);
    }
}