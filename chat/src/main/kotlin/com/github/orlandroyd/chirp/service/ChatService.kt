package com.github.orlandroyd.chirp.service

import com.github.orlandroyd.chirp.domain.exception.ChatParticipantNotFoundException
import com.github.orlandroyd.chirp.domain.exception.InvalidChatSizeException
import com.github.orlandroyd.chirp.domain.models.Chat
import com.github.orlandroyd.chirp.infra.database.entities.ChatEntity
import com.github.orlandroyd.chirp.infra.database.mappers.toChat
import com.github.orlandroyd.chirp.infra.database.repositories.ChatParticipantRepository
import com.github.orlandroyd.chirp.infra.database.repositories.ChatRepository
import com.github.orlandroyd.chirp.domain.type.UserId
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ChatService(
    private val chatRepository: ChatRepository,
    private val chatParticipantRepository: ChatParticipantRepository,
) {

    @Transactional
    fun createChat(
        creatorId: UserId,
        otherUserIds: Set<UserId>
    ): Chat {
        val otherParticipants = chatParticipantRepository.findByUserIdIn(
            userIds = otherUserIds
        )

        val allParticipants = (otherParticipants + creatorId)
        if (allParticipants.size < 2) {
            throw InvalidChatSizeException()
        }

        val creator = chatParticipantRepository.findByIdOrNull(creatorId)
            ?: throw ChatParticipantNotFoundException(creatorId)

        return chatRepository.save(
            ChatEntity(
                creator = creator,
                participants = setOf(creator) + otherParticipants
            )
        ).toChat(lastMessage = null)
    }
}