package com.example.e_commerceapp.data.repository

import com.example.e_commerceapp.data.remote.api.ProductsApi
import com.example.e_commerceapp.domain.model.ProductsListing
import com.example.e_commerceapp.domain.repository.ProductsListRepository

import com.example.e_commerceapp.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class ProductsListRepositoryImpl @Inject constructor(
    private  val api: ProductsApi
):ProductsListRepository {
    override suspend fun getProductsList(): Flow<Resource<List<ProductsListing>>> {
        return flow {
            emit(Resource.Loading(true))


            val productsList = try {
                api.getProductsList()

            } catch (e:IOException){
                e.printStackTrace()
                emit(Resource.Error("Could not load data"))
                null

            } catch (e:HttpException){
                e.printStackTrace()
                emit(Resource.Error("Could not load data"))
                null

            }
        }


    }


}