package com.example.sauexpert.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
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
        profileForInspection(userName = "user", percentage = 0.4f, showPercentage = true)
        Spacer(modifier = Modifier.height(32.dp))
        FillInfoStatFiled()
    }
}


@Composable
fun FillInfoStatFiled(
    modifier: Modifier = Modifier
) {
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

            FillTextFiled(textForHint = stringResource(R.string.complaints_patient))

            Spacer(modifier = Modifier.height(16.dp))

            FillTextFiled(textForHint = stringResource(R.string.anamnesis_illness))

            Spacer(modifier = Modifier.height(16.dp))

            FillTextFiled(textForHint = stringResource(R.string.anamnesis_of_life))

            Spacer(modifier = Modifier.height(16.dp))

            MainButton(
                text = stringResource(id = R.string.next),
                onClick = { /*TODO*/ },
                enableState = true,
//                modifier = modifier.background(color = Color.Red.copy(alpha = 0.19f))
            )

        }
    }
}




