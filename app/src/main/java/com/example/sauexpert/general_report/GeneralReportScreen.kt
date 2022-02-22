package com.example.sauexpert.general_report

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.sauexpert.dimensions.smallDimensions
import com.example.sauexpert.dimensions.sw360Dimensions
import com.example.sauexpert.model.TextOfTabData
import com.example.sauexpert.patient_card_screen.TabViewForPatientCardScreen
import com.example.sauexpert.ui.theme.Gray30
import com.example.sauexpert.widgets.compose.Toolbars.ActionToolBarWithSubtitle


@Composable
fun GeneralReportScreen() {
    var selectedTabIndex by remember {
        mutableStateOf(0)
    }

    val configuration = LocalConfiguration.current
    val dimensions = if (configuration.screenWidthDp <= 360) smallDimensions else sw360Dimensions

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Gray30.copy(alpha = 0.19f))
    ) {

        Spacer(modifier = Modifier.height(16.dp))

        ActionToolBarWithSubtitle(
            titleText = "Zhanna Akhmetova",
            subtitleText = "Общая оценка: 90 из 100",
            iconBackClick = Icons.Default.ArrowBack,
            onBackClick = {},
            onRightClick = {}
        )

        Spacer(modifier = Modifier.height(28.dp))

        TabViewForPatientCardScreen(
            TextOfTab = listOf(
                TextOfTabData(
                    text = stringResource(id = com.example.sauexpert.R.string.general)
                ),
                TextOfTabData(
                    text = stringResource(id = com.example.sauexpert.R.string.appointments)
                ),
                TextOfTabData(
                    text = stringResource(id = com.example.sauexpert.R.string.severely_case)
                )
            ),
            dimensions = dimensions

        ) {
            selectedTabIndex = it
        }
        when (selectedTabIndex) {
            0 -> Box(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                GeneralCaseReportScreen()
            }

            1 -> Box(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                PrescriptionReportScreen()
            }
            2 -> Box(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                CriticalCaseReportScreen()

            }
        }

    }
}