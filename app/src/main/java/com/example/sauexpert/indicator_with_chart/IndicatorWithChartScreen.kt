package com.example.sauexpert.indicator_with_chart

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.sauexpert.R
import com.example.sauexpert.bracelet_indicator.TabViewWithRoundBorder
import com.example.sauexpert.model.TextOfTabData
import com.example.sauexpert.ui.theme.Gray30
import com.example.sauexpert.widgets.compose.Toolbars.ActionToolBar

@ExperimentalMaterialApi
@Composable
fun IndicatorWithChartScreen() {
    var selectedTabIndex by remember {
        mutableStateOf(0)
    }

    Column(
        modifier = Modifier.fillMaxSize()
            .background(
                color = Gray30.copy(alpha = 0.19f)
            )
    ) {
        Spacer(modifier = Modifier.height(5.dp))
        ActionToolBar(
            titleText = stringResource(id = R.string.bracelet),
            iconBackClick = Icons.Default.ArrowBack,
            onBackClick = {},
            onRightClick = {}
        )
        Spacer(modifier = Modifier.height(28.dp))
        TabViewWithRoundBorder(
            TextOfTab = listOf(
                TextOfTabData(
                    text = stringResource(id = R.string.glucose)
                ),
                TextOfTabData(
                    text = stringResource(id = R.string.pressure_pulse)
                ),
                TextOfTabData(
                    text = stringResource(id = R.string.steps)
                )
            )


        ) {
            selectedTabIndex = it
        }
        when (selectedTabIndex) {
            0 ->
                GlucoseScreen()

            1 -> Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
            ) {
                PressureAndPulseScreen()
            }
            2 -> Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp)
            ) {
                StepsScreen()
            }
        }
    }
}