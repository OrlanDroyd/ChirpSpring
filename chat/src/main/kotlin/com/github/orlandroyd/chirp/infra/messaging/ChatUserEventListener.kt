package com.github.orlandroyd.chirp.infra.messaging

import com.github.orlandroyd.chirp.domain.events.user.UserEvent
import com.github.orlandroyd.chirp.domain.models.ChatParticipant
import com.github.orlandroyd.chirp.infra.message_queue.MessageQueues
import com.github.orlandroyd.chirp.service.ChatParticipantService
import org.slf4j.LoggerFactory
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.stereotype.Component

@Component
class ChatUserEventListener(
    private val chatParticipantService: ChatParticipantService
) {

    private val logger = LoggerFactory.getLogger(javaClass)

    @RabbitListener(queues = [MessageQueues.CHAT_USER_EVENTS])
    fun handleUserEvent(event: UserEvent) {
        logger.info("Received user event: {}", event)
        when (event) {
            is UserEvent.Verified -> {
                chatParticipantService.createChatParticipant(
                    chatParticipant = ChatParticipant(
                        userId = event.userId,
                        username = event.username,
                        email = event.email,
                        profilePictureUrl = null
                    )
                )
            }

            else -> Unit
        }
    }
}