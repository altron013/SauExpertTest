package com.example.sauexpert.general_report

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.runtime.R
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.sauexpert.model.TextOfTabData
import com.example.sauexpert.patient_card_screen.BeginInspectionScreen
import com.example.sauexpert.patient_card_screen.IndicatorPatientCardScreen
import com.example.sauexpert.patient_card_screen.TabViewForPatientCardScreen
import com.example.sauexpert.ui.theme.Gray30
import com.example.sauexpert.widgets.compose.Toolbars.ActionToolBarWithSubtitle
import com.example.sauexpert.widgets.compose.buttons.MainButtonsInRow

@Composable
fun GeneralReportScreen() {
    var selectedTabIndex by remember {
        mutableStateOf(0)
    }

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
            )


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