package ru.mtuci.coursemanagement.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Spring Security configuration with security headers and access control.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            // Настройка доступа к эндпоинтам
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/login", "/register", "/css/**", "/js/**", "/h2-console/**").permitAll()
                .anyRequest().authenticated()
            )
            // Настройка формы логина
            .formLogin(form -> form
                .loginPage("/login")
                .permitAll()
            )
            // Настройка logout
            .logout(logout -> logout
                .logoutUrl("/logout")
                .permitAll()
            )
            // Включение CSRF защиты
            .csrf(csrf -> csrf
                .ignoringRequestMatchers("/h2-console/**") // H2 console requires CSRF to be disabled
            )
            // Security headers
            .headers(headers -> headers
                .contentSecurityPolicy(csp -> csp
                    .policyDirectives("default-src 'self'; script-src 'self' 'unsafe-inline'; style-src 'self' 'unsafe-inline'")
                )
                .frameOptions(frame -> frame.sameOrigin())
                .xssProtection(xss -> xss.headerValue("1; mode=block"))
                .contentTypeOptions(cto -> cto.disable().disable())
            );

        // Для H2 console
        http.headers(headers -> headers.frameOptions(frame -> frame.disable()));

        return http.build();
    }
}
