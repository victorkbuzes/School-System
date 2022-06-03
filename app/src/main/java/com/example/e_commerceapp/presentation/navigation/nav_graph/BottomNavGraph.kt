package com.example.e_commerceapp.presentation.navigation.nav_graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.e_commerceapp.presentation.cart.CartScreen
import com.example.e_commerceapp.presentation.home.HomeScreen
import com.example.e_commerceapp.presentation.navigation.Screen
import com.example.e_commerceapp.presentation.products.productsList.ProductsListScreen
import com.example.e_commerceapp.presentation.profile.ProfileScreen
import com.example.e_commerceapp.presentation.search.SearchScreen
import com.example.e_commerceapp.presentation.settings.SettingsScreen

fun NavGraphBuilder.bottomNavGraph(){
    composable(route = Screen.Home.route) {
        HomeScreen()
    }

    composable(route = Screen.Search.route) {
        SearchScreen()
    }
    composable(route = Screen.Profile.route) {
        ProfileScreen()
    }
    composable(route = Screen.Cart.route) {
        CartScreen()
    }
    composable(route = Screen.Settings.route) {
        SettingsScreen()
    }

}