package com.example.e_commerceapp.di

import android.content.Context
import com.example.e_commerceapp.constants.Constants
import com.example.e_commerceapp.data.remote.api.ProductsApi
import com.example.e_commerceapp.data.repository.DataStoreRepository
import com.example.e_commerceapp.data.repository.ProductsListRepositoryImpl
import com.example.e_commerceapp.domain.repository.ProductsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideProductsApi(): ProductsApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ProductsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideProductsRepository(api: ProductsApi): ProductsRepository{
        return ProductsListRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideDataStoreRepository(
        @ApplicationContext context: Context
    ) = DataStoreRepository(context = context)

}