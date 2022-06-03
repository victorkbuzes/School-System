package com.example.e_commerceapp.domain.repository

import com.example.e_commerceapp.domain.model.ProductsListing
import com.example.e_commerceapp.utils.Resource
import kotlinx.coroutines.flow.Flow

interface ProductsListRepository {
    suspend fun getProductsList(): Flow<Resource<List<ProductsListing>>>
}