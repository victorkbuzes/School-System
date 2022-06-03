package com.example.e_commerceapp.presentation.auth.register

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.e_commerceapp.domain.usecases.validation.ValidateEmail
import com.example.e_commerceapp.domain.usecases.validation.ValidatePassword
import com.example.e_commerceapp.domain.usecases.validation.ValidateRepeatedPassword
import com.example.e_commerceapp.domain.usecases.validation.ValidateTerms
import com.example.e_commerceapp.presentation.auth.AuthEvent
import com.example.e_commerceapp.presentation.auth.AuthState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch


class RegisterViewModel(
    private  val validateEmail: ValidateEmail = ValidateEmail(),
    private  val validatePassword: ValidatePassword = ValidatePassword(),
    private  val validateRepeatedPassword: ValidateRepeatedPassword = ValidateRepeatedPassword(),
    private val validateTerms: ValidateTerms = ValidateTerms()
) : ViewModel(){
    var state by mutableStateOf(AuthState())
    private  val validationEventChannel = Channel<ValidationEvent>()
    val validationEvents = validationEventChannel.receiveAsFlow()
    fun onEvent(event: AuthEvent){
        when(event){
            is AuthEvent.EmailChanged -> {
                state = state.copy(email = event.email)

            }
            is AuthEvent.PasswordChanged -> {
                state = state.copy(password = event.password)

            }
            is AuthEvent.RepeatedPasswordChange -> {
                state = state.copy(repeatedPassword = event.repeatedPassword)

            }
            is AuthEvent.AcceptTerms -> {
                state = state.copy(acceptTerms = event.isAccepted)

            }
            is AuthEvent.Submit -> {
                submitData()
            }
        }

    }

    private fun submitData() {
        val emailResult = validateEmail.execute(state.email)
        val passwordResult = validatePassword.execute(state.password)
        val repeatPasswordResult = validateRepeatedPassword.execute(
            state.password, state.repeatedPassword)
        val termsResult = validateTerms.execute(state.acceptTerms)

        val hasError = listOf(
            emailResult,
            passwordResult,
            repeatPasswordResult,
            termsResult
        ).any { !it.successful }
        if (hasError){
            state = state.copy(
                emailError = emailResult.errorMessage,
                passwordError = passwordResult.errorMessage,
                repeatedPasswordError = repeatPasswordResult.errorMessage,
                termsError = termsResult.errorMessage

            )
            return
        }
        viewModelScope.launch {
            validationEventChannel.send(ValidationEvent.Success)

        }


    }
    sealed class ValidationEvent{
        object Success: ValidationEvent()
    }



}

