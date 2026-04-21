package com.github.orlandroyd.chirp.domain.models

import java.time.Instant

data class ProfilePictureUploadCredentials(
    val uploadUrl: String,
    val publicUrl: String,
    val headers: Map<String, String>,
    val expiresAt: Instant
)