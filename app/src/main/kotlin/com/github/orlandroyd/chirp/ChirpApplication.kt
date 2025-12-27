package com.github.orlandroyd.chirp

import com.github.orlandroyd.chirp.infra.database.entities.UserEntity
import com.github.orlandroyd.chirp.infra.database.repositories.UserRepository
import jakarta.annotation.PostConstruct
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ChirpApplication

fun main(args: Array<String>) {
    runApplication<ChirpApplication>(*args)
}
