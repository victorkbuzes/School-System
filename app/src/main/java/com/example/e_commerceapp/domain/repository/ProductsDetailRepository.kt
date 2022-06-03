package com.example.e_commerceapp.domain.repository

import com.example.e_commerceapp.domain.model.ProductsInfo
import com.example.e_commerceapp.utils.Resource
import kotlinx.coroutines.flow.Flow

interface ProductsDetailRepository {
    suspend fun getProductsDetail():Flow<Resource<List<ProductsInfo>>>
}