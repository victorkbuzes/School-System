package com.example.e_commerceapp.domain.usecases.validation

data class ValidationResult(
    val successful: Boolean,
    val errorMessage: String? = null
)
