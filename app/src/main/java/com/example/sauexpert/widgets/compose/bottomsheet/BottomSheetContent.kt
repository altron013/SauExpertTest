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
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 32.dp)
    ) {

        Text(
            text = stringResource(R.string.continue_inspection),
            style = MaterialTheme.typography.caption
        )

        Spacer(modifier = Modifier.height(24.dp))


        Row(
            modifier = modifier.fillMaxWidth()
        ) {
            RoundImage(
                image = avatar,
                modifier = Modifier
                    .size(48.dp)
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column {
                Text(
                    text = userName,
                    style = MaterialTheme.typography.subtitle2,
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = diagnosisString,
                    style = MaterialTheme.typography.body2,
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = stringResource(R.string.continue_inspection_des),
            style = MaterialTheme.typography.body1
        )

        Spacer(modifier = Modifier.height(24.dp))

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            MainButton(
                text = stringResource(id = R.string.continue_inspection),
                onClick = onClick,
                enableState = true
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = stringResource(R.string.close),
                style = MaterialTheme.typography.body1,
                color = Color.Red,
                modifier = modifier.clickable {
                    onBackClick()
                }
            )
        }
    }
}
