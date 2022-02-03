package com.example.sauexpert.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
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
fun ViewInspectionInfoScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Gray30.copy(alpha = 0.19f))
    ) {
        TopBarForInspectionScreen()
        Spacer(modifier = Modifier.height(20.dp))
        ProfileForInspection(content = "user", text = 0.4f)
        Spacer(modifier = Modifier.height(32.dp))
        InfoStatInspectionSectionForView(
            titleIllness = "Мочевыделительная система",
            subtitleIllness = "МОЧЕИСПУСКАНИЕ",
            subtitleIllness2 = "НЕДЕРЖАНИЕ МОЧИ",
            subtitleIllness3 = "СИМПТОМ ПОКОЛАЧИВАНИЯ",
            bottomText = stringResource(R.string.next_section),
            parameterChange = false
        )
    }

}

@Composable
fun InfoStatInspectionSectionForView(
    titleIllness: String,
    subtitleIllness: String,
    subtitleIllness2: String,
    subtitleIllness3: String,
    bottomText: String,
    parameterChange: Boolean = true,
    modifier: Modifier = Modifier
) {
    var textDescriptionField by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
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
                text = titleIllness,
                style = MaterialTheme.typography.subtitle2
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = subtitleIllness,
                style = MaterialTheme.typography.body2
            )

            Spacer(modifier = Modifier.height(8.dp))

            dropDownMenuWithFieldBackGround(
                dataList = listOf(
                    "Option 1", "Option 2", "Option 3", "Option 4",
                ),
                enableStatus = parameterChange
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextFieldWithBackground(stringResource(R.string.description), enableStatus = parameterChange,
                textState = textDescriptionField,
                onTextChange = { textDescriptionField = it }
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = subtitleIllness2,
                style = MaterialTheme.typography.body2
            )

            Spacer(modifier = Modifier.height(8.dp))

            dropDownMenuWithFieldBackGround(
                dataList = listOf(
                    "Option 1", "Option 2", "Option 3", "Option 4",
                ),
                enableStatus = parameterChange
            )

            Spacer(modifier = Modifier.height(20.dp))


            Text(
                text = subtitleIllness3,
                style = MaterialTheme.typography.body2
            )

            Spacer(modifier = Modifier.height(8.dp))

            dropDownMenuWithFieldBackGround(
                dataList = listOf(
                    "Option 1", "Option 2", "Option 3", "Option 4",
                ),
                enableStatus = parameterChange
            )

            Spacer(modifier = Modifier.height(48.dp))

            MainButton(
                text = stringResource(id = R.string.proceed),
                onClick = { /*TODO*/ },
                enableState = true,
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = bottomText,
                style = MaterialTheme.typography.body2,
                color = Gray30
            )

            Spacer(modifier = Modifier.height(40.dp))

        }
    }
}
