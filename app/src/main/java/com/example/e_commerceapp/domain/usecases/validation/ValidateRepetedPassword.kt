package com.example.e_commerceapp.domain.usecases.validation

class ValidateRepeatedPassword {
    fun execute(password: String, repeatedPassword: String): ValidationResult {
        if (password != repeatedPassword){
            return ValidationResult(
                successful = false,
                errorMessage = "Passwords do not match"
            )
        }
        return ValidationResult(
            successful = true
        )
    }
}