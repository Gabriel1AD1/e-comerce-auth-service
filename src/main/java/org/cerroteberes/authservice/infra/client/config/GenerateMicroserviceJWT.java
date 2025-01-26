
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.cerroteberes.authservice.domain.entity.enums.NameRole;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class GenerateJwtToken {

    @Value("${secret.token}")  // La clave secreta debe estar en el archivo de configuración
    private String SECRET_KEY;

    // Método para generar el token JWT
    public String generateToken(Long userId) {
        return JWT.create()
                .withSubject("user_auth")  // Sujeto del token, podría ser "user_auth"
                .withClaim("user_id", userId) // Agregar el user_id como claim
                .withClaim("roles", List.of(NameRole.MICROSERVICE)) // Agregar roles como lista de cadenas
                .withExpiresAt(new Date(System.currentTimeMillis() + 3600000)) // Expiración del token (1 hora)
                .sign(Algorithm.HMAC256(SECRET_KEY)); // Firmado con la clave secreta
    }
}
