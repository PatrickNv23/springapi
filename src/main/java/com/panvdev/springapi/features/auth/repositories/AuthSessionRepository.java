package com.panvdev.springapi.features.auth.repositories;

import com.panvdev.springapi.features.auth.entities.AuthSession;
import com.panvdev.springapi.features.auth.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AuthSessionRepository extends JpaRepository<AuthSession, UUID> {

    Optional<AuthSession> findByRefreshToken(String refreshToken);
    List<AuthSession> findByCustomerAndRevokedFalse(Customer customer);

    @Modifying
    @Query("UPDATE AuthSession a SET a.revoked = true WHERE a.customer.id = :customerId")
    void revokeAllByCustomerId(UUID customerId);

    @Modifying
    @Query("UPDATE AuthSession a SET a.deleted = true WHERE a.expiresAt < :now")
    void deleteExpiredSessions(LocalDateTime now);
}
