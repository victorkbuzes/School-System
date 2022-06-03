package com.example.e_commerceapp.presentation.products.productsList

sealed class ProductsEvents{
    object  Refresh : ProductsEvents()
    data class  OnSearchQueryChange( val query: String): ProductsEvents()
}
