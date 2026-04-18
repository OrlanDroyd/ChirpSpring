package com.github.orlandroyd.chirp.service

import com.github.orlandroyd.chirp.domain.event.MessageDeletedEvent
import com.github.orlandroyd.chirp.domain.events.chat.ChatEvent
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
import com.github.orlandroyd.chirp.infra.message_queue.EventPublisher
import org.springframework.context.ApplicationEventPublisher
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ChatMessageService(
    private val chatRepository: ChatRepository,
    private val chatMessageRepository: ChatMessageRepository,
    private val applicationEventPublisher: ApplicationEventPublisher,
    private val chatParticipantRepository: ChatParticipantRepository,
    private val eventPublisher: EventPublisher
) {

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

        val savedMessage = chatMessageRepository.saveAndFlush(
            ChatMessageEntity(
                id = messageId,
                content = content.trim(),
                chatId = chatId,
                chat = chat,
                sender = sender
            )
        )

        eventPublisher.publish(
            event = ChatEvent.NewMessage(
                senderId = sender.userId,
                senderUsername = sender.username,
                recipientIds = chat.participants.map { it.userId }.toSet(),
                chatId = chatId,
                message = savedMessage.content
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

        applicationEventPublisher.publishEvent(
            MessageDeletedEvent(
                chatId = message.chatId,
                messageId = messageId
            )
        )
    }
}