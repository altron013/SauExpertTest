package com.example.sauexpert.widgets.compose.snackbars

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Snackbar
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sauexpert.R
import com.example.sauexpert.ui.theme.Green34C759
import com.example.sauexpert.ui.theme.SauExpertTypography


@Composable
fun ApplicationSnackbar() {
    Snackbar(
        modifier = Modifier.padding(16.dp),
        shape = RoundedCornerShape(16.dp),
        backgroundColor = Green34C759,
        contentColor = Color.White,
        elevation = 10.dp,
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = stringResource(id = R.string.the_application_has_been_accepted_wait___),
                fontSize = 14.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(7f),
            )
        }
    }
}

@Composable
fun SnackbarText(text : String) {
    Snackbar(
        modifier = Modifier.padding(16.dp),
        shape = RoundedCornerShape(16.dp),
        backgroundColor = Green34C759,
        contentColor = Color.White,
        elevation = 10.dp,
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Text(
                text = text,
                style = SauExpertTypography.body1,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(7f),
            )
        }
    }
}


@Preview
@Composable
fun PrevApp(){
    SnackbarText(text = "\uD83D\uDC4F Группа пациентов создана")
}
