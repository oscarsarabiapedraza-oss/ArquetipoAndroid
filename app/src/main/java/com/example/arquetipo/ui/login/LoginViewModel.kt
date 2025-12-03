package com.example.arquetipo.ui.login

import android.app.Application
import android.util.Patterns
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.MutableLiveData
import com.example.arquetipo.data.UserRepository
import kotlinx.coroutines.launch

class LoginViewModel(application: Application): AndroidViewModel(application) {
    val status = MutableLiveData<String>()
    val loading = MutableLiveData<Boolean>(false)

    private val repo = UserRepository(application, "https://example.com/api/")

    fun login(email: String, password: String, onSuccess: ()->Unit) {
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            status.value = "Email inválido"
            return
        }
        if (password.length < 6) {
            status.value = "La contraseña debe tener al menos 6 caracteres"
            return
        }

        loading.value = true
        viewModelScope.launch {
            try {
                val ok = repo.validateRemote(email, password)
                if (ok) {
                    status.value = "Login exitoso"
                    onSuccess()
                } else {
                    status.value = "Credenciales incorrectas"
                }
            } catch (e: Exception) {
                status.value = "Error: ${e.message}"
            } finally {
                loading.value = false
            }
        }
    }
}
