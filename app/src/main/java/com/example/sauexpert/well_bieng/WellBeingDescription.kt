package com.example.sauexpert.well_bieng

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sauexpert.R
import com.example.sauexpert.widgets.compose.MainButton
import com.example.sauexpert.widgets.compose.Toolbars.MainToolbar
import com.example.sauexpert.widgets.compose.buttons.OutlinedMainButton


@ExperimentalAnimationApi
@Composable
fun WellBeingDescription() {
    Column() {
        MainToolbar(onBackClick = {}, text = stringResource(id = R.string.arterial_pressure))
        Column(
            Modifier
                .padding(20.dp)
                .verticalScroll(rememberScrollState())
        ) {
            WellBeingGeneral(
                heading = stringResource(id = R.string.arterial_pressure),
                date = "8 июня 2020 | 10:30",
                patient = "Анатолий Смирнов"
            )
            ArterialForm()
            Spacer(Modifier.padding(14.dp))
            WellBeingCheck()
            Spacer(Modifier.padding(5.dp))
            DefineWellBeing()
            Spacer(Modifier.padding(30.dp))
        }
    }
}

@Composable
fun ArterialForm() {
    Text(text = "САД", fontSize = 15.sp)
    Spacer(Modifier.padding(6.dp))
    TextField(value = "", onValueChange = {},
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.Transparent,
//        disabledTextColor = Color.Transparent,
//        focusedIndicatorColor = Color.Transparent,
//        unfocusedIndicatorColor = Color.Transparent,
//        disabledIndicatorColor = Color.Transparent
        ), modifier = Modifier.fillMaxWidth(),
        placeholder = {
            Text(
                "Введите текст",
                style = MaterialTheme.typography.body1
            )
        }
    )
    Spacer(Modifier.padding(6.dp))
    Text(text = "ДАД", fontSize = 15.sp)
    Spacer(Modifier.padding(6.dp))
    TextField(value = "", onValueChange = {},
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.Transparent,
//        disabledTextColor = Color.Transparent,
//        focusedIndicatorColor = Color.Transparent,
//        unfocusedIndicatorColor = Color.Transparent,
//        disabledIndicatorColor = Color.Transparent
        ), modifier = Modifier.fillMaxWidth(),
        placeholder = {
            Text(
                "Введите текст",
                style = MaterialTheme.typography.body1
            )
        }
    )
    Spacer(Modifier.padding(6.dp))
    Text(text = "Пульс", fontSize = 15.sp)
    Spacer(Modifier.padding(6.dp))
    TextField(value = "", onValueChange = {},
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.Transparent,
//        disabledTextColor = Color.Transparent,
//        focusedIndicatorColor = Color.Transparent,
//        unfocusedIndicatorColor = Color.Transparent,
//        disabledIndicatorColor = Color.Transparent
        ), modifier = Modifier.fillMaxWidth(),
        placeholder = {
            Text(
                "Введите текст",
                style = MaterialTheme.typography.body1
            )
        }
    )
    Spacer(Modifier.padding(6.dp))
    Text(text = "* обязательное поле", fontSize = 15.sp)
}

@Composable
fun WellBeingCheck() {
    val options = listOf(
        "Да",
        "Нет"
    )
    var selectedOption by remember {
        mutableStateOf("")
    }
    val onSelectionChange = { text: String ->
        selectedOption = text
    }
    val selectedRole = remember { mutableStateOf("") }
    Text(text = "Хорошо ли Вы себя чувствуете?", fontSize = 17.sp)
    Spacer(Modifier.padding(12.dp))
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize(),
    ) {
        options.forEach { text ->
            Row(
                modifier = Modifier
                    .padding(
                        all = 8.dp,
                    )
                    .fillMaxWidth()
            ) {
                Text(
                    text = text,
                    style = typography.body1.merge(),
                    color = Color.Red,
                    modifier = Modifier
                        .clickable {
                            onSelectionChange(text)
                        }
                        .border(border = BorderStroke(
                            width = 1.dp,
                            color =
                            if (text == selectedOption) {
                                Color.Red
                            } else {
                                Color.LightGray
                            }
                        )).clip(
                            shape = RoundedCornerShape(
                                size = 12.dp,
                            ),
                        )
                        .padding(
                            vertical = 12.dp,
                            horizontal = 16.dp,
                        )

                )
            }
        }
//        Column() {
//            options.forEach { text ->
//                OutlinedMainButton(
//                    text = text,
//                    onClick = {
//                        //selectedRole.value = text
//                    },
//                    enableState = true,
//                    modifier = Modifier
//                        .clickable {
//                            onSelectionChange(text)
//                        }
//                        .background(
//                            if (text == selectedOption) {
//                                Color.Magenta
//                            } else {
//                                Color.LightGray
//                            }
//                        )
//                )
//            }
//        }
    }
}

@Composable
fun DefineWellBeing() {
    Text(text = "Опишите самочувствие", fontSize = 17.sp)
    Column(modifier = Modifier.padding(end = 56.dp, start = 69.dp)) {
        Spacer(Modifier.padding(10.dp))
        OutlinedMainButton(text = "ГОЛОВНАЯ БОЛЬ", onClick = { /*TODO*/ }, enableState = true)
        Spacer(Modifier.padding(10.dp))
        OutlinedMainButton(text = "ГОЛОВНАЯ БОЛЬ", onClick = { /*TODO*/ }, enableState = true)
        Spacer(Modifier.padding(10.dp))
        OutlinedMainButton(text = "ГОЛОВНАЯ БОЛЬ", onClick = { /*TODO*/ }, enableState = true)
        Spacer(Modifier.padding(10.dp))
        OutlinedMainButton(text = "ГОЛОВНАЯ БОЛЬ", onClick = { /*TODO*/ }, enableState = true)
        Spacer(Modifier.padding(10.dp))
        OutlinedMainButton(text = "ГОЛОВНАЯ БОЛЬ", onClick = { /*TODO*/ }, enableState = true)
        Spacer(Modifier.padding(10.dp))
    }
    MainButton(
        text = stringResource(id = R.string.confirm),
        onClick = { /*TODO*/ },
        enableState = false
    )
}