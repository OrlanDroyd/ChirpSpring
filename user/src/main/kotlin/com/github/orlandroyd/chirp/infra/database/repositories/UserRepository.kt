package com.github.orlandroyd.chirp.infra.database.repositories

import com.github.orlandroyd.chirp.domain.type.UserId
import com.github.orlandroyd.chirp.infra.database.entities.UserEntity
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<UserEntity, UserId> {
    fun findByEmail(email: String): UserEntity?
    fun findByEmailOrUsername(email: String, username: String): UserEntity?
}