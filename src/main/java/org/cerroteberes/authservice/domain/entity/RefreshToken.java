package org.cerroteberes.authservice.domain.entity;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data@Builder
@AllArgsConstructor
public class RefreshToken {
    @NotNull
    private Long id;
    @NotNull
    private Long userId;
    @NotNull
    private String refreshToken;
    @NotNull
    private Instant expirationTime;
    @NotNull
    private Instant createAt;

}
