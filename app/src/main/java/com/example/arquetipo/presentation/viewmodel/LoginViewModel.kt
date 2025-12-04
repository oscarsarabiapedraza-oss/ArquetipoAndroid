package com.example.arquetipo.presentation.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import com.example.arquetipo.application.ServiceLocator
import com.example.arquetipo.domain.usecase.LoginUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class LoginUiState(
    val username: String = "",
    val password: String = "",
    val loading: Boolean = false,
    val error: String? = null
)

class LoginViewModel(private val loginUC: LoginUseCase): ViewModel() {
    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    fun onUsernameChange(value: String) {
        _uiState.value = _uiState.value.copy(username = value)
    }
    fun onPasswordChange(value: String) {
        _uiState.value = _uiState.value.copy(password = value)
    }

    fun login(apiBaseUrl: String, onResult: (Boolean)->Unit) {
        val username = uiState.value.username
        val password = uiState.value.password
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(loading = true, error = null)
            when (val r = loginUC(username, password, apiBaseUrl)) {
                is com.example.arquetipo.core.Result.Success -> {
                    val ok = r.data
                    _uiState.value = _uiState.value.copy(loading = false)
                    onResult(ok)
                }
                is com.example.arquetipo.core.Result.Error -> {
                    _uiState.value = _uiState.value.copy(loading = false, error = r.message)
                    onResult(false)
                }
            }
        }
    }

    companion object {
        fun provideFactory(context: Context): androidx.lifecycle.ViewModelProvider.Factory {
            val repo = ServiceLocator.provideUserRepository(context)
            val loginUC = LoginUseCase(repo)
            return androidx.lifecycle.viewmodel.viewModelFactory {
                initializer {
                    LoginViewModel(loginUC)
                }
            }
        }
    }
}
