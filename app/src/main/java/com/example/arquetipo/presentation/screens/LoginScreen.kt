package com.example.arquetipo.presentation.screens

import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import com.example.arquetipo.presentation.viewmodel.LoginViewModel
import com.example.arquetipo.application.ServiceLocator
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory

@Composable
fun LoginScreen(onRegisterClick: () -> Unit, navController: NavController, appContext: Context) {
    // Provide ViewModel manually via factory
    val vm: LoginViewModel = viewModel(factory = LoginViewModel.provideFactory(appContext))
    val uiState by vm.uiState.collectAsState()

    var apiUrl by remember { mutableStateOf("https://example.com/api/login") }
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Login", style = MaterialTheme.typography.headlineMedium)
        OutlinedTextField(value = uiState.username, onValueChange = { vm.onUsernameChange(it) }, label = { Text("Username") })
        OutlinedTextField(value = uiState.password, onValueChange = { vm.onPasswordChange(it) }, label = { Text("Password") })
        OutlinedTextField(value = apiUrl, onValueChange = { apiUrl = it }, label = { Text("API Base URL (configurable)") })
        Button(onClick = {
            vm.login(apiUrl) { success ->
                if (success) navController.navigate("${'$'}{com.example.arquetipo.presentation.navigation.Routes.HOME}") {
                    popUpTo(com.example.arquetipo.presentation.navigation.Routes.LOGIN) { inclusive = true }
                }
            }
        }) {
            Text("Sign in")
        }
   //     Text("Register a new user", modifier = Modifier.padding(top = 8.dp).clickable { navController.navigate("${'$'}{com.example.arquetipo.presentation.navigation.Routes.REGISTER}") })

        Button(onClick = onRegisterClick) {
            Text("Register a new user")
        }
        if (uiState.error != null) {
            Text("Error: ${'$'}{uiState.error}", color = MaterialTheme.colorScheme.error)
        }
    }
}
