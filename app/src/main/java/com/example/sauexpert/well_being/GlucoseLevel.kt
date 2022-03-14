package com.example.sauexpert.well_being

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sauexpert.R
import com.example.sauexpert.ui.theme.Green57C3A7
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
    var selectedOption by remember {
        mutableStateOf("")
    }
    val onSelectionChange = { text: String ->
        selectedOption = text
    }
    var sad = remember { mutableStateOf(TextFieldValue()) }
    WellBeingTextField(sad, "Показатель уровня глюкозы в крови")
    Spacer(Modifier.padding(10.dp))
    Text(text = stringResource(id = R.string.when_measurement_was_taken), fontSize = 15.sp)
    Spacer(Modifier.padding(10.dp))
    Row(Modifier.fillMaxWidth()){
        ColoredOutlineButton(
            "До еды",
            onSelectionChange = onSelectionChange,
            selectedOption = selectedOption,
            modifier = Modifier
                .weight(0.5f)
        )
        Spacer(modifier = Modifier.padding(7.dp))
        ColoredOutlineButton(
            "После еды",
            onSelectionChange = onSelectionChange,
            selectedOption = selectedOption,
            modifier = Modifier.weight(0.5f)
        )
    }
}

@Composable
fun ColoredOutlineButton(
    text: String,
    onSelectionChange: (String) -> Unit,
    selectedOption: String,
    modifier: Modifier
) {
    OutlinedMainButton(
        text = text,
        onClick = {
            if (onSelectionChange != null) {
                onSelectionChange(text)
            }
        },
        modifier = modifier,
        enableState = true,
        backgroundColor = if (text == selectedOption) Green57C3A7 else Color.Transparent,
        textColor = if (text == selectedOption) Color.White else Color.Gray,

    )
}