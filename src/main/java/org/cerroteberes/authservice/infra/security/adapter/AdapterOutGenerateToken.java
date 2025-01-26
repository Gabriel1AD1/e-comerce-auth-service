package org.cerroteberes.authservice.infra.security.adapter;

import lombok.AllArgsConstructor;
import org.cerroteberes.authservice.app.port.output.utils.OutGenerateToken;
import org.cerroteberes.authservice.infra.security.utils.JwtUtils;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class AdapterOutGenerateToken implements OutGenerateToken {

    private final JwtUtils jwtUtils;
    @Override
    public String generateToken(Long userId, List<String> roles) {
        return jwtUtils.generateUserToken(userId,roles);
    }

    @Override
    public Long expirationTimeInSeconds() {
        return jwtUtils.EXPIRATION_TIME;
    }

    @Override
    public String generateTokenRefresh(Long userId) {
        return jwtUtils.generateRefreshToken(userId);
    }

    @Override
    public Long expirationRefreshTimeInSeconds() {
        return jwtUtils.REFRESH_TOKEN_EXPIRATION_TIME;
    }

    @Override
    public Long getIdUserForRefreshToken(String refreshToken) {
        return jwtUtils.getUserIdFromToken(refreshToken);
    }

}
