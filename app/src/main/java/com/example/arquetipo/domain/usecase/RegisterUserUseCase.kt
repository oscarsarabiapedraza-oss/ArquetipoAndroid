package com.example.arquetipo.domain.usecase

import com.example.arquetipo.domain.model.User
import com.example.arquetipo.domain.repository.UserRepository
import com.example.arquetipo.core.Result

class RegisterUserUseCase(private val repo: UserRepository) {
    suspend operator fun invoke(user: User): Result<Long> {
        return repo.registerUser(user)
    }
}
