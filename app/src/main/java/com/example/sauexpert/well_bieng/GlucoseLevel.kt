package com.example.sauexpert.well_bieng

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sauexpert.R
import com.example.sauexpert.signup_doctor.SubmitApplicationToolbar
import com.example.sauexpert.widgets.compose.buttons.OutlinedMainButton

@Composable
fun GlucoseLevelScreen(){
    Column() {
        SubmitApplicationToolbar(leftText = "Назад",centerText = stringResource(id = R.string.glucose_level))
        Column(
            Modifier
                .padding(20.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Text(
                text = stringResource(id = R.string.glucose_level),
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
            GlucoseIndicatorForm()
            Spacer(Modifier.padding(14.dp))
            WellBeingCheck()
            Spacer(Modifier.padding(5.dp))
            DefineWellBeing()
            Spacer(Modifier.padding(30.dp))
        }
    }
}

@Composable
fun GlucoseIndicatorForm(){
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
    Spacer(Modifier.padding(10.dp))
    Text(text = stringResource(id = R.string.when_measurement_was_taken), fontSize = 15.sp)
    Spacer(Modifier.padding(10.dp))
    Row {
        OutlinedMainButton(text = stringResource(id = R.string.before_food), onClick = { /*TODO*/ }, enableState =true,modifier = Modifier.weight(1f))
        Spacer(modifier=Modifier.padding(7.dp))
        OutlinedMainButton(text = stringResource(id = R.string.after_food), onClick = { /*TODO*/ }, enableState = true,modifier = Modifier.weight(1f))
    }
}