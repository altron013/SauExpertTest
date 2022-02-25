package com.example.sauexpert.patient_card_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.sauexpert.R
import com.example.sauexpert.dimensions.smallDimensions
import com.example.sauexpert.dimensions.sw360Dimensions
import com.example.sauexpert.ui.theme.Gray30
import com.example.sauexpert.widgets.compose.MainButton


@Composable
fun BeginInspectionScreen(
    modifier: Modifier = Modifier
) {
    val configuration = LocalConfiguration.current
    val dimensions = if (configuration.screenWidthDp <= 360) smallDimensions else sw360Dimensions

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(R.drawable.beging_inspection),
            contentDescription = null,
            modifier = modifier
                .height(dimensions.imageHeight_3)
                .width(dimensions.imageWidth_0)
        )

        Text(
            text = stringResource(id = R.string.begin_first_inspection),
            style = MaterialTheme.typography.h6,
            fontSize = dimensions.fontSizeCustom_0
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = stringResource(id = R.string.begin_first_inspection_desc),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.button,
            fontSize = dimensions.fontSizeCustom_1,
            color = Gray30,
            modifier = modifier.padding(horizontal = 30.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        MainButton(
            text = stringResource(id = R.string.begin_diagnostic),
            icon = R.drawable.ic_play_fill,
            onClick = { /*TODO*/ },
            enableState = true,
            modifier = modifier.padding(horizontal = 66.dp),
            buttonHeight = dimensions.buttonHeight_0,
            sizeText = dimensions.fontSizeBody_1
        )


    }
}