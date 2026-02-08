package com.github.orlandroyd.chirp.infra.database.mappers

import com.github.orlandroyd.chirp.domain.model.EmailVerificationToken
import com.github.orlandroyd.chirp.infra.database.entities.EmailVerificationTokenEntity

fun EmailVerificationTokenEntity.toEmailVerificationToken(): EmailVerificationToken {
    return EmailVerificationToken(
        id = id,
        token = token,
        user = user.toUser()
    )
}