package com.github.orlandroyd.chirp.api.dto

data class AuthenticatedUserDto(
    val user: UserDto,
    val accessToken: String,
    val refreshToken: String
)