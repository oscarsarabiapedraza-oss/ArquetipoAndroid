package com.example.arquetipo.presentation.screens

import android.content.Context
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
import com.example.arquetipo.presentation.viewmodel.RegisterViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun RegisterScreen(navController: NavController, appContext: Context) {
    val vm: RegisterViewModel = viewModel(factory = RegisterViewModel.provideFactory(appContext))
    val uiState by vm.uiState.collectAsState()
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Register", style = MaterialTheme.typography.headlineMedium)
        OutlinedTextField(value = uiState.username, onValueChange = { vm.onUsernameChange(it) }, label = { Text("Username") })
        OutlinedTextField(value = uiState.password, onValueChange = { vm.onPasswordChange(it) }, label = { Text("Password") })
        Button(onClick = {
            vm.register {
                // on success, go back to login
                navController.popBackStack()
            }
        }) {
            Text("Create account (saves in Room/SQLite)")
        }
        if (uiState.successMessage != null) {
            Text("${'$'}{uiState.successMessage}", color = MaterialTheme.colorScheme.primary)
        }
        if (uiState.error != null) {
            Text("Error: ${'$'}{uiState.error}", color = MaterialTheme.colorScheme.error)
        }
    }
}
