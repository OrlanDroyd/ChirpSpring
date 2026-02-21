package com.github.orlandroyd.chirp.domain.models

import com.github.orlandroyd.chirp.domain.type.UserId

data class ChatParticipant(
    val userId: UserId,
    val username: String,
    val email: String,
    val profilePictureUrl: String?
)