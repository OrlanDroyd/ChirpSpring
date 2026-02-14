package com.github.orlandroyd.chirp.api.dto

import com.github.orlandroyd.chirp.api.util.Password
import jakarta.validation.constraints.NotBlank

data class ResetPasswordRequest(
    @field:NotBlank
    val token: String,
    @field:Password
    val newPassword: String
)