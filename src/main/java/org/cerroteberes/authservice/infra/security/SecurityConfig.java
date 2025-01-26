package org.cerroteberes.userservice.infra.security;

import lombok.AllArgsConstructor;
import org.cerroteberes.userservice.domain.entity.enums.NameRole;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.cerroteberes.userservice.infra.security.EndpointSecurityConstant.*;


@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {

    /**
     * Filtro de seguridad personalizado para la autenticación basada en tokens.
     */
    private final SecurityFilter securityFilter;
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
                // Agrega un filtro de seguridad personalizado antes del UsernamePasswordAuthenticationFilter
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                // Configura las reglas de autorización
                .authorizeHttpRequests(authorize -> authorize
                        // Endpoints públicos: accesibles para todos sin autenticación
                        .requestMatchers(ENDPOINT_PUBLIC).permitAll()
                        // Endpoints privados: requieren la autoridad ROLE_USER
                        .requestMatchers(ENDPOINT_SWAGGER).authenticated()
                        // Endpoints Swagger: requieren la autoridad ROLE_ADMIN
                        // Todas las demás solicitudes: requieren autenticación
                        .anyRequest().authenticated()
                )
                // Deshabilita la autenticación HTTP Basic
                .httpBasic(AbstractHttpConfigurer::disable)
                .build();
    }

}
