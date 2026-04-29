package com.github.orlandroyd.chirp.api.dto

import com.github.orlandroyd.chirp.domain.type.UserId
import java.time.Instant

data class DeviceTokenDto(
    val userId: UserId,
    val token: String,
    val createdAt: Instant
)