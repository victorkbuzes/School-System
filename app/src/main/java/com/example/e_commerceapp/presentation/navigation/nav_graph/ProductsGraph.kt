package com.example.e_commerceapp.presentation.navigation.nav_graph

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.*
import androidx.navigation.compose.composable

import com.example.e_commerceapp.presentation.navigation.Screen
import com.example.e_commerceapp.presentation.products.productsInfo.ProductInfoScreen
import com.example.e_commerceapp.presentation.products.productsList.ProductsListScreen

const val DETAIL_AUGMENT = "productName"
const val PRODUCT_ROUTE = "product"
fun NavGraphBuilder.productsNavGraph(
    navController: NavController
){
    navigation(startDestination = Screen.ProductList.route, route = PRODUCT_ROUTE){
        composable(route = Screen.ProductList.route){
            ProductsListScreen()
        }
        composable(route = Screen.ProductInfo.route,
            arguments = listOf( navArgument(DETAIL_AUGMENT){
                type = NavType.StringType
            })
        ){
            val productName = remember {
                it.arguments?.getString(DETAIL_AUGMENT)

            }
            ProductInfoScreen()
        }
    }

}