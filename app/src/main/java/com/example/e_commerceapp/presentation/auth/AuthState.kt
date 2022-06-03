package com.example.e_commerceapp.presentation.auth

data class AuthState(
    val email: String = "",
    val emailError: String? = null,
    val password: String = "",
    val passwordError:String? = null,
    val repeatedPassword: String  = "",
    val repeatedPasswordError: String? = null,
    val acceptTerms: Boolean = false,
    val termsError: String? = null

)
