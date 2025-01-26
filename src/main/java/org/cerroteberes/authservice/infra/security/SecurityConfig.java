package org.cerroteberes.authservice.infra.security;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {

    /**
     * Configura la cadena de filtros de seguridad para la aplicación.
     *
     * @param http Objeto HttpSecurity para personalizar las configuraciones de seguridad.
     * @return SecurityFilterChain la cadena de filtros de seguridad configurada.
     * @throws Exception si ocurre un error durante la configuración.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                // Deshabilita CORS y CSRF
                .cors(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                // Configura las reglas de autorización
                .authorizeHttpRequests(authorize -> authorize
                        // Permite todas las solicitudes sin autenticación
                        .anyRequest().permitAll()
                )
                // Deshabilita la autenticación HTTP Basic
                .httpBasic(AbstractHttpConfigurer::disable)
                .build();
    }
}
