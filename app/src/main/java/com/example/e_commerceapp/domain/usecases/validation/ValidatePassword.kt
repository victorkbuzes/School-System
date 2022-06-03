package com.example.e_commerceapp.domain.usecases.validation


class ValidatePassword {
    fun execute(password: String): ValidationResult {
        if (password.length < 6){
            return ValidationResult(
                successful = false,
                errorMessage = "Password needs to consist of at least 8 characters"
            )
        }
        var consistLettersAndDigits = password.any { it.isDigit() } && password.any { it.isLetter() }
        if (!consistLettersAndDigits){
            return ValidationResult(
                successful = false,
                errorMessage = "password needs to contain at  least a digit and letter"
            )
        }
        return ValidationResult(
            successful = true
        )
    }
}