package com.github.orlandroyd.chirp.api.dto

import com.github.orlandroyd.chirp.domain.type.UserId
import jakarta.validation.constraints.Size

data class AddParticipantToChatDto(
    @field:Size(min = 1)
    val userIds: List<UserId>
)