package com.example.arquetipo.domain.usecase

import com.example.arquetipo.core.Result
import com.example.arquetipo.domain.repository.UserRepository

class LoginUseCase(private val repo: UserRepository) {
    suspend operator fun invoke(username: String, password: String, apiBaseUrl: String): Result<Boolean> {
        // first check local DB for the user (optional) then remote validate
        return repo.loginRemote(username, password, apiBaseUrl)
    }
}
