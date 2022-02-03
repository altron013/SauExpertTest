package com.example.sauexpert.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.sauexpert.R
import com.example.sauexpert.ui.theme.Gray30
import com.example.sauexpert.widgets.compose.MainButton

@Composable
fun NewInspectionScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Gray30.copy(alpha = 0.19f))
    ) {
        TopBarForInspectionScreen()
        Spacer(modifier = Modifier.height(20.dp))
        ProfileForInspection(content = "user", text = 0.4f, showPercentage = true)
        Spacer(modifier = Modifier.height(32.dp))
        FillInfoStatFiled()
    }
}


@Composable
fun FillInfoStatFiled(
    modifier: Modifier = Modifier
) {
    var textLifeField by rememberSaveable  { mutableStateOf("") }
    var textComplaintsField by rememberSaveable  { mutableStateOf("") }
    var textIllnessField by rememberSaveable  { mutableStateOf("") }
    val stateBoolean = textLifeField != "" && textComplaintsField != "" && textIllnessField != ""
    val textColorOfButton: Color = if (stateBoolean) Color.Red else Color.White

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(
                color = Color.White
            )
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = stringResource(R.string.fill_patient_detail),
                style = MaterialTheme.typography.body1
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextFieldWithBackground(
                textForHint = stringResource(R.string.complaints_patient),
                textState = textComplaintsField,
                onTextChange = { textComplaintsField = it }
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextFieldWithBackground(
                textForHint = stringResource(R.string.anamnesis_illness),
                textState = textIllnessField,
                onTextChange = { textIllnessField = it })

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextFieldWithBackground(
                textForHint = stringResource(R.string.anamnesis_of_life),
                textState = textLifeField,
                onTextChange = { textLifeField = it })

            Spacer(modifier = Modifier.height(16.dp))

            MainButton(
                text = stringResource(id = R.string.next),
                onClick = { /*TODO*/ },
                enableState = stateBoolean,
                backgroundColor = Color(255, 205, 211),
                textColor = textColorOfButton
//                modifier = modifier.background(color = Color.Red.copy(alpha = 0.19f))
            )

        }
    }
}





