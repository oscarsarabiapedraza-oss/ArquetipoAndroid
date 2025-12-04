package com.example.arquetipo.domain.usecase

import com.example.arquetipo.core.Result
import com.example.arquetipo.domain.repository.UserRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertTrue
import org.junit.Test

class LoginUseCaseTest {
    @Test
    fun `login remote returns true`() = runBlocking {
        val repo = mockk<UserRepository>()
        coEvery { repo.loginRemote(any(), any(), any()) } returns Result.Success(true)
        val uc = LoginUseCase(repo)
        val res = uc("u","p","https://example.com/")
        assertTrue(res is Result.Success && (res as Result.Success).data)
    }
}
