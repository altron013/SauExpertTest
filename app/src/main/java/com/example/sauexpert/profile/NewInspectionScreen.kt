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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.sauexpert.R
import com.example.sauexpert.dimensions.Dimensions
import com.example.sauexpert.dimensions.smallDimensions
import com.example.sauexpert.dimensions.sw360Dimensions
import com.example.sauexpert.ui.theme.Gray30
import com.example.sauexpert.ui.theme.SauExpertTheme
import com.example.sauexpert.ui.theme.Pink42949
import com.example.sauexpert.widgets.compose.MainButton
import com.example.sauexpert.widgets.compose.Toolbars.MainActionToolBar

@Composable
fun NewInspectionScreen() {
    val configuration = LocalConfiguration.current
    val dimensions = if (configuration.screenWidthDp <= 360) smallDimensions else sw360Dimensions

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
            onBackClick = {},
            modifier = Modifier.padding(16.dp)
        )

        Spacer(modifier = Modifier.height(dimensions.grid_2_5))

        ProfileForInspection(
            content = "user",
            text = 0.4f,
            showPercentage = true,
            dimensions = dimensions
        )

        Spacer(modifier = Modifier.height(dimensions.grid_4))

        FillInfoStatFiled(
            dimensions = dimensions
        )
    }
}


@Composable
fun FillInfoStatFiled(
    dimensions: Dimensions,
    modifier: Modifier = Modifier
) {
    var textLifeField by rememberSaveable { mutableStateOf("") }
    var textComplaintsField by rememberSaveable { mutableStateOf("") }
    var textIllnessField by rememberSaveable { mutableStateOf("") }
    val stateBoolean = textLifeField != "" && textComplaintsField != "" && textIllnessField != ""
    val textColorOfButton: Color = if (stateBoolean) Color.Red else Color.White

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(
                color = Color.White
            )
            .padding(16.dp)
    ) {
        Text(
            text = stringResource(R.string.fill_patient_detail),
            style = MaterialTheme.typography.body1,
            fontSize = dimensions.fontSizeBody_1
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
            backgroundColor = Pink42949,
            textColor = textColorOfButton,
            buttonHeight = dimensions.buttonHeight_0,
            sizeText = dimensions.fontSizeBody_1,
        )
    }
}

@Preview
@Composable
fun PrevIns() {
    SauExpertTheme() {
        ViewInspectionInfoScreen()
    }
}





