package com.github.orlandroyd.chirp.domain.model

import com.github.orlandroyd.chirp.domain.type.UserId

data class User(
    val id: UserId,
    val username: String,
    val email: String,
    val hasEmailVerified: Boolean
)