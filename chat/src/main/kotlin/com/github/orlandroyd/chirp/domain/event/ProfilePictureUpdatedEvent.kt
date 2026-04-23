package com.github.orlandroyd.chirp.domain.event

import com.github.orlandroyd.chirp.domain.type.UserId

data class ProfilePictureUpdatedEvent(
    val userId: UserId,
    val newUrl: String?
)