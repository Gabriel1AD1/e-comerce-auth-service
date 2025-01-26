package org.cerroteberes.authservice.app.implementation.service.refresh_token;

import lombok.AllArgsConstructor;
import org.cerroteberes.authservice.app.exception.EntityNotFoundException;
import org.cerroteberes.authservice.app.exception.FailedCreateRefreshTokenException;
import org.cerroteberes.authservice.app.port.output.annotation.AppService;
import org.cerroteberes.authservice.app.port.output.repo.OutRefreshToken;
import org.cerroteberes.authservice.app.port.output.utils.OutGenerateToken;
import org.cerroteberes.authservice.domain.entity.RefreshToken;
import org.cerroteberes.authservice.domain.repo.RefreshTokenRepository;

import java.time.Duration;
import java.time.Instant;
import java.util.Optional;

@AppService
@AllArgsConstructor
public class RefreshTokenServiceI implements RefreshTokenService {
    private final RefreshTokenRepository refreshTokenRepository;
    private final OutGenerateToken generateToken;
    @Override
    public RefreshToken createOrDelete(Long userId) {
        // Verificar si ya existe un refresh token para el userId
        Optional<RefreshToken> existingToken = refreshTokenRepository.findByUserId(userId);

        if (existingToken.isPresent()) {
            // Verificar si ha pasado más de una semana desde la creación del token
            Duration duration = Duration.between(existingToken.get().getCreateAt(), Instant.now());
            if (duration.toDays() <= 7) {
                // Si no han pasado más de 7 días (una semana), devolver el token existente
                return existingToken.get();
            } else {
                // Si ha pasado más de una semana, eliminar el token expirado
                refreshTokenRepository.deleteById(existingToken.get().getId());
            }
        }
        RefreshToken refreshToken = RefreshToken.builder()
                .userId(userId)
                .refreshToken(generateToken.generateTokenRefresh(userId))
                .createAt(Instant.now())
                .expirationTime(Instant.now().plusSeconds(60 * 60 * 24 * 7))
                .build();
        // Si no existe el token o ha sido eliminado, guardar y devolver el nuevo token
        return refreshTokenRepository.save(refreshToken);
    }

    @Override
    public Boolean verifyTokenNotExpired(RefreshToken refreshToken) {
        // Verificar si ha pasado más de una semana desde la creación del token
        Duration duration = Duration.between(refreshToken.getCreateAt(), Instant.now());
        // Si no han pasado más de 7 días (una semana), devolver el token existente
        // Si ha pasado más de una semana, eliminar el token expirado
        return duration.toDays() <= 7;
    }

    @Override
    public RefreshToken findByUserId(Long userId) {
        return refreshTokenRepository.findByUserId(userId).orElseThrow(
                () -> new EntityNotFoundException("No se ha encontrado el token que se ha buscado por su identificador")
        );
    }
}
