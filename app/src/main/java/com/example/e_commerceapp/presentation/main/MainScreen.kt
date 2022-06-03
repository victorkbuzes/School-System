package com.example.e_commerceapp.presentation.main

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.e_commerceapp.presentation.navigation.nav_graph.SetupNavGraph
import com.example.e_commerceapp.presentation.navigation.Screen

@Composable
fun MainScreen(

) {

    val navController = rememberNavController()
    Scaffold(

        bottomBar = { BottomNavBar(navController = navController) }
    ) {
        SetupNavGraph(navController = navController, startDestination = Screen.Home.route)
    }
}

@Composable
fun BottomNavBar(navController: NavHostController,
) {
    val screens = listOf(
        Screen.Home,
        Screen.Profile,
        Screen.Settings,
        Screen.Cart
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    BottomNavigation {
        screens.forEach { screen ->
            AddItem(
                screen = screen,
                currentDestination = currentDestination,
                navController = navController
            )
        }
    }
}

@Composable
fun RowScope.AddItem(
    screen: Screen,
    currentDestination: NavDestination?,
    navController: NavHostController
) {
    BottomNavigationItem(
        label = {
            Text(text = screen.title.toString())
        },
        icon = {
            Icon(
                imageVector = screen.icon!!,
                contentDescription = "Navigation Icon"
            )
        },
        selected = currentDestination?.hierarchy?.any {
            it.route == screen.route
        } == true,
        unselectedContentColor = LocalContentColor.current.copy(alpha = ContentAlpha.disabled),
        onClick = {
            navController.navigate(screen.route) {
                popUpTo(navController.graph.findStartDestination().id)
                launchSingleTop = true
            }
        }
    )
}

