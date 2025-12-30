package com.github.orlandroyd.chirp.infra.security

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Component

@Component
class PasswordEncoder {

    private val bcrypt = BCryptPasswordEncoder()

    fun encode(rawPassword: String): String = bcrypt.encode(rawPassword).orEmpty()

    fun matches(rawPassword: String, hashedPassword: String): Boolean {
        return bcrypt.matches(rawPassword, hashedPassword)
    }
}