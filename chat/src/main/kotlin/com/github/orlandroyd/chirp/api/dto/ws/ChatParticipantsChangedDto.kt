package com.github.orlandroyd.chirp.api.dto.ws

import com.github.orlandroyd.chirp.domain.type.ChatId

data class ChatParticipantsChangedDto(
    val chatId: ChatId
)