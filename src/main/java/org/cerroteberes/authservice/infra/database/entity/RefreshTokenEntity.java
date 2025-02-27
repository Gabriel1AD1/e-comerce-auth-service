package org.cerroteberes.authservice.infra.database.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tbl_refresh_token")
public class RefreshTokenEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(nullable = false)
    private Long id;

    @Column(name = "user_id", unique = true)
    private Long userId;

    @Column(name = "refresh_token")
    private String refreshToken;

    @Column(name = "expiration_time")
    private Instant expirationTime;

    @Column(name = "create_at")
    private Instant createAt;

    @PrePersist
    private void generateCreate() {
        this.createAt = Instant.now();
        this.expirationTime = Instant.now().plusSeconds(60 * 60 * 24 * 7); // 1 semana
    }
}
