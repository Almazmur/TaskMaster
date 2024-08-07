package org.example.taskmanagement.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.authentication.AuthenticationManager;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationConfiguration authenticationConfiguration;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        AuthenticationManager authenticationManager = authenticationConfiguration.getAuthenticationManager();

        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/auth/**").permitAll()
                                .requestMatchers("/tasks").authenticated() // Убедитесь, что /tasks доступен только аутентифицированным пользователям
                                .anyRequest().authenticated()
                )
                .addFilterBefore(new JwtAuthenticationFilter(authenticationManager, jwtUtil), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(new JwtAuthorizationFilter(authenticationManager, jwtUtil, userDetailsService), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}