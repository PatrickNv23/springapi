package com.panvdev.springapi.features.auth.repositories;

import com.panvdev.springapi.features.auth.UserActionTokenType;
import com.panvdev.springapi.features.auth.entities.AuthSession;
import com.panvdev.springapi.features.auth.entities.UserActionToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserActionTokenRepository extends JpaRepository<UserActionToken, UUID> {

    Optional<UserActionToken> findByToken(String token);
    List<AuthSession> findByTokenAndTokenType(String token, UserActionTokenType tokenType);

    @Modifying
    @Query("UPDATE UserActionToken t SET t.deleted = true WHERE t.expiresAt < :now OR t.used = true")
    void deleteExpiredOrUsedTokens(LocalDateTime now);
}
