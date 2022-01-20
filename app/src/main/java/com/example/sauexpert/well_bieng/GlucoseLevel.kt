package com.example.sauexpert.well_bieng

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
import com.example.sauexpert.widgets.compose.Toolbars.MainToolbar
import com.example.sauexpert.widgets.compose.buttons.OutlinedMainButton

@Composable
fun WellBeingGeneral(
    heading: String,
    date: String,
    patient: String
) {
    Column() {
        Text(
            text = heading,
            fontSize = 30.sp,
            fontWeight = FontWeight.SemiBold
        )
        Spacer(Modifier.padding(12.dp))
        Text(
            text = "Назначено: $date",
            fontSize = 17.sp,
            fontWeight = FontWeight.SemiBold
        )
        Spacer(Modifier.padding(4.dp))
        Text(
            text = patient,
            fontSize = 17.sp,
            fontWeight = FontWeight.SemiBold
        )
        Spacer(Modifier.padding(14.dp))
    }
}

@Composable
fun GlucoseLevelScreen() {
    Column() {
        MainToolbar(onBackClick = {}, text = stringResource(id = R.string.glucose_level))
        Column(
            Modifier
                .padding(20.dp)
                .verticalScroll(rememberScrollState())
        ) {
            WellBeingGeneral(
                heading = stringResource(id = R.string.glucose_level),
                date = "8 июня 2020 | 10:30",
                patient = "Анатолий Смирнов"
            )
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
fun GlucoseIndicatorForm() {
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
        OutlinedMainButton(
            text = stringResource(id = R.string.before_food),
            onClick = { /*TODO*/ },
            enableState = true,
            modifier = Modifier.weight(1f)
        )
        Spacer(modifier = Modifier.padding(7.dp))
        OutlinedMainButton(
            text = stringResource(id = R.string.after_food),
            onClick = { /*TODO*/ },
            enableState = true,
            modifier = Modifier.weight(1f)
        )
    }
}