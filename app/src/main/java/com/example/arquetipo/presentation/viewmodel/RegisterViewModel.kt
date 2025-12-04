package com.example.arquetipo.presentation.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.arquetipo.application.ServiceLocator
import com.example.arquetipo.domain.usecase.RegisterUserUseCase
import com.example.arquetipo.domain.model.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch



import androidx.lifecycle.viewmodel.initializer



data class RegisterUiState(
    val username: String = "",
    val password: String = "",
    val successMessage: String? = null,
    val error: String? = null
)

class RegisterViewModel(private val registerUC: RegisterUserUseCase): ViewModel() {
    private val _uiState = MutableStateFlow(RegisterUiState())
    val uiState: StateFlow<RegisterUiState> = _uiState.asStateFlow()

    fun onUsernameChange(value: String) { _uiState.value = _uiState.value.copy(username = value) }
    fun onPasswordChange(value: String) { _uiState.value = _uiState.value.copy(password = value) }

    fun register(onComplete: ()->Unit) {
        val username = uiState.value.username
        val password = uiState.value.password
        viewModelScope.launch {
            val res = registerUC(User(username = username, password = password))
            when (res) {
                is com.example.arquetipo.core.Result.Success -> {
                    _uiState.value = _uiState.value.copy(successMessage = "User created (id=${'$'}{res.data})", error = null)
                    onComplete()
                }
                is com.example.arquetipo.core.Result.Error -> {
                    _uiState.value = _uiState.value.copy(error = res.message)
                }
            }
        }
    }

    companion object {
        fun provideFactory(context: Context): androidx.lifecycle.ViewModelProvider.Factory {
            val repo = ServiceLocator.provideUserRepository(context)
            val regUC = RegisterUserUseCase(repo)
            return androidx.lifecycle.viewmodel.viewModelFactory {
                initializer {
                    RegisterViewModel(regUC)
                }
            }
        }
    }
}
