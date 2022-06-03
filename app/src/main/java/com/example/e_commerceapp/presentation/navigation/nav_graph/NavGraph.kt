package com.example.e_commerceapp.presentation.navigation.nav_graph

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.e_commerceapp.presentation.main.MainScreen
import com.example.e_commerceapp.presentation.navigation.ROOT_ROUTE
import com.example.e_commerceapp.presentation.navigation.Screen
import com.example.e_commerceapp.presentation.welcome.WelcomeScreen


@Composable
fun SetupNavGraph(
    navController: NavHostController,
    startDestination: String,

) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        route = ROOT_ROUTE
    ) {

        composable(route = Screen.Welcome.route){
            WelcomeScreen(navController = navController)

        }
        composable(route = Screen.Main.route) {
            MainScreen()
        }

        bottomNavGraph()
        authNavGraph(navController = navController)
        productsNavGraph(navController = navController)


    }
}
