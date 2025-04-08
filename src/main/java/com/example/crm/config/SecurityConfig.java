package com.example.crm.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.http.HttpMethod;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true, prePostEnabled = true)
public class SecurityConfig {

    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
            .authorizeHttpRequests(auth -> auth
                // Cusomer roles and permissions
                // Anyone can access this endpoint for GET requests
                .requestMatchers("/api/customers").hasAnyRole("ADMIN", "SALES", "PUBLIC") 
                .requestMatchers("/api/customers/**").hasAnyRole("ADMIN", "SALES", "PUBLIC")
                // Only admins can add customers
                .requestMatchers(HttpMethod.POST, "/api/customers").hasRole("ADMIN") 
                // Only admins can update customers
                .requestMatchers(HttpMethod.PUT, "/api/customers/**").hasRole("ADMIN") 
                // Only admins can delete customers
                .requestMatchers(HttpMethod.DELETE, "/api/customers/**").hasRole("ADMIN") 
                
                // Employee roles and permissions
                // Only admins can access this endpoint for POST, PUT, DELETE requests
                .requestMatchers("/api/employees").hasRole("ADMIN") 
                .requestMatchers("/api/employees/**").hasRole("ADMIN") 
                
                // Interaction roles and permissions
                // Anyone with ADMIN or SALES role can add interactions
                .requestMatchers(HttpMethod.POST, "/api/interactions").hasAnyRole("ADMIN", "SALES") 
                // Anyone with ADMIN or SALES role can update interactions
                .requestMatchers(HttpMethod.PUT, "/api/interactions/**").hasAnyRole("ADMIN", "SALES") 
                // Only admins can delete interactions
                .requestMatchers(HttpMethod.DELETE, "/api/interactions/**").hasRole("ADMIN") 
                // Anyone with ADMIN or SALES role can fetch interactions
                .requestMatchers("/api/interactions/**").hasAnyRole("ADMIN", "SALES") 
                .requestMatchers("/api/interactions").hasAnyRole("ADMIN", "SALES")

                // Product roles and permissions
                // Only admins can add products
                .requestMatchers(HttpMethod.POST, "/api/products").hasRole("ADMIN")
                // Only admins can update products
                .requestMatchers(HttpMethod.PUT, "/api/products/**").hasRole("ADMIN") 
                // Only admins can delete products
                .requestMatchers(HttpMethod.DELETE, "/api/products/**").hasRole("ADMIN") 
                // Anyone with PUBLIC, ADMIN, SALES, or PRODUCTION role can fetch products
                .requestMatchers("/api/products/**").hasAnyRole("PUBLIC", "ADMIN", "SALES", "PRODUCTION")
                .requestMatchers("/api/products").hasAnyRole("PUBLIC", "ADMIN", "SALES", "PRODUCTION")

                // Sales roles and permissions
                // Anyone with PUBLIC, ADMIN, SALES role can add sales
                .requestMatchers(HttpMethod.POST, "/api/sales").hasAnyRole("ADMIN", "PUBLIC", "SALES") 
                // Anyone with ADMIN, SALES, or PRODUCTION role can update sales
                .requestMatchers(HttpMethod.PUT, "/api/sales/**").hasAnyRole("ADMIN", "PRODUCTION", "SALES") 
                // Only admins can delete sales
                .requestMatchers(HttpMethod.DELETE, "/api/sales/**").hasRole("ADMIN") 
                // Anyone with PUBLIC, ADMIN, SALES, or PRODUCTION role can fetch sales
                .requestMatchers("/api/sales").hasAnyRole("PUBLIC", "ADMIN", "SALES", "PRODUCTION")
                .requestMatchers("/api/sales/**").hasAnyRole("PUBLIC", "ADMIN", "SALES", "PRODUCTION") 
                
                .anyRequest().authenticated()
            ).httpBasic(); // Use HTTP Basic authentication instead
        return http.build();
    }

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }
}
