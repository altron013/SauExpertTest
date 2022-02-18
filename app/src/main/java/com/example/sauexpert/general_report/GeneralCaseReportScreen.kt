package com.example.sauexpert.general_report

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.sauexpert.R
import com.example.sauexpert.patient_card_screen.*
import com.example.sauexpert.patient_card_screen.IndicatorInfromationSection
import com.example.sauexpert.ui.theme.Orange4294

@Composable
fun GeneralCaseReportScreen() {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .padding(vertical = 24.dp, horizontal = 16.dp)
    ) {
        BraceletIndicatorCell(
            text = stringResource(R.string.risk_factor),
            backgroundColor = Color.White
        )
        Spacer(modifier = Modifier.height(24.dp))
        IndicatorInfromationForMonthSection()

    }

}


@Composable
fun IndicatorInfromationForMonthSection(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {

        Text(
            text = stringResource(R.string.patient_indicator),
            style = MaterialTheme.typography.subtitle2,
        )

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = modifier.fillMaxWidth()
        ) {

        }

        Spacer(modifier = Modifier.height(13.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = modifier.fillMaxWidth()
        ) {

        }

        Spacer(modifier = Modifier.height(16.dp))

        ProgressBarForSteps(stepPercent = 0.5f, stepValue = 2000)

    }
}