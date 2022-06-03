package com.example.e_commerceapp.presentation.products.productsList


import com.example.e_commerceapp.domain.model.ProductsListing


data class ProductsState(
    val products: List<ProductsListing> = emptyList(),
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val searchQuery: String = ""
)
