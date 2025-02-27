package org.cerroteberes.authservice.domain.repo;

import org.cerroteberes.authservice.domain.comons.ReadRepository;
import org.cerroteberes.authservice.domain.comons.WriteRepository;
import org.cerroteberes.authservice.domain.entity.RefreshToken;

import java.util.Optional;

public interface RefreshTokenRepository {
    RefreshToken save(RefreshToken refreshToken);

    boolean existTokenByUserId(Long userId);

    Optional<RefreshToken> findByUserId(Long userId);

    void deleteById(Long refreshTokenId);
}
