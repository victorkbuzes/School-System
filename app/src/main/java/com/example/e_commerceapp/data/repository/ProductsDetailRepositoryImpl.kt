package com.example.e_commerceapp.data.repository

import com.example.e_commerceapp.domain.model.ProductsInfo
import com.example.e_commerceapp.domain.repository.ProductsDetailRepository
import com.example.e_commerceapp.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ProductsDetailRepositoryImpl:ProductsDetailRepository {
    override suspend fun getProductsDetail(): Flow<Resource<List<ProductsInfo>>> {
        return  flow {
            emit(Resource.Loading(true))

            val productsDetail = try {

            }
        }
    }

}