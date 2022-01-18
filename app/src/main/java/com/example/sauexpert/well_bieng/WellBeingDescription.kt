package com.example.sauexpert.well_bieng

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sauexpert.R
import com.example.sauexpert.signup_doctor.SubmitApplicationToolbar
import com.example.sauexpert.widgets.compose.MainButton
import com.example.sauexpert.widgets.compose.buttons.OutlinedMainButton


@ExperimentalAnimationApi
@Composable
fun WellBeingDescription() {
    Column() {
        SubmitApplicationToolbar(
            centerText = stringResource(id = R.string.arterial_pressure),
            leftText = stringResource(id = R.string.back)
        )
        Column(
            Modifier
                .padding(20.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Text(
                text = stringResource(id = R.string.arterial_pressure),
                fontSize = 30.sp,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(Modifier.padding(15.dp))
            Text(
                text = "Назначено: 8 июня 2020 | 10:30",
                fontSize = 17.sp,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(Modifier.padding(4.dp))
            Text(text = "Анатолий Смирнов", fontSize = 17.sp)
            Spacer(Modifier.padding(14.dp))
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
    Text(text = "Хорошо ли Вы себя чувствуете?", fontSize = 17.sp)
    Spacer(Modifier.padding(12.dp))
    Row() {
        OutlinedMainButton(
            text = "Да",
            onClick = { /*TODO*/ },
            enableState = true,
            modifier = Modifier.weight(1f)
        )
        Spacer(Modifier.padding(6.dp))
        OutlinedMainButton(
            text = "Нет",
            onClick = { /*TODO*/ },
            enableState = true,
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
fun DefineWellBeing() {
    Text(text = "Опишите самочувствие", fontSize = 17.sp)
    Column(modifier = Modifier.padding(end=56.dp,start = 69.dp)) {
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
    MainButton(text = stringResource(id = R.string.confirm), onClick = { /*TODO*/ }, enableState =false )
}