package com.github.orlandroyd.chirp.domain.exception

class InvalidProfilePictureException(
    override val message: String? = null
): RuntimeException(
    message ?: "Invalid profile picture data"
)