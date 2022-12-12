package com.sociogram.sociogram.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.sociogram.sociogram.securities.JWTAuthzFilter;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {

  @Autowired
  private JWTAuthzFilter jwtAuthzFilter;

  @Bean
  public BCryptPasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
      .csrf()
      .disable()
      .addFilterAfter(jwtAuthzFilter, UsernamePasswordAuthenticationFilter.class)
      .authorizeHttpRequests((authz) -> authz
        .requestMatchers("/users/login", "/users/signup").permitAll()
        .anyRequest().authenticated()
      );

    return http.build();
  }

}
