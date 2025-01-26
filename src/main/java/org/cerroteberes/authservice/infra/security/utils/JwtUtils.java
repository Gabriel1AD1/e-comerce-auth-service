package org.cerroteberes.authservice.app.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.cerroteberes.authservice.domain.entity.enums.NameRole;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JwtUtils {

    private static final Logger log = LoggerFactory.getLogger(JwtUtils.class);

    @Value("${secret.token}")
    private String SECRET_KEY; // Clave secreta para firmar el JWT

    @Value("${jwt.expiration.ms}")
    private long EXPIRATION_TIME; // Tiempo de expiración en milisegundos

    /**
     * Genera un token JWT para un usuario.
     * @param userId ID del usuario.
     * @param roles Lista de roles del usuario.
     * @return Token JWT.
     */
    public String generateUserToken(Long userId, List<NameRole> roles) {
        log.info("Generando token para usuario con ID: {} y roles: {}", userId, roles);

        String rolesAsString = roles.stream()
                .map(Enum::name)
                .collect(Collectors.joining(","));

        return JWT.create()
                .withSubject("USER")
                .withClaim("user_id", userId)
                .withClaim("roles", rolesAsString)
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(Algorithm.HMAC256(SECRET_KEY));
    }
}
