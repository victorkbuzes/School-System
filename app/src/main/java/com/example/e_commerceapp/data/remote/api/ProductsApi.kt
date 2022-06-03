package com.example.e_commerceapp.data.remote.api

import com.example.e_commerceapp.data.remote.dto.ProductsInfoDto
import com.example.e_commerceapp.data.remote.dto.productsList.ProductsListDto
import com.example.e_commerceapp.presentation.navigation.Screen
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import javax.inject.Singleton



@Singleton
interface ProductsApi {
    @GET("/products")
    suspend fun  getProductsList(): Response<List<ProductsListDto>>

    @GET("/products/{id}")
    suspend fun  getProductInfo(
        @Path("id") id: Int
    ): Response<List<ProductsInfoDto>>
}
