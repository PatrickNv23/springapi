package com.panvdev.springapi.features.auth.entities;

import com.panvdev.springapi.core.entities.BaseEntity;
import com.panvdev.springapi.features.auth.UserActionTokenType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "user_action_token")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = "customer") // evita carga lazy
@EqualsAndHashCode(exclude = "customer", callSuper = false)
public class UserActionToken extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private Long id;

    @Column(nullable = false, unique = true)
    private String token;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private UserActionTokenType tokenType;

    @Column(nullable = false)
    private LocalDateTime expiresAt;

    @Column(nullable = false)
    private Boolean used = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    public boolean isValid() {
        return !used && LocalDateTime.now().isBefore(expiresAt);
    }
}
