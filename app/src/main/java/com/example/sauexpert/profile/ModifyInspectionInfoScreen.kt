package com.example.sauexpert.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.example.sauexpert.R
import com.example.sauexpert.dimensions.Dimensions
import com.example.sauexpert.dimensions.smallDimensions
import com.example.sauexpert.dimensions.sw360Dimensions
import com.example.sauexpert.ui.theme.Gray30
import com.example.sauexpert.widgets.compose.MainButton
import com.example.sauexpert.widgets.compose.Toolbars.MainActionToolBar

@Composable
fun ModifyInspectionInfoScreen() {
    val configuration = LocalConfiguration.current
    val dimensions = if (configuration.screenWidthDp <= 360) smallDimensions else sw360Dimensions
    val navigator = LocalNavigator.currentOrThrow

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Gray30.copy(alpha = 0.19f))
    ) {
        MainActionToolBar(
            titleText = stringResource(R.string.general_inspection),
            iconBackClick = Icons.Default.ArrowBack,
            sizeIconBackClick = dimensions.iconSize_2,
            sizeTitleText = dimensions.fontSizeCustom_5,
            onBackClick = { navigator.pop() },
            modifier = Modifier.padding(16.dp)
        )

        Spacer(modifier = Modifier.height(dimensions.grid_2_5))

        ProfileForInspection(
            content = "user",
            text = 0.4f,
            dimensions = dimensions
        )

        Spacer(modifier = Modifier.height(dimensions.grid_4))

        InfoStatInspectionSection(
            titleIllness = "Мочевыделительная система",
            subtitleIllness = "МОЧЕИСПУСКАНИЕ",
            subtitleIllness2 = "НЕДЕРЖАНИЕ МОЧИ",
            subtitleIllness3 = "СИМПТОМ ПОКОЛАЧИВАНИЯ",
            bottomText = stringResource(R.string.completed_inspection_des),
            dimensions = dimensions
        )
    }
}

@Composable
fun InfoStatInspectionSection(
    titleIllness: String,
    subtitleIllness: String,
    subtitleIllness2: String,
    subtitleIllness3: String,
    bottomText: String,
    parameterChange: Boolean = true,
    dimensions: Dimensions,
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
                style = MaterialTheme.typography.subtitle2,
                fontSize = dimensions.fontSizeSubtitle_2
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = subtitleIllness,
                style = MaterialTheme.typography.body2,
                fontSize = dimensions.fontSizeBody_2
            )

            Spacer(modifier = Modifier.height(8.dp))

            dropDownMenuWithFieldBackGround(
                dataList = listOf(
                    "Option 1", "Option 2", "Option 3", "Option 4",
                ),
                enableStatus = parameterChange,
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextFieldWithBackground(
                textForHint = stringResource(R.string.description),
                enableStatus = parameterChange,
                textState = textDescriptionField,
                onTextChange = { textDescriptionField = it }

            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = subtitleIllness2,
                style = MaterialTheme.typography.body2,
                fontSize = dimensions.fontSizeBody_2
            )

            Spacer(modifier = Modifier.height(8.dp))

            dropDownMenuWithFieldBackGround(
                dataList = listOf(
                    "Option 1", "Option 2", "Option 3", "Option 4",
                ),
                enableStatus = parameterChange,
            )

            Spacer(modifier = Modifier.height(20.dp))


            Text(
                text = subtitleIllness3,
                style = MaterialTheme.typography.body2,
                fontSize = dimensions.fontSizeBody_2
            )

            Spacer(modifier = Modifier.height(8.dp))

            dropDownMenuWithFieldBackGround(
                dataList = listOf(
                    "Option 1", "Option 2", "Option 3", "Option 4",
                ),
                enableStatus = parameterChange,
            )

            Spacer(modifier = Modifier.height(dimensions.grid_6))

            MainButton(
                text = stringResource(id = R.string.proceed),
                onClick = { /*TODO*/ },
                enableState = true,
                buttonHeight = dimensions.buttonHeight_0,
                sizeText = dimensions.fontSizeBody_1,
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = bottomText,
                style = MaterialTheme.typography.body2,
                fontSize = dimensions.fontSizeBody_2,
                color = Gray30
            )

            Spacer(modifier = Modifier.height(20.dp))

        }
    }
}

