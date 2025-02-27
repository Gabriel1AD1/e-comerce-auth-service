package org.cerroteberes.authservice.infra.security.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
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

    @Value("${jwt.expiration.s}")
    public long EXPIRATION_TIME; // Tiempo de expiración en milisegundos

    @Value("${jwt.refresh.expiration.s}")
    public long REFRESH_TOKEN_EXPIRATION_TIME;

    /**
     * Genera un token JWT para un usuario.
     *
     * @param userId ID del usuario.
     * @param roles  Lista de roles del usuario.
     * @return Token JWT.
     */
    public String generateUserToken(Long userId, List<String> roles) {
        log.info("Generando token para usuario con ID: {} y roles: {}", userId, roles);

        return JWT.create()
                .withSubject("USER")
                .withClaim("user_id", userId)
                .withClaim("roles", roles)
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME)) // Expiración
                .sign(Algorithm.HMAC256(SECRET_KEY)); // Firma con la clave secreta
    }

    /**
     * Genera un token de refresco (Refresh Token) solo con el userId.
     *
     * @param userId ID del usuario.
     * @return Token de refresco (Refresh Token).
     */
    public String generateRefreshToken(Long userId) {
        log.info("Generando refresh token para usuario con ID: {}", userId);

        return JWT.create()
                .withSubject("REFRESH")
                .withClaim("user_id", userId) // Solo el userId
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + REFRESH_TOKEN_EXPIRATION_TIME)) // Expiración del Refresh Token
                .sign(Algorithm.HMAC256(SECRET_KEY)); // Firma con la clave secreta
    }

    /**
     * Decodifica un token JWT y obtiene el userId.
     *
     * @param token El token JWT.
     * @return El userId extraído del token.
     * @throws JWTDecodeException Si el token no puede ser decodificado correctamente.
     */
    public Long getUserIdFromToken(String token) throws JWTDecodeException {
        try {
            // Decodificar el token y obtener la claim "user_id"
            return JWT.decode(token).getClaim("user_id").asLong();
        } catch (JWTDecodeException e) {
            log.error("Error decodificando el token: {}", e.getMessage());
            throw e;
        }
    }
}
