package com.github.orlandroyd.chirp.api.mappers

import com.github.orlandroyd.chirp.api.dto.DeviceTokenDto
import com.github.orlandroyd.chirp.api.dto.PlatformDto
import com.github.orlandroyd.chirp.domain.model.DeviceToken

fun DeviceToken.toDeviceTokenDto(): DeviceTokenDto {
    return DeviceTokenDto(
        userId = userId,
        token = token,
        createdAt = createdAt
    )
}

fun PlatformDto.toPlatformDto(): DeviceToken.Platform {
    return when (this) {
        PlatformDto.ANDROID -> DeviceToken.Platform.ANDROID
        PlatformDto.IOS -> DeviceToken.Platform.IOS
    }
}