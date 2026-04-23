package com.github.orlandroyd.chirp.api.dto.ws

import com.github.orlandroyd.chirp.domain.type.UserId

data class ProfilePictureUpdateDto(
    val userId: UserId,
    val newUrl: String?
)