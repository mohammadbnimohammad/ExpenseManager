package com.mohammad.ExpenseManager.config;

import com.mohammad.ExpenseManager.security.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain
            (HttpSecurity http, JwtAuthenticationFilter jwtAuthenticationFilter)throws Exception{
        http.addFilterBefore(jwtAuthenticationFilter,UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(
                        auth -> auth.requestMatchers("/api/user","/api/user/login")
                                .permitAll().anyRequest().authenticated())
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(
                        session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
