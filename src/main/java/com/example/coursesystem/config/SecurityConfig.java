package com.example.coursesystem.config;

import com.example.coursesystem.entity.Role;
import com.example.coursesystem.exception.AccessDeniedHandlerException;
import com.example.coursesystem.security.JwtAuthFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig {

  private final JwtAuthFilter jwtAuthFilter;

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
    return httpSecurity
        .exceptionHandling(
            (exception) -> exception.accessDeniedHandler(new AccessDeniedHandlerException()))
        .csrf(AbstractHttpConfigurer::disable)
        .sessionManagement(
            (session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
        .authorizeHttpRequests(
            request ->
                request
                    .requestMatchers(
                        "/auth/**", "/swagger-ui.html", "/swagger-ui/**", "/v3/api-docs/**")
                    .permitAll()
                    .requestMatchers(HttpMethod.GET, "/courses/*", "/categories/*")
                    .permitAll()
                    .requestMatchers(HttpMethod.POST, "/auth/*")
                    .permitAll()
                    .requestMatchers(
                        HttpMethod.GET, "/users/me", "/users/me/favorites", "/enrollments/*")
                    .hasAnyRole(Role.ADMIN.name(), Role.STUDENT.name(), Role.INSTRUCTOR.name())
                    .requestMatchers(
                        HttpMethod.POST, "/courses/*", "videos/*", "/modules/*", "/lessons/*")
                    .hasAnyRole(Role.ADMIN.name(), Role.INSTRUCTOR.name())
                    .requestMatchers(
                        HttpMethod.PUT, "/courses/*", "videos/*", "/modules/*", "/lessons/*")
                    .hasAnyRole(Role.ADMIN.name(), Role.INSTRUCTOR.name())
                    .requestMatchers(
                        HttpMethod.DELETE, "/courses/*", "videos/*", "/modules/*", "/lessons/*")
                    .hasAnyRole(Role.ADMIN.name(), Role.INSTRUCTOR.name())
                    .requestMatchers(
                        HttpMethod.POST,
                        "/enrollments/*, ",
                        "users/favorite-courses/*",
                        "ratings/*")
                    .hasAnyRole(Role.ADMIN.name(), Role.STUDENT.name(), Role.INSTRUCTOR.name())
                    .requestMatchers(HttpMethod.POST, "/categories/*")
                    .hasRole(Role.ADMIN.name())
                    .requestMatchers(HttpMethod.DELETE, "/categories/*")
                    .hasRole(Role.ADMIN.name())
                    .requestMatchers(HttpMethod.PUT, "/categories/*")
                    .hasRole(Role.ADMIN.name())
                    .anyRequest()
                    .authenticated())
        .build();
  }
}
