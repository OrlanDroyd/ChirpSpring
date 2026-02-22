package com.github.orlandroyd.chirp.infra.database.entities

import com.github.orlandroyd.chirp.domain.type.ChatId
import com.github.orlandroyd.chirp.domain.type.ChatMessageId
import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction
import java.time.Instant

@Entity
@Table(
    name = "chat_messages",
    schema = "chat_service",
    indexes = [
        Index(
            name = "idx_chat_message_chat_id_created_at",
            columnList = "chat_id,created_at DESC"
        )
    ]
)
class ChatMessageEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    var id: ChatMessageId? = null,
    @Column(nullable = false)
    var content: String,
    @Column(
        name = "chat_id",
        nullable = false,
        updatable = false
    )
    var chatId: ChatId,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
        name = "chat_id",
        nullable = false,
        insertable = false,
        updatable = false
    )
    @OnDelete(action = OnDeleteAction.CASCADE)
    var chat: ChatEntity? = null,
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(
        name = "sender_id",
        nullable = false,
        insertable = false,
        updatable = false
    )
    var sender: ChatParticipantEntity,
    @CreationTimestamp
    var createdAt: Instant = Instant.now()
)