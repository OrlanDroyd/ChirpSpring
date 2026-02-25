package com.github.orlandroyd.chirp.api.util

import com.github.orlandroyd.chirp.domain.exception.UnauthorizedException
import com.github.orlandroyd.chirp.domain.type.UserId
import org.springframework.security.core.context.SecurityContextHolder

val requestUserId: UserId
    get() = SecurityContextHolder.getContext().authentication?.principal as? UserId
        ?: throw UnauthorizedException()