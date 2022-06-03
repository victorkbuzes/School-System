package com.example.e_commerceapp.utils

import com.example.e_commerceapp.domain.model.ProductsListing
import kotlinx.coroutines.flow.Flow

sealed class Resource<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T?): Resource<T>(data)
    class Error<T>(message: String, data: T? = null): Resource<T>(data, message)
    class Loading<T>(val isLoading: Boolean = true): Resource<T>(null)
}

