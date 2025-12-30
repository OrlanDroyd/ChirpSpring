package com.github.orlandroyd.chirp.api.dto

import com.github.orlandroyd.chirp.domain.model.UserId

data class UserDto(
    val id: UserId,
    val email: String,
    val username: String,
    val hasVerifiedEmail: Boolean,
)