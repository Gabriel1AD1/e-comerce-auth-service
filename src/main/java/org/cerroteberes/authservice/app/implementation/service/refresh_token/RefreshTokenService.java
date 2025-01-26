package org.cerroteberes.authservice.app.implementation.service.refresh_token;

import org.cerroteberes.authservice.domain.entity.RefreshToken;

public interface RefreshTokenService {
    RefreshToken createOrDelete(Long userId);
    Boolean verifyTokenNotExpired(RefreshToken refreshToken);

    RefreshToken findByUserId(Long userId);
}
