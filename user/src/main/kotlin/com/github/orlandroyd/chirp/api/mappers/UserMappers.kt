package com.github.orlandroyd.chirp.api.mappers

import com.github.orlandroyd.chirp.api.dto.AuthenticatedUserDto
import com.github.orlandroyd.chirp.api.dto.UserDto
import com.github.orlandroyd.chirp.domain.model.AuthenticatedUser
import com.github.orlandroyd.chirp.domain.model.User

fun AuthenticatedUser.toAuthenticatedUserDto(): AuthenticatedUserDto {
    return AuthenticatedUserDto(
        user = user.toUserDto(),
        accessToken = accessToken,
        refreshToken = refreshToken
    )
}

fun User.toUserDto(): UserDto {
    return UserDto(
        id = id,
        email = email,
        username = username,
        hasVerifiedEmail = hasEmailVerified
    )
}