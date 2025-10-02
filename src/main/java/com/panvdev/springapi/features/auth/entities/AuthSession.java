package com.panvdev.springapi.features.auth.entities;

import com.panvdev.springapi.core.entities.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "auth_session")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = "customer") // Evita carga lazy
@EqualsAndHashCode(exclude = "customer", callSuper = false)
public class AuthSession extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, unique = true, length = 500)
    private String refreshToken;

    @Column(nullable = false)
    private LocalDateTime expiresAt;

    @Column(length = 45)
    private String ipAddress;

    private String userAgent;

    @Column(nullable = false)
    private Boolean revoked = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    public boolean isValid() {
        return !revoked && LocalDateTime.now().isBefore(expiresAt);
    }
}
