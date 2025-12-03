package com.example.arquetipo.ui.register

import android.app.Application
import android.util.Patterns
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.arquetipo.data.User
import com.example.arquetipo.data.UserRepository
import kotlinx.coroutines.launch

class RegisterViewModel(application: Application): AndroidViewModel(application) {
    val status = MutableLiveData<String>()
    private val repo = UserRepository(application, "https://example.com/api/")

    fun register(name: String, email: String, password: String, onDone: ()->Unit) {
        if (name.isBlank()) {
            status.value = "El nombre no puede estar vacío"
            return
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            status.value = "Email inválido"
            return
        }
        if (password.length < 6) {
            status.value = "La contraseña debe tener al menos 6 caracteres"
            return
        }

        viewModelScope.launch {
            try {
                val id = repo.registerUser(User(name = name, email = email, password = password))
                status.value = "Registrado con id $id"
                onDone()
            } catch (e: Exception) {
                status.value = "Error: ${e.message}"
            }
        }
    }
}
