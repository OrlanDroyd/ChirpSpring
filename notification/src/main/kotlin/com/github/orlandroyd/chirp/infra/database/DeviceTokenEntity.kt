package com.github.orlandroyd.chirp.infra.database


import com.github.orlandroyd.chirp.domain.type.UserId
import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import java.time.Instant

@Entity
@Table(
    name = "device_tokens",
    schema = "notification_service",
    indexes = [
        Index(name = "idx_device_tokens_user_id", columnList = "user_id"),
        Index(name = "idx_device_tokens_token", columnList = "token", unique = true),
    ]
)
class DeviceTokenEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,
    @Column(nullable = false)
    var userId: UserId,
    @Column(nullable = false)
    var token: String,
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    var platform: PlatformEntity,
    @CreationTimestamp
    var createdAt: Instant = Instant.now(),
)