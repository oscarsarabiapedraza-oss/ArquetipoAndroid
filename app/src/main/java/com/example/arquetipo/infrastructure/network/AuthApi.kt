package com.example.arquetipo.infrastructure.network

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Url

data class AuthRequest(val username: String, val password: String)
data class AuthResponse(val success: Boolean)

interface AuthApi {
    // POST to a configurable URL passed at call time via @Url
    @POST
    suspend fun login(@Url url: String, @Body body: AuthRequest): Response<AuthResponse>
}
