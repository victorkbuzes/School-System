package com.example.e_commerceapp.presentation.navigation.nav_graph

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.e_commerceapp.presentation.auth.login.LoginScreen
import com.example.e_commerceapp.presentation.auth.register.RegisterScreen

import com.example.e_commerceapp.presentation.navigation.Screen

const val AUTHENTICATION_ROUTE = "authentication"
fun NavGraphBuilder.authNavGraph(
    navController: NavController
){

    navigation(startDestination = Screen.Login.route, route = AUTHENTICATION_ROUTE){
        composable(route = Screen.Login.route){
            LoginScreen()
        }
        composable(route = Screen.Register.route){
            RegisterScreen()
        }

    }

}