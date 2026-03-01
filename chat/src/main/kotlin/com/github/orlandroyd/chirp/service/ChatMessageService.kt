package com.github.orlandroyd.chirp.service

import com.github.orlandroyd.chirp.api.dto.ChatMessageDto
import com.github.orlandroyd.chirp.api.mappers.toChatMessageDto
import com.github.orlandroyd.chirp.domain.exception.ChatNotFoundException
import com.github.orlandroyd.chirp.domain.exception.ChatParticipantNotFoundException
import com.github.orlandroyd.chirp.domain.exception.ForbiddenException
import com.github.orlandroyd.chirp.domain.exception.MessageNotFoundException
import com.github.orlandroyd.chirp.domain.models.ChatMessage
import com.github.orlandroyd.chirp.domain.type.ChatId
import com.github.orlandroyd.chirp.domain.type.ChatMessageId
import com.github.orlandroyd.chirp.domain.type.UserId
import com.github.orlandroyd.chirp.infra.database.entities.ChatMessageEntity
import com.github.orlandroyd.chirp.infra.database.mappers.toChatMessage
import com.github.orlandroyd.chirp.infra.database.repositories.ChatMessageRepository
import com.github.orlandroyd.chirp.infra.database.repositories.ChatParticipantRepository
import com.github.orlandroyd.chirp.infra.database.repositories.ChatRepository
import org.springframework.data.domain.PageRequest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.Instant

@Service
class ChatMessageService(
    private val chatRepository: ChatRepository,
    private val chatMessageRepository: ChatMessageRepository,
    private val chatParticipantRepository: ChatParticipantRepository
) {

    fun getChatMessages(
        chatId: ChatId,
        before: Instant?,
        pageSize: Int
    ): List<ChatMessageDto> {
        return chatMessageRepository
            .findByChatIdBefore(
                chatId = chatId,
                before = before ?: Instant.now(),
                pageable = PageRequest.of(0, pageSize)
            )
            .content
            .asReversed()
            .map { it.toChatMessage().toChatMessageDto() }
    }

    @Transactional
    fun sendMessage(
        chatId: ChatId,
        senderId: UserId,
        content: String,
        messageId: ChatMessageId? = null
    ): ChatMessage {
        val chat = chatRepository.findChatById(chatId, senderId)
            ?: throw ChatNotFoundException()
        val sender = chatParticipantRepository.findByIdOrNull(senderId)
            ?: throw ChatParticipantNotFoundException(senderId)

        val savedMessage = chatMessageRepository.save(
            ChatMessageEntity(
                id = messageId,
                content = content.trim(),
                chatId = chatId,
                chat = chat,
                sender = sender
            )
        )

        return savedMessage.toChatMessage()
    }

    @Transactional
    fun deleteMessage(
        messageId: ChatMessageId,
        requestUserId: UserId
    ) {
        val message = chatMessageRepository.findByIdOrNull(messageId)
            ?: throw MessageNotFoundException(messageId)

        if (message.sender.userId != requestUserId) {
            throw ForbiddenException()
        }

        chatMessageRepository.delete(message)
    }
}