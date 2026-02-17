package com.github.orlandroyd.chirp.domain.exception

class RateLimitException(
    val resetsInSeconds: Long
): RuntimeException(
    "Rate limit exceeded. Please try again in $resetsInSeconds seconds."
)