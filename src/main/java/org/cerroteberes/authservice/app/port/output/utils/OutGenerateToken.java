package org.cerroteberes.authservice.app.port.output.utils;

import java.util.List;


public interface OutGenerateToken {
    String generateToken(Long userId, List<String> roles);
    Long expirationTimeInSeconds();
    String generateTokenRefresh(Long userId);
    Long expirationRefreshTimeInSeconds();
    Long getIdUserForRefreshToken(String refreshToken);
}
