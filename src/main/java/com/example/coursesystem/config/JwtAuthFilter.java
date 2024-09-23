package com.example.coursesystem.config;

import com.example.coursesystem.exception.UnauthorizedException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtAuthenticationProvider jwtAuthenticationProvider;

    private final List<String> urlsToSkip = List.of("/auth/register", "/auth/.*", "/swagger-ui.html", "/swagger-ui/.*", "/v3/api-docs/.*", "/v3/api-docs", "/favicon.ico");

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String requestURI = request.getRequestURI();
        System.out.println("Entering shouldNotFilter: " + requestURI);
        return urlsToSkip.stream().anyMatch(requestURI::matches);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        System.out.println("Entering doFilterInternal");
        String header = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (header == null) {
            System.out.println("No Authorization header found");
            throw new UnauthorizedException();
        }

        String[] parts = header.split(" ");

        if (parts.length != 2 || !"Bearer".equals(parts[0])) {
            throw new UnauthorizedException();
        }

        try {
            Authentication auth = jwtAuthenticationProvider.validateToken(parts[1]);
            SecurityContextHolder.getContext().setAuthentication(auth);

            System.out.println("voy a imprimir el context");
            System.out.println(SecurityContextHolder.getContext());
            System.out.println("voy a imprimir la autenticacion");
            System.out.println(SecurityContextHolder.getContext().getAuthentication());
        } catch (RuntimeException e) {
            System.out.println("Authentication failed");
            SecurityContextHolder.clearContext();
            throw new UnauthorizedException();
        }

        filterChain.doFilter(request, response);
    }
}
