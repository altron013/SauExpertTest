package com.example.sauexpert.on_board_screen

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
import com.example.sauexpert.R
import com.example.sauexpert.model.OnBoardScreenData
import com.example.sauexpert.widgets.compose.MainButton
import com.example.sauexpert.widgets.compose.buttons.MainButtonsInRow
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState


@ExperimentalAnimationApi
@ExperimentalPagerApi
@Composable
fun OnBoardInfoScreen() {
    Column(
        modifier = Modifier.fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Spacer(modifier = Modifier.height(40.dp))
        LogoSection()
        Spacer(modifier = Modifier.height(9.dp))
        OnBoardingUI(
            onBoardScreenData = listOf(
                OnBoardScreenData(
                    stringResource(id = R.string.inspections),
                    stringResource(id = R.string.inspections_description),
                    R.drawable.ic_info_page_1
                ),
                OnBoardScreenData(
                    stringResource(id = R.string.manager_patient),
                    stringResource(id = R.string.manager_patient_description),
                    R.drawable.ic_info_page_2
                ),
                OnBoardScreenData(
                    stringResource(id = R.string.notification),
                    stringResource(id = R.string.notification_description),
                    R.drawable.ic_info_page_3
                )
            )
        )
    }
}

@Composable
fun LogoSection(modifier: Modifier = Modifier) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo_light_1),
            contentDescription = null,
            modifier = modifier
                .size(width = 67.dp, height = 53.dp)
        )
        Spacer(modifier = modifier.width(16.dp))
        Image(
            painter = painterResource(id = R.drawable.logo_light_2),
            contentDescription = null,
            modifier = modifier
                .size(width = 133.dp, height = 53.dp)
        )
    }
}

@ExperimentalAnimationApi
@ExperimentalPagerApi
@Composable
fun OnBoardingUI(
    onBoardScreenData: List<OnBoardScreenData>,
    modifier: Modifier = Modifier
) {
    val pagerState = rememberPagerState(pageCount = 3)
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Box(
            modifier = modifier
                .fillMaxWidth()
        ) {
            HorizontalPager(
                state = pagerState,
                modifier = modifier
                    .fillMaxWidth()
            ) { page ->
                PageUI(
                    page = onBoardScreenData[page],
                    modifier = modifier.align(Alignment.Center)
                )
            }

            HorizontalPagerIndicator(
                pagerState = pagerState,
                modifier = modifier
                    .padding(top = 140.dp)
                    .align(Alignment.Center),
                activeColor = colorResource(R.color.black)
            )
        }

        Spacer(modifier = Modifier.height(50.dp))
        ButtonForBottomForInfoPage(statePage = pagerState.currentPage)
    }
}

@Composable
fun PageUI(page: OnBoardScreenData, modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .fillMaxWidth()
    ) {

        Image(
            painter = painterResource(page.image),
            contentDescription = null,
            modifier = modifier
        )
        Spacer(modifier = Modifier.height(50.dp))
        Text(
            text = page.title,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.caption
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = page.description,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.body1,
            modifier = modifier.padding(horizontal = 10.dp)
        )
    }
}

@ExperimentalAnimationApi
@Composable
fun ButtonForBottomForInfoPage(
    statePage: Int
) {
    AnimatedVisibility(
        visible = statePage != 2,
        enter = fadeIn(animationSpec = tween(durationMillis = 2000)),
        exit = fadeOut(animationSpec = tween(durationMillis = 250))
    ) {
        MainButtonsInRow(
            textForOutlineBtn = stringResource(id = R.string.skip),
            textForMainBtn = stringResource(id = R.string.next),
            weightOfFirstButton = 0.45f,
            onClickForOutlineBtn = { /*TODO*/ },
            onClickForMainBtn = { /*TODO*/ },
            enableStateForOutlineBtn = true,
            enableStateForMainBtn = true
        )
    }
    AnimatedVisibility(
        visible = statePage == 2,
        enter = fadeIn(animationSpec = tween(durationMillis = 2000)),
        exit = fadeOut(animationSpec = tween(durationMillis = 250))
    ) {
        MainButton(
            text = stringResource(id = R.string.understand_thanks),
            onClick = { /*TODO*/ }, enableState = true,
        )
    }
}
