package com.example.e_commerceapp.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector


const val ROOT_ROUTE = "root"


const val DETAIL_AUGMENT_KEY = "id"
const val DETAIL_AUGMENT_KEY2 = "name"


sealed class Screen(
    val route: String,
    val title: String? = null,
    val icon: ImageVector? = null,
    val badgeCount: Int = 0
) {

    object Welcome : Screen(
        route = "welcome"
    )
    object Main : Screen(
        route = "main",

    )

    object Login : Screen(
        route = "login"
    )
    object Register : Screen(
        route = "register"

    )
    object ProductList : Screen(
        route = "product_list_screen"
    )
    object ProductInfo : Screen(
        route = "product_info_screen"
    )

    object Home : Screen(
        route = "home",
        title = "Home",
        icon = Icons.Default.Home

    )

    object Search : Screen(
        route = "search",
        title = "Search",
        icon = Icons.Default.Search

    )


    object Profile : Screen(
        route = "profile",
        title = "Profile",
        icon = Icons.Default.Person
    )
    object Cart : Screen(
        route = "cart",
        title = "cart",
        icon = Icons.Default.ShoppingCart,

        )


    object Settings : Screen(
        route = "settings",
        title = "Settings",
        icon = Icons.Default.Settings
    )
}




