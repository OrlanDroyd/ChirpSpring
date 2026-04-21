package com.github.orlandroyd.chirp

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableScheduling
class ChirpApplication

fun main(args: Array<String>) {
    runApplication<ChirpApplication>(*args)
}
