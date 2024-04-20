package com.example.taxeazy.screens

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeScreen(navController)
        }
        composable("registerUser") {
            SignUpScreenUser(navController)
        }
        composable("login") {
            LoginScreen(navController)
        }
        composable("registerCA") {
            SignUpScreenCA(navController)
        }
    }
}
