package com.example.crm.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.beans.factory.annotation.Autowired;

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
                .requestMatchers("/api/admin/**").hasRole("ADMIN")
                .requestMatchers("/api/orders").hasAnyRole("PUBLIC", "ADMIN")
                .requestMatchers("/api/orders/delete").hasAnyRole("PUBLIC", "ADMIN")
                .requestMatchers("/api/products").hasAnyRole("PUBLIC", "ADMIN", "SALES", "PRODUCTION")
                .requestMatchers("/api/interactions/**").hasAnyRole("SALES", "ADMIN")
                .requestMatchers("/api/orders/update-status").hasAnyRole("PRODUCTION", "ADMIN")
                .anyRequest().authenticated()
            ).httpBasic(); // Use HTTP Basic authentication instead
        return http.build();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
            .withUser("admin")
            .password(passwordEncoder().encode("password"))
            .roles("ADMIN")
            .and()
            .withUser("public")
            .password(passwordEncoder().encode("password"))
            .roles("PUBLIC")
            .and()
            .withUser("sales")
            .password(passwordEncoder().encode("password"))
            .roles("SALES")
            .and()
            .withUser("production")
            .password(passwordEncoder().encode("password"))
            .roles("PRODUCTION");
    }
}
