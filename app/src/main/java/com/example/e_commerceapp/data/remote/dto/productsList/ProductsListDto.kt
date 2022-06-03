package com.example.e_commerceapp.data.remote.dto.productsList

import com.example.e_commerceapp.domain.model.ProductsListing

data class ProductsListDto(

    val description: String,
    val id: Int,
    val image: String,
    val price: Double,
    val title: String
){
    fun  toProducts():ProductsListing{
        return  ProductsListing(
            description = description,
            id = id,
            image = image,
            price = price,
            title = title
        )


    }
}