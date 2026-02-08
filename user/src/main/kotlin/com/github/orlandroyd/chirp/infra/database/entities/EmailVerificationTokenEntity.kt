package com.github.orlandroyd.chirp.infra.database.entities

import com.github.orlandroyd.chirp.infra.security.TokenGenerator
import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import java.time.Instant

@Entity
@Table(
    name = "email_verification_tokens",
    schema = "user_service",
)
class EmailVerificationTokenEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,
    @Column(nullable = false, unique = true)
    var token: String = TokenGenerator.generateSecureToken(),
    @Column(nullable = false)
    var expiresAt: Instant,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    var user: UserEntity,
    @Column
    var usedAt: Instant?,
    @CreationTimestamp
    var createdAt: Instant = Instant.now(),
)