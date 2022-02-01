package com.example.sauexpert.signup_patient

import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.example.sauexpert.R
import com.example.sauexpert.ui.theme.SauExpertTheme
import com.example.sauexpert.ui.theme.SurfaceF9
import com.example.sauexpert.ui.theme.SurfaceF9
import com.example.sauexpert.widgets.compose.MainButtonM


@ExperimentalComposeUiApi
@Composable
fun RegisterPatientScreen() {
    Column(
        Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
    ) {
        AuthToolbar(text = stringResource(id = R.string.registration))
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(16.dp))
                .background(Color.White),
        ) {
            Image(
                painter = painterResource(id = R.drawable.avatar),
                modifier = Modifier
                    .height(100.dp)
                    .width(100.dp)
                    .clip(CircleShape)
                    .padding(top = 32.dp, bottom = 8.dp),

                contentDescription = null
            )
            Text(
                text = stringResource(id = R.string.new_photo),
                style = MaterialTheme.typography.subtitle1
            )
            RegistrationFields()
            Spacer(modifier = Modifier.size(48.dp))
        }
    }
}


@ExperimentalComposeUiApi
@Composable
fun RegistrationFields() {
    val context = LocalContext.current
    val name = remember {
        mutableStateOf(TextFieldValue())
    }
    val surname = remember { mutableStateOf(TextFieldValue()) }
    val middlename = remember { mutableStateOf(TextFieldValue()) }
    val date = remember { mutableStateOf(TextFieldValue()) }

    val ErrorState = remember { mutableStateOf(false) }
    val surnameNameErrorState = remember { mutableStateOf(false) }
    val codeErrorState = remember { mutableStateOf(false) }
    val dateErrorState = remember { mutableStateOf(false) }
//    val confirmPasswordErrorState = remember { mutableStateOf(false) }
   // SauExpertTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
        ) {
            Spacer(Modifier.size(8.dp))
            Text(
                text = stringResource(id = R.string.personal_info),
                style = MaterialTheme.typography.body1
            )
            AuthItem(
                textTitle = stringResource(id = R.string.your_name),
                name = name,
                nameErrorState = ErrorState,
                placeholder = stringResource(
                    id = R.string.write_name
                )
            )
            AuthItem(
                textTitle = stringResource(id = R.string.surname),
                name = surname,
                nameErrorState = ErrorState,
                placeholder = stringResource(
                    id = R.string.write_surname
                )
            )
            AuthItem(
                textTitle = stringResource(id = R.string.middle_name_optional),
                name = middlename,
                nameErrorState = ErrorState,
                placeholder = stringResource(
                    id = R.string.write_middle_name
                )
            )
            AuthItem(
                textTitle = stringResource(id = R.string.birth_date),
                name = date,
                nameErrorState = ErrorState,
                placeholder = stringResource(
                    id = R.string.choose_date
                )
            )
            Spacer(modifier = Modifier.size(16.dp))
            RadioButtonDemo()
            AuthItem(
                textTitle = stringResource(id = R.string.doctor_code),
                name = date,
                nameErrorState = ErrorState,
                placeholder = stringResource(
                    id = R.string.write_code_doctor
                )
            )
            Spacer(Modifier.size(8.dp))
            Text(
                text = stringResource(id = R.string.doctor_with_code_your_doctor),
                style = MaterialTheme.typography.body2
            )
            Spacer(Modifier.size(24.dp))
            MainButtonM(
                onClick = {
                    when {
                        name.value.text.isEmpty() -> {
                            ErrorState.value = true
                        }
//                        surname.value.text.isEmpty() -> {
//                            surnameNameErrorState.value = true
//                        }
//                        password.value.text.isEmpty() -> {
//                            passwordErrorState.value = true
//                        }
//                        confirmPassword.value.text.isEmpty() -> {
//                            confirmPasswordErrorState.value = true
//                        }
//                        confirmPassword.value.text != password.value.text -> {
//                            confirmPasswordErrorState.value = true
//                        }
                        else -> {
                            Toast.makeText(
                                context,
                                "Registered successfully",
                                Toast.LENGTH_SHORT
                            ).show()
//                        navController.navigate("login_screen") {
//                            popUpTo(navController.graph.startDestinationId)
//                            launchSingleTop = true
//                        }
                        }
                    }
                },
                text = stringResource(id = R.string.proceed),
                enableState = true
            )
        }
    }
//}

@ExperimentalComposeUiApi
@Composable
fun RadioButtonDemo() {
    Column() {
        val selectedGender = remember { mutableStateOf("") }
        Text(stringResource(id = R.string.gender), style = MaterialTheme.typography.body2)
        Spacer(modifier = Modifier.size(16.dp))
        Row {
            RadioButton(selected = selectedGender.value == Gender.male, onClick = {
                selectedGender.value = Gender.male
            })
            Spacer(modifier = Modifier.size(5.dp))
            Text(
                Gender.male,
                style = MaterialTheme.typography.subtitle1,
                modifier = Modifier.clickable { selectedGender.value = Gender.male })
            Spacer(modifier = Modifier.size(5.dp))
            RadioButton(selected = selectedGender.value == Gender.female, onClick = {
                selectedGender.value = Gender.female
            })
            Spacer(modifier = Modifier.size(5.dp))
            Text(
                Gender.female,
                style = MaterialTheme.typography.subtitle1,
                modifier = Modifier.clickable { selectedGender.value = Gender.female })
        }

    }
}

object Gender {
    const val male = "Мужской"
    const val female = "Женский"
}

@Composable
fun AuthTextFiled(
    name: MutableState<TextFieldValue>,
    ErrorState: MutableState<Boolean>,
    placeholder: String,
    modifier: Modifier=Modifier.fillMaxWidth()
        .border(
            BorderStroke(
                width = 1.dp,
                color = if (ErrorState.value) Color.Red else Color.Transparent
            ),
            shape = RoundedCornerShape(10.dp)

        )
) {

    TextField(
        value = name.value,
        onValueChange = {
            if (ErrorState.value) {
                ErrorState.value = false
            }
            name.value = it
        },
        modifier = modifier
            .fillMaxWidth()
            .border(
                BorderStroke(
                    width = 1.dp,
                    color = if (ErrorState.value) Color.Red else Color.Transparent
                ),
                shape = RoundedCornerShape(10.dp)

            ),
        //isError = ErrorState.value,
        label = null,
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = SurfaceF9,
            disabledTextColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        ),
        placeholder = { Text(placeholder, style = MaterialTheme.typography.body1) },
        shape = RoundedCornerShape(10.dp),
    )
    if (ErrorState.value) {
        Text(text = "Обязательное поле", color = Color.Red, style = MaterialTheme.typography.body2)
    }

}

@Composable
fun AuthItem(
    textTitle: String,
    name: MutableState<TextFieldValue>,
    nameErrorState: MutableState<Boolean>,
    placeholder: String
) {
    Spacer(Modifier.size(20.dp))
    Text(
        text = textTitle,
        style = MaterialTheme.typography.body2
    )
    Spacer(Modifier.size(16.dp))
    AuthTextFiled(name = name, ErrorState = nameErrorState, placeholder = placeholder)
}

