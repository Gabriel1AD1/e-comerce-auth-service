package org.cerroteberes.authservice.infra.client.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

import static org.cerroteberes.authservice.domain.entity.enums.RoleMicroservice.TOTAL_ACCESS;

@Component
public class GenerateMicroserviceJWT {

    @Value("${secret.token}")  // La clave secreta debe estar en el archivo de configuración
    private String SECRET_KEY;

    @Value("${spring.application.name}")
    private String applicationName;

    // Método para generar el token JWT
    public String tokenGenerate() {
        return JWT.create()
                .withSubject("MICROSERVICE")
                .withClaim("name_microservice", applicationName) // Agregar el user_id como claim
                .withClaim("roles", List.of(TOTAL_ACCESS.name())) // Si TOTAL_ACCESS es un enum
                .withExpiresAt(new Date(System.currentTimeMillis() + 3600000)) // Expiración del token (1 hora)
                .sign(Algorithm.HMAC256(SECRET_KEY)); // Firmado con la clave secreta
    }
}
