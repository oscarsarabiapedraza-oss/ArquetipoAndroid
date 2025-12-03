package com.example.arquetipo.data

import android.content.Context
import com.example.arquetipo.network.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class UserRepository(context: Context, baseUrl: String) {
    private val db = AppDatabase.getInstance(context)
    private val userDao = db.userDao()

    private val api: ApiService = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ApiService::class.java)

    suspend fun registerUser(user: User): Long = withContext(Dispatchers.IO) {
        userDao.insert(user)
    }

    suspend fun findLocalByEmail(email: String) = withContext(Dispatchers.IO) {
        userDao.findByEmail(email)
    }

    suspend fun validateRemote(email: String, password: String): Boolean = withContext(Dispatchers.IO) {
        // calls an API endpoint that returns JSON { "success": true } or similar
        val resp = api.validate(mapOf("email" to email, "password" to password))
        return@withContext resp.success
    }
}
