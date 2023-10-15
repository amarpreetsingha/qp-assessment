package com.example.qpassessment.config;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Component;

@Component
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers(HttpMethod.GET, "/grocery/items").hasAnyRole("USER", "ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/grocery/items").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/grocery/items").hasRole( "ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/grocery/items").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/grocery/order").hasAnyRole("USER", "ADMIN")
                        .requestMatchers(HttpMethod.GET, "/grocery/order").hasAnyRole("USER", "ADMIN")
                        .anyRequest().permitAll())
                .httpBasic(Customizer.withDefaults())
                .csrf().disable();
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user =
                User.builder()
                        .username("user")
                        .password( passwordEncoder().encode("user") )
                        .roles("USER")
                        .build();

        UserDetails admin =
                User.builder()
                        .username("admin")
                        .password( passwordEncoder().encode("admin") )
                        .roles("ADMIN", "USER")
                        .build();

        return new InMemoryUserDetailsManager(user, admin);
    }

    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
