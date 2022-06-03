package com.example.e_commerceapp.data.remote.dto.productsList

import com.example.e_commerceapp.domain.model.Rating

data class RatingDto(
    val count: Int,
    val rate: Double
){
    fun toRating():Rating{
        return Rating(
            count = count,
            rate = rate
        )

    }
}
