package com.example.e_commerceapp

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.example.e_commerceapp.presentation.navigation.nav_graph.SetupNavGraph
import com.example.e_commerceapp.presentation.welcome.SplashViewModel
import com.example.e_commerceapp.ui.theme.ECommerceAppTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var splashViewModel: SplashViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
        installSplashScreen().setKeepOnScreenCondition{
            !splashViewModel.isLoading.value
        }


        setContent {
            ECommerceAppTheme {
                val screen by splashViewModel.startDestination
                val navController = rememberNavController()
                SetupNavGraph(navController = navController, startDestination = screen)

            }
        }
    }
}

