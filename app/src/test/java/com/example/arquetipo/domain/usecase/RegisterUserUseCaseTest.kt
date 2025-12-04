package com.example.arquetipo.domain.usecase

import com.example.arquetipo.core.Result
import com.example.arquetipo.domain.model.User
import com.example.arquetipo.domain.repository.UserRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertTrue
import org.junit.Test

class RegisterUserUseCaseTest {
    @Test
    fun `register success returns id`() = runBlocking {
        val repo = mockk<UserRepository>()
        coEvery { repo.registerUser(any()) } returns Result.Success(42L)
        val uc = RegisterUserUseCase(repo)
        val res = uc(User(username = "a", password = "b"))
        assertTrue(res is Result.Success && (res as Result.Success).data == 42L)
    }
}
