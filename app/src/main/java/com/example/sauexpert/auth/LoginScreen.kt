package com.example.sauexpert.auth

import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.sauexpert.R
import com.example.sauexpert.signup_doctor.MessageCard
import com.example.sauexpert.signup_patient.AuthItem
import com.example.sauexpert.signup_patient.AuthToolbar
import com.example.sauexpert.ui.theme.Red435B
import com.example.sauexpert.ui.theme.SauExpertTheme
import com.example.sauexpert.ui.theme.SuraceF9
import com.example.sauexpert.widgets.compose.MainButtonM


@ExperimentalComposeUiApi
@Composable
fun LoginScreen() {
    val context = LocalContext.current
    val email = remember { mutableStateOf(TextFieldValue()) }
    val password = remember { mutableStateOf(TextFieldValue()) }
    val errorState = remember { mutableStateOf(false) }
    val passwordErrorState = remember { mutableStateOf(false) }
    //SauExpertTheme() {
        Column(
            Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
        ) {
            AuthToolbar(text = stringResource(id = R.string.sign_in))
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color.White)
                    .padding(16.dp)
            ) {
                MessageCard(stringResource(id = R.string.to_log_in_use_your_credentials___))
                AuthItem(
                    textTitle = stringResource(id = R.string.email),
                    name = email,
                    nameErrorState = passwordErrorState,
                    placeholder = stringResource(id = R.string.enter_email)
                )
                val passwordVisibility = remember { mutableStateOf(true) }
                Spacer(modifier = Modifier.size(16.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = stringResource(id = R.string.password),
                        style = MaterialTheme.typography.body2
                    )
                    Text(
                        text = stringResource(id = R.string.forgot_password),
                        style = MaterialTheme.typography.body2
                    )
                }
                Spacer(modifier = Modifier.size(16.dp))
                TextField(
                    value = password.value,
                    onValueChange = {
                        if (passwordErrorState.value) {
                            passwordErrorState.value = false
                        }
                        password.value = it
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(
                            BorderStroke(
                                width = 1.dp,
                                color = if (passwordErrorState.value) Color.Red else Color.Transparent
                            ),
                            shape = RoundedCornerShape(10.dp)

                        ),
                   // isError = passwordErrorState.value,
                    label = null,
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = SuraceF9,
                        disabledTextColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent
                    ),
                    placeholder = {
                        Text(
                            stringResource(id = R.string.forgot_password),
                            style = MaterialTheme.typography.body1
                        )
                    },
                    shape = RoundedCornerShape(10.dp),
                    trailingIcon = {
                        IconButton(onClick = { passwordVisibility.value = !passwordVisibility.value }) {
                            Icon(
                                imageVector = if (passwordVisibility.value) Icons.Default.VisibilityOff else Icons.Default.Visibility,
                                contentDescription = "visibility",
                                tint = Color.Black
                            )
                        }
                    },
                    visualTransformation = if (passwordVisibility.value) PasswordVisualTransformation() else VisualTransformation.None
                )
                if (passwordErrorState.value) {
                    Text(text = "Обязательное поле", color = Color.Red, style = MaterialTheme.typography.body2)
                }
                Spacer(modifier = Modifier.size(32.dp))
                MainButtonM(
                    text = stringResource(id = R.string.login),
                    onClick = {
                        when {
                            password.value.text.isEmpty() -> {
                                passwordErrorState.value = true
                            }
                            email.value.text.isEmpty() -> {
                                passwordErrorState.value = true
                            }
                            else -> {
                                Toast.makeText(
                                    context,
                                    "You are logged in",
                                    Toast.LENGTH_SHORT
                                ).show()
//                            navController.navigate("login_screen") {
//                                popUpTo(navController.graph.startDestinationId)
//                                launchSingleTop = true
                                //  }
                            }
                        }
                    },
                    enableState = true
                )
                Spacer(modifier = Modifier.size(24.dp))
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = stringResource(id = R.string.i_dont_have_an_account),
                        style = MaterialTheme.typography.body2,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.size(4.dp))
                    Text(
                        text = stringResource(id = R.string.signup),
                        style = MaterialTheme.typography.body2,
                        textAlign = TextAlign.Center,
                        color = Red435B
                    )
                }
                Spacer(modifier = Modifier.size(40.dp))
            }
        }
    }
//}