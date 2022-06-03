import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.e_commerceapp.R
import com.example.e_commerceapp.presentation.auth.AuthEvent
import com.example.e_commerceapp.presentation.auth.register.RegisterViewModel
import com.example.e_commerceapp.ui.theme.Purple500
import com.example.e_commerceapp.ui.theme.WhiteBackground


@Preview
@Composable
fun ComposablePreview() {
    BioMetricScreen()
}
@Composable
fun BioMetricScreen() {
    val registerImage = painterResource(id = R.drawable.register_page)
    val passwordVisibility = remember { mutableStateOf(false) }
    val confirmPasswordVisibility = remember { mutableStateOf(false) }

    val viewModel = viewModel<RegisterViewModel>()
    val state = viewModel.state

    val context = LocalContext.current
    val emailVal = remember { mutableStateOf("") }
    val passwordVal = remember { mutableStateOf("") }

    val checked = remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {


        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.login_image),
                contentDescription = "Login image",
                modifier = Modifier
                    .width(250.dp)
                    .height(250.dp),
                contentScale = ContentScale.Fit
            )

            Spacer(modifier = Modifier.padding(10.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 15.dp, end = 15.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(WhiteBackground),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Sign In",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.padding(15.dp))

                OutlinedTextField(
                    value = emailVal.value,
                    onValueChange = { emailVal.value = it },
                    label = { Text(text = "Email Address") },
                    placeholder = { Text(text = "Email Address") },
                    leadingIcon = { Icon(Icons.Filled.Email, contentDescription = "Email") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(0.8f)
                )

                OutlinedTextField(
                    value = passwordVal.value,
                    onValueChange = { passwordVal.value = it },
                    trailingIcon = {
                        IconButton(
                            onClick = {
                                passwordVisibility.value = !passwordVisibility.value
                            }
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_password_eye),
                                contentDescription = "password eye",
                                tint = if (passwordVisibility.value) Purple500 else Color.Gray
                            )
                        }
                    },
                    label = { Text(text = "Password") },
                    placeholder = { Text(text = "Password") },
                    leadingIcon = {  Icon(Icons.Filled.Lock, contentDescription = "Password") },
                    singleLine = true,
                    visualTransformation = if (passwordVisibility.value)
                        VisualTransformation.None else PasswordVisualTransformation(),
                    modifier = Modifier.fillMaxWidth(0.8f)
                )

                Spacer(modifier = Modifier.padding(10.dp))

                Button(
                    onClick = {
                        if (emailVal.value.isEmpty()) {
                            Toast.makeText(context, "Please enter email address!", Toast.LENGTH_SHORT).show()
                        } else if (passwordVal.value.isEmpty()) {
                            Toast.makeText(context, "Please enter password!", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(context, "Logged Successfully!", Toast.LENGTH_SHORT).show()
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .height(50.dp)
                ) {
                    Text(text = "Sign In", fontSize = 20.sp)
                }

                Spacer(modifier = Modifier.padding(20.dp))

                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Checkbox(
                        checked = state.acceptTerms,
                        onCheckedChange = {
                            viewModel.onEvent(AuthEvent.AcceptTerms(it))
                        }
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = "Accept terms")
                }

                Spacer(modifier = Modifier.padding(10.dp))
            }
        }
    }
}
