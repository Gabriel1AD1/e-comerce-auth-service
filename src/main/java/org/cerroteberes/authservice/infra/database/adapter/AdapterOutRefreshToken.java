package org.cerroteberes.authservice.infra.database.adapter;

import lombok.AllArgsConstructor;
import org.cerroteberes.authservice.app.port.output.repo.OutRefreshToken;
import org.cerroteberes.authservice.domain.entity.RefreshToken;
import org.cerroteberes.authservice.infra.database.entity.RefreshTokenEntity;
import org.cerroteberes.authservice.infra.database.mapper.RefreshTokenEntityMapper;
import org.cerroteberes.authservice.infra.database.repo.RefreshTokenEntityRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static org.cerroteberes.authservice.app.utils.OptionalMapper.wrapInOptional;


@Repository
@AllArgsConstructor
public class AdapterOutRefreshToken implements OutRefreshToken {
    private final RefreshTokenEntityRepository refreshTokenEntityRepository;
    private final RefreshTokenEntityMapper refreshTokenEntityMapper;
    @Override
    public RefreshToken save(RefreshToken refreshToken) {
        return refreshTokenEntityMapper.toDomain(refreshTokenEntityRepository.save(refreshTokenEntityMapper.toEntity(refreshToken)));
    }

    @Override
    public boolean existTokenByUserId(Long userId) {
        return refreshTokenEntityRepository.existsByUserId(userId);
    }

    @Override
    public Optional<RefreshToken> findByUserId(Long userId) {
        RefreshTokenEntity refreshTokenOptional = refreshTokenEntityRepository.findByUserId(userId).orElse(null);
        RefreshToken refreshToken = refreshTokenEntityMapper.toDomain(refreshTokenOptional);
        return wrapInOptional(refreshToken);
    }

    @Override
    public void deleteById(Long refreshTokenId) {
        refreshTokenEntityRepository.deleteById(refreshTokenId);
    }
}
