package com.example.arquetipo.presentation.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.composable
import com.example.arquetipo.presentation.screens.LoginScreen
import com.example.arquetipo.presentation.screens.RegisterScreen
import com.example.arquetipo.presentation.screens.HomeScreen

@Composable
fun ArqNavHost(appContext: Context) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Routes.LOGIN) {
        composable(Routes.LOGIN) {
            LoginScreen(
                onRegisterClick = {
                navController.navigate(Routes.REGISTER)
            },navController = navController, appContext = appContext

            )

        }
        composable(Routes.REGISTER) {
            RegisterScreen(navController = navController, appContext = appContext
            )
        }
        composable(Routes.HOME) {
            HomeScreen()
        }
    }
}
