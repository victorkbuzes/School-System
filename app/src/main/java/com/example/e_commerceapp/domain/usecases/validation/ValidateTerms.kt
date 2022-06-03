package com.example.e_commerceapp.domain.usecases.validation


class ValidateTerms {
    fun execute(acceptedTerms: Boolean): ValidationResult {
        if (!acceptedTerms){
            return ValidationResult(
                successful = false,
                errorMessage = "Please accept the terms to proceed"
            )
        }

        return ValidationResult(
            successful = true
        )
    }
}