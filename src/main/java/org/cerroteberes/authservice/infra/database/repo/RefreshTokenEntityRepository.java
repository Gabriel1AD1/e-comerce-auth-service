package org.cerroteberes.authservice.infra.database.repo;

import org.cerroteberes.authservice.infra.database.entity.RefreshTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenEntityRepository extends JpaRepository<RefreshTokenEntity, Long> {
    Optional<RefreshTokenEntity> findByUserId(Long userId);

    boolean existsByUserId(Long userId);
}