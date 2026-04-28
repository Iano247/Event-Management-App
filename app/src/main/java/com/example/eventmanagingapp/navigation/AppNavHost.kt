package com.example.eventmanagingapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.eventmanagingapp.models.Organizer
import com.example.eventmanagingapp.ui.theme.screens.cart.CartScreen
import com.example.eventmanagingapp.ui.theme.screens.home.HomeScreen
import com.example.eventmanagingapp.ui.theme.screens.login.LoginScreen
import com.example.eventmanagingapp.ui.theme.screens.register.RegisterScreen

@Composable
fun EventManagerNavHost() {
    val navController = rememberNavController()
    val startDestination = "register"

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable("register") {
            RegisterScreen(
                onRegistrationSuccess = {
                    navController.navigate("login") {
                        popUpTo("register") { inclusive = true }
                    }
                },
                onLoginClick = {
                    navController.navigate("login") {
                        popUpTo("register") { inclusive = true }
                    }
                }
            )
        }
        composable("login") {
            LoginScreen(
                onLoginSuccess = { organizer: Organizer ->
                    navController.navigate("home") {
                        popUpTo(0)
                    }
                }
            )
        }
        composable("home") { HomeScreen(navController) }
        composable("cart") { CartScreen(navController) }
    }
}
