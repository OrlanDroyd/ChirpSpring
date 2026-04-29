package com.github.orlandroyd.chirp.infra.mappers

import com.github.orlandroyd.chirp.domain.model.DeviceToken
import com.github.orlandroyd.chirp.infra.database.DeviceTokenEntity

fun DeviceTokenEntity.toDeviceToken(): DeviceToken {
    return DeviceToken(
        id = id,
        userId = userId,
        token = token,
        platform = platform.toPlatform(),
        createdAt = createdAt
    )
}