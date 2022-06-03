package com.example.e_commerceapp.data.remote.dto

import com.example.e_commerceapp.data.remote.dto.productsList.RatingDto

data class ProductsInfoDto(
    val category: String,
    val description: String,
    val id: Int,
    val image: String,
    val price: Double,
    val rating: RatingDto,
    val title: String
)
