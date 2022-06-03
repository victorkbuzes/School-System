package com.example.e_commerceapp.presentation.auth

sealed class AuthEvent {
    data class EmailChanged(val email: String):AuthEvent()
    data class  PasswordChanged(val password: String):AuthEvent()
    data class  RepeatedPasswordChange(val repeatedPassword: String): AuthEvent()
    data class  AcceptTerms(val isAccepted: Boolean): AuthEvent()
    object Submit: AuthEvent()
}