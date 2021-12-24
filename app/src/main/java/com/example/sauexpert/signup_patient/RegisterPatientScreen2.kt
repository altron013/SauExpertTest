package com.example.sauexpert.signup_patient

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.unit.dp
import com.example.sauexpert.R
import com.example.sauexpert.ui.theme.SauExpertTheme
import com.example.sauexpert.widgets.compose.MainButtonM


@Composable
fun RegisterPatientScreen2() {
    Column(
        Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        AuthToolbar(text = stringResource(id = R.string.registration))
        Column(
            //horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(16.dp))
                .background(Color.White)
            .padding(start=16.dp,end=16.dp)
            ) {
            RegistrationPage2Fields()
            Spacer(modifier = Modifier.size(50.dp))

        }
    }
}

@Composable
fun RegistrationPage2Fields() {
    val context = LocalContext.current
    val telNumber = remember {
        mutableStateOf(TextFieldValue())
    }
    val email = remember { mutableStateOf(TextFieldValue()) }
    val countryCode = remember { mutableStateOf(TextFieldValue()) }
    val mobileNo = remember { mutableStateOf(TextFieldValue()) }
    val password = remember { mutableStateOf(TextFieldValue()) }
    val confirmPassword = remember { mutableStateOf(TextFieldValue()) }

    val mobileNoErrorState = remember { mutableStateOf(false) }
    val emailErrorState = remember { mutableStateOf(false) }
    val passwordErrorState = remember { mutableStateOf(false) }
    val confirmPasswordErrorState = remember { mutableStateOf(false) }
    val scrollState = rememberScrollState()
    //SauExpertTheme() {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            AuthItem(
                textTitle = stringResource(id = R.string.your_phone_number).uppercase(),
                name = mobileNo,
                nameErrorState = mobileNoErrorState,
                placeholder = stringResource(id = R.string.enter_phone_number)
            )
            AuthItem(
                textTitle = stringResource(id = R.string.email).uppercase(),
                name = email,
                nameErrorState = mobileNoErrorState,
                placeholder = stringResource(id = R.string.enter_email)
            )
            AuthItem(
                textTitle = stringResource(id = R.string.pick_password),
                name = password,
                nameErrorState = mobileNoErrorState,
                placeholder = stringResource(id = R.string.pick_password)
            )
            AuthItem(
                textTitle = stringResource(id = R.string.repeat_password),
                name = confirmPassword,
                nameErrorState = mobileNoErrorState,
                placeholder = stringResource(id = R.string.repeat_password)
            )

//            val passwordVisibility = remember { mutableStateOf(true) }
//            Text(
//                text = stringResource(id = R.string.password),
//                style = MaterialTheme.typography.body2
//            )
//            Spacer(modifier = Modifier.size(16.dp))
//            TextField(
//                value = password.value,
//                onValueChange = {
//                    if (passwordErrorState.value) {
//                        passwordErrorState.value = false
//                    }
//                    password.value = it
//                },
//                modifier = Modifier.fillMaxWidth(),
//                label = {
//                    Text(text = stringResource(id = R.string.pick_password),
//                        style = MaterialTheme.typography.body2)
//
//                },
//                colors = TextFieldDefaults.textFieldColors(
//                    backgroundColor = MaterialTheme.colors.onBackground,
//                    disabledTextColor = Color.Transparent,
//                    focusedIndicatorColor = Color.Transparent,
//                    unfocusedIndicatorColor = Color.Transparent,
//                    disabledIndicatorColor = Color.Transparent
//                ),
//                isError = passwordErrorState.value,
////                trailingIcon = {
////                    IconButton(onClick = {
////                        passwordVisibility.value = !passwordVisibility.value
////                    }) {
////                        Icon(
////                            imageVector = if (passwordVisibility.value) Icons.Default.VisibilityOff else Icons.Default.Visibility,
////                            contentDescription = "visibility",
////                            tint = Color.Red
////                        )
////                    }
////                },
//                visualTransformation = if (passwordVisibility.value) PasswordVisualTransformation() else VisualTransformation.None
//            )
//            if (passwordErrorState.value) {
//                Text(text = "Required", color = Color.Red)
//            }
//
//            Spacer(Modifier.size(16.dp))
//            val cPasswordVisibility = remember { mutableStateOf(true) }
//
//            TextField(
//                value = confirmPassword.value,
//                onValueChange = {
//                    if (confirmPasswordErrorState.value) {
//                        confirmPasswordErrorState.value = false
//                    }
//                    confirmPassword.value = it
//                },
//                modifier = Modifier.fillMaxWidth(),
//                isError = confirmPasswordErrorState.value,
//                label = {
//                    Text(text = stringResource(id = R.string.repeat_password),
//                        style = MaterialTheme.typography.body2)
//                },
//                colors = TextFieldDefaults.textFieldColors(
//                    backgroundColor = MaterialTheme.colors.onBackground,
//                    disabledTextColor = Color.Transparent,
//                    focusedIndicatorColor = Color.Transparent,
//                    unfocusedIndicatorColor = Color.Transparent,
//                    disabledIndicatorColor = Color.Transparent
//                ),
////                trailingIcon = {
////                    IconButton(onClick = {
////                        cPasswordVisibility.value = !cPasswordVisibility.value
////                    }) {
////                        Icon(
////                            imageVector = if (cPasswordVisibility.value) Icons.Default.VisibilityOff else Icons.Default.Visibility,
////                            contentDescription = "visibility",
////                            tint = Color.Red
////                        )
////                    }
////                },
//                visualTransformation = if (cPasswordVisibility.value) PasswordVisualTransformation() else VisualTransformation.None
//            )
//            if (confirmPasswordErrorState.value) {
//                val msg = if (confirmPassword.value.text.isEmpty()) {
//                    "Required"
//                } else if (confirmPassword.value.text != password.value.text) {
//                    "Password not matching"
//                } else {
//                    ""
//                }
//                Text(text = msg, color = Color.Red)
//            }
//            Spacer(Modifier.size(24.dp))

            Spacer(modifier = Modifier.size(24.dp))
            MainButtonM(
                onClick = {
                    when {

                        mobileNo.value.text.isEmpty() -> {
                            mobileNoErrorState.value = true
                        }
                        email.value.text.isEmpty() -> {
                            mobileNoErrorState.value = true
                        }
                        password.value.text.isEmpty() -> {
                            mobileNoErrorState.value = true
                        }
                        confirmPassword.value.text != password.value.text -> {
                            mobileNoErrorState.value = true
                        }
                        else -> {
                            Toast.makeText(
                                context,
                                "Registered successfully",
                                Toast.LENGTH_SHORT
                            ).show()
//                            navController.navigate("login_screen") {
//                                popUpTo(navController.graph.startDestinationId)
//                                launchSingleTop = true
                            //  }
                        }
                    }
                },
                text = stringResource(id = R.string.signup),
                enableState = true
            )
            Spacer(Modifier.size(16.dp))
        }
    }
//}
