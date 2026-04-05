package com.zorvyn.assignment.config;

// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private final JWTFilter jwtFilter;

    public SecurityConfig(JWTFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }
//     @Autowired
//     private CorsConfig config;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
        .cors(cors -> {})
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth

                        // ── Public 
                        .requestMatchers("/users/create", "/auth/login", "/auth/**",
                                                        "/swagger-ui/**",
                                                        "/v3/api-docs/**",
                                                        "/swagger-ui.html").permitAll()

                        // ── ADMIN only 
                        .requestMatchers(
                                "/transactions/create/**",
                                "/transactions/update/**",
                                "/transactions/delete/**",
                                "/users/delete/**",
                                "/users/update/**")
                        .hasAuthority("ADMIN")

                        // ── ADMIN + ANALYST
                        .requestMatchers(
                                "/transactions/category/**",
                                "/transactions/type/**",
                                "/transactions/date/**",
                                "/dashboard/**")
                        .hasAnyAuthority("ADMIN", "ANALYST")

                        // ── ADMIN + ANALYST + VIEWER 
                        .requestMatchers(
                                "/transactions/all")
                        .hasAnyAuthority("ADMIN", "ANALYST", "VIEWER")

                        .anyRequest().authenticated())
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}