package com.github.orlandroyd.chirp.api.mappers

import com.github.orlandroyd.chirp.api.dto.PictureUploadResponse
import com.github.orlandroyd.chirp.domain.models.ProfilePictureUploadCredentials

fun ProfilePictureUploadCredentials.toResponse(): PictureUploadResponse {
    return PictureUploadResponse(
        uploadUrl = uploadUrl,
        publicUrl = publicUrl,
        headers = headers,
        expiresAt = expiresAt
    )
}