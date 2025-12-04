package com.example.arquetipo.infrastructure.repository

import com.example.arquetipo.domain.repository.UserRepository
import com.example.arquetipo.domain.model.User
import com.example.arquetipo.core.Result
import com.example.arquetipo.infrastructure.db.UserDao
import com.example.arquetipo.infrastructure.db.UserEntity
import com.example.arquetipo.infrastructure.network.AuthApi
import com.example.arquetipo.infrastructure.network.AuthRequest
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

class UserRepositoryImpl(private val userDao: UserDao): UserRepository {

    override suspend fun registerUser(user: User): Result<Long> {
        val entity = UserEntity(username = user.username, password = user.password)
        return try {
            val id = userDao.insert(entity)
            Result.Success(id)
        } catch (e: Exception) {
            Result.Error(e.localizedMessage)
        }
    }

    override suspend fun getUserByUsername(username: String): User? {
        val e = userDao.findByUsername(username) ?: return null
        return User(e.id, e.username, e.password)
    }

    override suspend fun loginRemote(username: String, password: String, apiBaseUrl: String): Result<Boolean> {
        return try {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BASIC
            val client = OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .addInterceptor(logging)
                .build()

            val retrofit = Retrofit.Builder()
                .baseUrl(apiBaseUrl) // base, but we'll use full @Url in call
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val api = retrofit.create(AuthApi::class.java)
            val response = api.login(apiBaseUrl, AuthRequest(username, password))
            if (response.isSuccessful) {
                val body = response.body()
                Result.Success(body?.success == true)
            } else {
                Result.Error("HTTP ${'$'}{response.code()}: ${'$'}{response.message()}")
            }
        } catch (e: Exception) {
            Result.Error(e.localizedMessage)
        }
    }
}
