package com.github.orlandroyd.chirp.infra.database.repositories

import com.github.orlandroyd.chirp.infra.database.entities.EmailVerificationTokenEntity
import com.github.orlandroyd.chirp.infra.database.entities.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import java.time.Instant

interface EmailVerificationTokenRepository : JpaRepository<EmailVerificationTokenEntity, Long> {
    fun findByToken(token: String): EmailVerificationTokenEntity?
    fun deleteByExpiresAtLessThan(now: Instant)

    @Modifying
    @Query(
        """
        UPDATE EmailVerificationTokenEntity e
        SET e.usedAt = CURRENT_TIMESTAMP 
        WHERE e.user = :user
    """
    )
    fun invalidateActiveTokensForUser(user: UserEntity)
}