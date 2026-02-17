package com.github.orlandroyd.chirp.api.controllers

import com.github.orlandroyd.chirp.api.dto.*
import com.github.orlandroyd.chirp.api.mappers.toAuthenticatedUserDto
import com.github.orlandroyd.chirp.api.mappers.toUserDto
import com.github.orlandroyd.chirp.infra.rate_limiting.EmailRateLimiter
import com.github.orlandroyd.chirp.service.AuthService
import com.github.orlandroyd.chirp.service.EmailVerificationService
import com.github.orlandroyd.chirp.service.PasswordResetService
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/auth")
class AuthController(
    private val authService: AuthService,
    private val emailVerificationService: EmailVerificationService,
    private val passwordResetService: PasswordResetService,
    private val emailRateLimiter: EmailRateLimiter
) {

    @PostMapping("/register")
    fun register(
        @Valid @RequestBody body: RegisterRequest
    ): UserDto {
        return authService.register(
            email = body.email,
            username = body.username,
            password = body.password
        ).toUserDto()
    }

    @PostMapping("/login")
    fun login(
        @RequestBody body: LoginRequest
    ): AuthenticatedUserDto {
        return authService.login(
            email = body.email,
            password = body.password
        ).toAuthenticatedUserDto()
    }

    @PostMapping("/refresh")
    fun refresh(
        @RequestBody body: RefreshRequest
    ): AuthenticatedUserDto {
        return authService
            .refresh(body.refreshToken)
            .toAuthenticatedUserDto()
    }

    @PostMapping("/logout")
    fun logout(
        @RequestBody body: RefreshRequest
    ) {
        authService.logout(body.refreshToken)
    }

    @PostMapping("/resend-verification")
    fun resendVerification(
        @Valid @RequestBody body: EmailRequest
    ) {
        emailRateLimiter.withRateLimit(
            email = body.email
        ) {
            emailVerificationService.resendVerificationEmail(body.email)
        }
    }

    @GetMapping("/verify")
    fun verifyEmail(
        @RequestParam token: String
    ) {
        emailVerificationService.verifyEmail(token)
    }

    @PostMapping("/forgot-password")
    fun forgotPassword(
        @Valid @RequestBody body: EmailRequest
    ) {
        passwordResetService.requestPasswordReset(body.email)
    }

    @PostMapping("/reset-password")
    fun resetPassword(
        @Valid @RequestBody body: ResetPasswordRequest
    ) {
        passwordResetService.resetPassword(
            token = body.token,
            newPassword = body.newPassword
        )
    }

    @PostMapping("/change-password")
    fun changePassword(
        @Valid @RequestBody body: ChangePasswordRequest
    ) {
        // TODO: Extract request user ID and call service
    }
}