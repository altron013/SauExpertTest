package com.example.sauexpert.widgets.compose.bottomsheet

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.sauexpert.R
import com.example.sauexpert.dimensions.Dimensions
import com.example.sauexpert.profile.RoundImage
import com.example.sauexpert.widgets.compose.MainButton


@ExperimentalMaterialApi
@Composable
fun BottomSheetContentForContinueInspection(
    userName: String,
    diagnosisString: String,
    avatar: Painter,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    onBackClick: () -> Unit,
    dimensions: Dimensions
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 32.dp)
    ) {

        Text(
            text = stringResource(R.string.continue_inspection),
            style = MaterialTheme.typography.caption,
            fontSize = dimensions.fontSizeCaption
        )

        Spacer(modifier = Modifier.height(dimensions.grid_3))


        Row(
            modifier = modifier.fillMaxWidth()
        ) {
            RoundImage(
                image = avatar,
                modifier = Modifier
                    .size(dimensions.imageSize_0)
            )

            Spacer(modifier = Modifier.width(dimensions.grid_1_5))

            Column {
                Text(
                    text = userName,
                    style = MaterialTheme.typography.subtitle2,
                    fontSize = dimensions.fontSizeSubtitle_2
                )

                Spacer(modifier = Modifier.height(dimensions.grid_0_5))

                Text(
                    text = diagnosisString,
                    style = MaterialTheme.typography.body2,
                    fontSize = dimensions.fontSizeBody_2
                )
            }
        }

        Spacer(modifier = Modifier.height(dimensions.grid_3))

        Text(
            text = stringResource(R.string.continue_inspection_des),
            style = MaterialTheme.typography.body1,
            fontSize = dimensions.fontSizeBody_1
        )

        Spacer(modifier = Modifier.height(dimensions.grid_3))

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            MainButton(
                text = stringResource(id = R.string.continue_inspection),
                onClick = onClick,
                enableState = true,
                buttonHeight = dimensions.buttonHeight_0,
                sizeText = dimensions.fontSizeBody_1
            )

            Spacer(modifier = Modifier.height(dimensions.grid_2_5))

            Text(
                text = stringResource(R.string.close),
                style = MaterialTheme.typography.body1,
                fontSize = dimensions.fontSizeBody_1,
                color = Color.Red,
                modifier = modifier.clickable {
                    onBackClick()
                }
            )
        }
    }
}

@ExperimentalMaterialApi
@Composable
fun BottomSheetContentForSoas(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    dimensions: Dimensions
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 17.dp, vertical = 32.dp)
    ) {
        Column(
            modifier = Modifier
        ) {
            Text(
                text = stringResource(R.string.soas_analysis),
                style = MaterialTheme.typography.caption,
                fontSize = dimensions.fontSizeCaption
            )


            Spacer(modifier = Modifier.height(dimensions.grid_2))

            Text(
                text = stringResource(R.string.soas_description),
                style = MaterialTheme.typography.body1,
                fontSize = dimensions.fontSizeBody_1
            )

            Spacer(modifier = Modifier.height(dimensions.grid_3))

            MainButton(
                text = stringResource(id = R.string.understand),
                onClick = onClick,
                enableState = true,
                buttonHeight = dimensions.buttonHeight_0,
                sizeText = dimensions.fontSizeBody_1
            )
        }
    }
}
