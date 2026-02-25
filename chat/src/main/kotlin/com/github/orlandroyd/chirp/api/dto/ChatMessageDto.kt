package com.github.orlandroyd.chirp.api.dto

import com.github.orlandroyd.chirp.domain.type.ChatId
import com.github.orlandroyd.chirp.domain.type.ChatMessageId
import com.github.orlandroyd.chirp.domain.type.UserId
import java.time.Instant

data class ChatMessageDto(
    val id: ChatMessageId,
    val chatId: ChatId,
    val content: String,
    val createdAt: Instant,
    val senderId: UserId
)