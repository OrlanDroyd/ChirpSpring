package com.github.orlandroyd.chirp.infra.database.mappers

import com.github.orlandroyd.chirp.domain.model.User
import com.github.orlandroyd.chirp.infra.database.entities.UserEntity

fun UserEntity.toUser(): User {
    return User(
        id = id!!,
        username = username,
        email = email,
        hasEmailVerified = hasVerifiedEmail
    )
}