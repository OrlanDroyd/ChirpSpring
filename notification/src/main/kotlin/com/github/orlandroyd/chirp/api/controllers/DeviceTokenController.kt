package com.github.orlandroyd.chirp.api.controllers

import com.github.orlandroyd.chirp.api.dto.DeviceTokenDto
import com.github.orlandroyd.chirp.api.dto.RegisterDeviceRequest
import com.github.orlandroyd.chirp.api.mappers.toDeviceTokenDto
import com.github.orlandroyd.chirp.api.mappers.toPlatformDto
import com.github.orlandroyd.chirp.api.util.requestUserId
import com.github.orlandroyd.chirp.service.PushNotificationService
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/notification")
class DeviceTokenController(private val pushNotificationService: PushNotificationService) {

    @PostMapping("/register")
    fun registerDeviceToken(
        @Valid @RequestBody body: RegisterDeviceRequest
    ): DeviceTokenDto {
        return pushNotificationService.registerDevice(
            userId = requestUserId,
            token = body.token,
            platform = body.platform.toPlatformDto()
        ).toDeviceTokenDto()
    }

    @DeleteMapping("/{token}")
    fun unregisterDeviceToken(
        @PathVariable("token") token: String
    ) {
        pushNotificationService.unregisterDevice(token)
    }
}