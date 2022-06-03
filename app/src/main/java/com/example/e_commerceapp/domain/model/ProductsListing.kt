package com.example.e_commerceapp.domain.model

import com.example.e_commerceapp.data.remote.dto.productsList.RatingDto

data class ProductsListing(
    val description: String,
    val id: Int,
    val image: String,
    val price: Double,
    val title: String
)
