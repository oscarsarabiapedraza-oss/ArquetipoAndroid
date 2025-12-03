package com.example.arquetipo.network

import retrofit2.http.Body
import retrofit2.http.POST

data class ValidateResponse(val success: Boolean)

interface ApiService {
    @POST("validate")
    suspend fun validate(@Body body: Map<String, String>): ValidateResponse
}
