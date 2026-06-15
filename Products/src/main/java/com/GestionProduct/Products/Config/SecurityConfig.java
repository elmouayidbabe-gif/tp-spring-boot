package com.GestionProduct.Products.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .authorizeHttpRequests(auth -> auth

                        // Static resources
                        .requestMatchers("/css/**", "/js/**", "/images/**").permitAll()

                        // PRODUCTS (ADMIN ONLY actions)
                        .requestMatchers("/products/new").hasRole("ADMIN")
                        .requestMatchers("/products/save").hasRole("ADMIN")
                        .requestMatchers("/products/delete/**").hasRole("ADMIN")

                        // ARTICLES (VIEW for USER + ADMIN)
                        .requestMatchers("/articles", "/articles/*")
                        .hasAnyRole("USER", "ADMIN")

                        // ARTICLES (ADMIN ONLY actions)
                        .requestMatchers("/articles/new").hasRole("ADMIN")
                        .requestMatchers("/articles/save").hasRole("ADMIN")
                        .requestMatchers("/articles/delete/**").hasRole("ADMIN")

                        // Everything else authenticated
                        .anyRequest().authenticated()
                )

                .formLogin(form -> form
                        .permitAll()
                        .defaultSuccessUrl("/products", false)
                )

                .logout(logout -> logout
                        .logoutSuccessUrl("/login?logout")
                        .permitAll()
                );

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {

        UserDetails admin = User.withUsername("admin")
                .password("{noop}admin123")
                .roles("ADMIN")
                .build();

        UserDetails user = User.withUsername("user")
                .password("{noop}user123")
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(admin, user);
    }
}