package org.cerroteberes.authservice.infra.database.mapper;

import org.cerroteberes.authservice.domain.entity.RefreshToken;
import org.cerroteberes.authservice.infra.database.entity.RefreshTokenEntity;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface RefreshTokenEntityMapper {
    RefreshTokenEntity toEntity(RefreshToken refreshToken);

    RefreshToken toDomain(RefreshTokenEntity refreshTokenEntity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    RefreshTokenEntity partialUpdate(RefreshToken refreshToken, @MappingTarget RefreshTokenEntity refreshTokenEntity);
}