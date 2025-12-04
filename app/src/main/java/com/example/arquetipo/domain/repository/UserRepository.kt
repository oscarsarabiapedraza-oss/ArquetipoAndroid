package com.example.arquetipo.domain.repository

import com.example.arquetipo.domain.model.User
import com.example.arquetipo.core.Result

interface UserRepository {
    suspend fun registerUser(user: User): Result<Long>
    suspend fun getUserByUsername(username: String): User?
    suspend fun loginRemote(username: String, password: String, apiBaseUrl: String): Result<Boolean>
}
