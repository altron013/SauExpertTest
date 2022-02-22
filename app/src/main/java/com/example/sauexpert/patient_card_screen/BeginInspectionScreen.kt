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
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sauexpert.R
import com.example.sauexpert.dimensions.smallDimensions
import com.example.sauexpert.dimensions.sw360Dimensions
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
        val configuration = LocalConfiguration.current
        val dimensions = if (configuration.screenWidthDp <= 360) smallDimensions else sw360Dimensions

        Image(
            painter = painterResource(R.drawable.beging_inspection),
            contentDescription = null,
            modifier = modifier
                .height(dimensions.imageHeight_3)
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