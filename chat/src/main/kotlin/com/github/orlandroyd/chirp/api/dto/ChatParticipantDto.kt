package com.github.orlandroyd.chirp.api.dto

import com.github.orlandroyd.chirp.domain.type.UserId

data class ChatParticipantDto(
    val userId: UserId,
    val username: String,
    val email: String,
    val profilePictureUrl: String?
)