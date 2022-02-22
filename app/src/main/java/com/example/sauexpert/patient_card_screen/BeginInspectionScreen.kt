package com.example.sauexpert.patient_card_screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sauexpert.R
import com.example.sauexpert.model.OnBoardScreenData
import com.example.sauexpert.ui.theme.Gray30
import com.example.sauexpert.widgets.compose.MainButton
import com.example.sauexpert.widgets.compose.buttons.MainButtonsInRow
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState


@Composable
fun BeginInspectionScreen(
    modifier: Modifier = Modifier
) {
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
                .size(width = 145.dp, height = 185.dp)
        )

        Text(
            text = stringResource(id = R.string.begin_first_inspection),
            style = MaterialTheme.typography.h6,
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = stringResource(id = R.string.begin_first_inspection_desc),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.button,
            fontSize = 15.sp,
            color = Gray30,
            modifier = modifier.padding(horizontal = 30.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        MainButton(
            text = stringResource(id = R.string.begin_diagnostic),
            icon = R.drawable.ic_play_fill,
            onClick = { /*TODO*/ },
            enableState = true,
            modifier = modifier.padding(horizontal = 66.dp)
        )


    }
}