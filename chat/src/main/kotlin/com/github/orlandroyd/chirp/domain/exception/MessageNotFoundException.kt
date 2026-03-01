package com.github.orlandroyd.chirp.domain.exception

import com.github.orlandroyd.chirp.domain.type.ChatMessageId

class MessageNotFoundException(
    id: ChatMessageId
) : RuntimeException(
    "Message with ID $id not found"
)