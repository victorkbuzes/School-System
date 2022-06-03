package com.example.e_commerceapp.presentation.auth.register

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

import com.example.e_commerceapp.R
import com.example.e_commerceapp.presentation.auth.AuthEvent
import com.example.e_commerceapp.ui.theme.PrimaryColor
import com.example.e_commerceapp.ui.theme.WhiteBackground
import kotlinx.coroutines.flow.collect


@Preview
@Composable
fun ComposablePreview() {
    RegisterPage()
}
@Composable
fun RegisterPage() {

    val registerImage = painterResource(id = R.drawable.register_page)
    val passwordVisibility = remember { mutableStateOf(false) }
    val confirmPasswordVisibility = remember { mutableStateOf(false) }

    val viewModel = viewModel<RegisterViewModel>()
    val state = viewModel.state
    val context = LocalContext.current
    LaunchedEffect(key1 = context) {
        viewModel.validationEvents.collect { event ->
            when (event) {
                is RegisterViewModel.ValidationEvent.Success -> {
                    Toast.makeText(
                        context,
                        "Registration successful",
                        Toast.LENGTH_LONG
                    ).show()
                }

            }

        }
    }
    //
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White), contentAlignment = Alignment.BottomCenter
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White),
            contentAlignment = Alignment.TopCenter
        ) {
            Image(painter = registerImage, contentDescription = "Register")

        }
        Column(

            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.60f)
                .clip(RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp))
                .background(WhiteBackground)
                .padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,

            ) {
            Text(
                text = "Sign Up",
                style = TextStyle(

                    fontWeight = FontWeight.Bold,
                    letterSpacing = 2.sp,
                ),
                fontSize = 20.sp
            )
            Spacer(modifier = Modifier.padding(20.dp))
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(32.dp),
                verticalArrangement = Arrangement.Center


            ) {
                OutlinedTextField(
                    value = state.email,
                    onValueChange = { viewModel.onEvent(AuthEvent.EmailChanged(it)) },
                    isError = state.emailError != null,
                    label = { Text(text = "Email Address") },
                    placeholder = { Text(text = "Email ") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(0.9f),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
                )
                if (state.emailError != null) {
                    Text(
                        text = state.emailError,
                        color = MaterialTheme.colors.error,
                        modifier = Modifier.align(Alignment.End)
                    )
                }

                OutlinedTextField(
                    value = state.password,
                    onValueChange = {
                        viewModel.onEvent(AuthEvent.PasswordChanged(it))

                    },
                    isError = state.passwordError != null,
                    label = { Text(text = "password") },
                    placeholder = { Text(text = "password") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(0.9f),
                    trailingIcon = {
                        IconButton(onClick = {
                            passwordVisibility.value = !passwordVisibility.value
                        }) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_password_eye),
                                contentDescription = "eye",
                                tint = if (passwordVisibility.value) PrimaryColor else Color.Gray
                            )
                        }
                    },
                    visualTransformation = if (passwordVisibility.value) VisualTransformation.None
                    else PasswordVisualTransformation(),
                )
                if (state.passwordError != null) {
                    Text(
                        text = state.passwordError,
                        color = MaterialTheme.colors.error,
                        modifier = Modifier.align(Alignment.End)
                    )

                }

                OutlinedTextField(
                    value = state.repeatedPassword,
                    onValueChange = { viewModel.onEvent(AuthEvent.RepeatedPasswordChange(it)) },
                    isError = state.repeatedPasswordError != null,
                    label = { Text(text = "Confirm Password") },
                    placeholder = { Text(text = "Confirm Password") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(0.9f),
                    trailingIcon = {
                        IconButton(onClick = {
                            confirmPasswordVisibility.value = !confirmPasswordVisibility.value
                        }) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_password_eye),
                                contentDescription = "eye",
                                tint = if (passwordVisibility.value) PrimaryColor else Color.Gray
                            )
                        }
                    },
                    visualTransformation = if (confirmPasswordVisibility.value) VisualTransformation.None
                    else PasswordVisualTransformation()
                )
                Spacer(modifier = Modifier.height(16.dp))
                Row() {
                    Checkbox(
                        checked = state.acceptTerms,
                        onCheckedChange = {
                            viewModel.onEvent(AuthEvent.AcceptTerms(it))
                        },
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = "Accept Terms")
                }
                if (state.termsError != null) {
                    Text(
                        text = state.termsError,
                        color = MaterialTheme.colors.error,
                    )
                }


                Button(
                    onClick = { viewModel.onEvent(AuthEvent.Submit) }, modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .height(50.dp)
                ) {
                    Text(text = "SigUp", fontSize = 20.sp)
                }
                Spacer(modifier = Modifier.padding(20.dp))
                Text(
                    text = "Login Instead",
                    modifier = Modifier.clickable(onClick = {
//                        navController.navigate("login_page"){
//                            popUpTo = navController.graph.startDestination
//                            launchSingleTop = true
//                        }
                    })
                )


            }
        }
    }
}

