package com.example.arquetipo.application

import android.content.Context
import com.example.arquetipo.infrastructure.db.AppDatabase
import com.example.arquetipo.infrastructure.repository.UserRepositoryImpl
import com.example.arquetipo.domain.repository.UserRepository

object ServiceLocator {
    fun provideUserRepository(context: Context): UserRepository {
        val db = AppDatabase.getInstance(context)
        return UserRepositoryImpl(db.userDao())
    }
}
