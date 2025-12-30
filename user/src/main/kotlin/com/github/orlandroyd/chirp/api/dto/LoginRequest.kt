package com.github.orlandroyd.chirp.api.dto

data class LoginRequest(
    val email: String,
    val password: String
)