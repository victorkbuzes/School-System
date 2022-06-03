package com.example.e_commerceapp.presentation.auth.register

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.e_commerceapp.presentation.auth.AuthEvent
import com.example.e_commerceapp.presentation.auth.login.LoginScreen
import com.example.e_commerceapp.presentation.components.StandardTextField
import kotlinx.coroutines.flow.collect


@Composable
fun RegisterScreen(){
    val viewModel = viewModel<RegisterViewModel>()
    val state = viewModel.state
    val context = LocalContext.current
    LaunchedEffect(key1 = context){
        viewModel.validationEvents.collect { event ->
            when(event){
                is RegisterViewModel.ValidationEvent.Success ->{
                    Toast.makeText(
                        context,
                        "Registration successful",
                        Toast.LENGTH_LONG
                    ).show()
                }

            }

        }
    }

    Column(
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp)
    ) {
        TextField(
            value = state.email,
            onValueChange = {
            viewModel.onEvent(AuthEvent.EmailChanged(it))
        },
            isError = state.emailError != null,
            modifier = Modifier.fillMaxWidth(),
            placeholder = {
                Text(text = "Example@gmail.com")
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email
            )
        

            )
        if (state.emailError != null){
            Text(text = state.emailError,
                color = MaterialTheme.colors.error,
                modifier = Modifier.align(Alignment.End)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = state.password,
            onValueChange = {
                viewModel.onEvent(AuthEvent.PasswordChanged(it))
            },
            isError = state.passwordError != null,
            modifier = Modifier.fillMaxWidth(),
            placeholder = {
                Text(text = "password")
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password
            ),
            visualTransformation = PasswordVisualTransformation()


        )
        if (state.passwordError != null){
            Text(text = state.passwordError,
                color = MaterialTheme.colors.error,
                modifier = Modifier.align(Alignment.End)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = state.repeatedPassword,
            onValueChange = {
                viewModel.onEvent(AuthEvent.RepeatedPasswordChange(it))
            },
            isError = state.repeatedPassword != null,
            modifier = Modifier.fillMaxWidth(),
            placeholder = {
                Text(text = " repeated password")
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password
            ),
            visualTransformation = PasswordVisualTransformation()


        )
        if (state.repeatedPasswordError != null){
            Text(text = state.repeatedPasswordError,
                color = MaterialTheme.colors.error,
                modifier = Modifier.align(Alignment.End)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Checkbox(
                checked = state.acceptTerms,
                onCheckedChange = {
                viewModel.onEvent(AuthEvent.AcceptTerms(it))
            }
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Accept Terms")
        }
        if (state.termsError != null) {
            Text(
                text = state.termsError,
                color = MaterialTheme.colors.error,
            )
        }
        Button(
            onClick = {
                viewModel.onEvent(AuthEvent.Submit)
            },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text(text = "Submit")
        }

    }
}