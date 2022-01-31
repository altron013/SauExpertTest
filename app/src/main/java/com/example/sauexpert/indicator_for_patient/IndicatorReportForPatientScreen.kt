package com.example.sauexpert.indicator_for_patient

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sauexpert.R
import com.example.sauexpert.patients_bracelet_indicators.CriticalCaseIndicators
import com.example.sauexpert.patients_bracelet_indicators.IndicatorCard
import com.example.sauexpert.patients_bracelet_indicators.IndicatorCardFields
import com.example.sauexpert.patients_bracelet_indicators.WorkAroundExample
import com.example.sauexpert.ui.theme.Gray30
import com.example.sauexpert.ui.theme.SurfaceE5E5E5
import com.example.sauexpert.widgets.compose.Toolbars.MainToolbar

@Composable
fun IndicatorReportForPatientScreen(){
    val criticalIndicatorsList = listOf(
        CriticalCaseIndicators(
            diseaseName = stringResource(id = R.string.hyperglycemia),
            lastDate = "22.08.2020 | 21:00",
            lastDatePercentage = 0.1f,
            firstDatePercentage = 0.1f
        ),
        CriticalCaseIndicators(
            diseaseName = stringResource(id = R.string.hyperglycemia),
            lastDate = "02.08.2021 | 21:00",
            lastDatePercentage = 0.2f,
            firstDatePercentage = 0.2f
        ),
        CriticalCaseIndicators(
            diseaseName = stringResource(id = R.string.hypertension),
            lastDate = "18.02.2020 | 21:00",
            lastDatePercentage = 0.5f,
            firstDatePercentage = 0.3f
        ),
        CriticalCaseIndicators(
            diseaseName = stringResource(id = R.string.hypertension),
            lastDate = "22.05.2022 | 21:00",
            lastDatePercentage = 0.9f,
            firstDatePercentage = 0.4f
        )
    )
    val indicatorCardList = listOf(
        IndicatorCardFields(
            image = painterResource(R.drawable.ic_report_glukoza),
            title = stringResource(id = R.string.glucose_level),
            date = "22.08.2020 | 14:00",
            percentage = 0.9f
        ),
        IndicatorCardFields(
            image = painterResource(R.drawable.ic_report_davlenie),
            title = stringResource(id = R.string.arterial_pressure),
            date = "22.08.2020 | 14:00",
            percentage = 0.02f
        ),
        IndicatorCardFields(
            image = painterResource(R.drawable.ic_report_pulse),
            title = stringResource(id = R.string.pulse),
            date = "22.08.2020 | 14:00",
            percentage = 0.01f
        ),
        IndicatorCardFields(
            image = painterResource(R.drawable.ic_report_shagi),
            title = stringResource(id = R.string.steps),
            percentage = 0.068f,
            progressBarPercentage = 0.2f,
            progressBarValue = 20
        ),
        IndicatorCardFields(
            image = painterResource(R.drawable.ic_report_samochuvstvie),
            title = stringResource(id = R.string.steps),
            progressBarPercentage = 0.2f,
            progressBarValue = 20,
            image2 = painterResource(R.drawable.ic_report_samochuvstvie)
        ),
        IndicatorCardFields(
            image = painterResource(R.drawable.ic_report_list_check),
            title = stringResource(id = R.string.steps),
            progressBarPercentage = 0.2f,
            progressBarValue = 20,
            image2 = painterResource(R.drawable.ic_report_list_check)
        ),
        IndicatorCardFields(
            image = painterResource(R.drawable.ic_report_wes),
            title = stringResource(id = R.string.steps),
            progressBarPercentage = 0.2f,
            progressBarValue = 20,
            image2 = painterResource(R.drawable.ic_report_wes)
        )
    )

    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .background(
                color = Gray30.copy(alpha = 0.19f)
            ),
    ) {
        TopBarForIndicatorForPatient()
        Column(
            modifier = Modifier.padding(top = 16.dp, start = 20.dp, end = 20.dp)
        ) {
            Text(
                text = stringResource(id = R.string.graphic_indicators),
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp
            )
            Spacer(modifier = Modifier.padding(9.dp))
            for (it in indicatorCardList) {
                if (it.percentage != null)
                    IndicatorCard(
                        image = it.image,
                        date = it.date ?: "",
                        title = it.title,
                        percentage = it.percentage ?: 0f,
                        progressBarValue = it.progressBarValue,
                        progressBarPercentage = it.progressBarPercentage
                    )
                Spacer(modifier = Modifier.padding(6.dp))
            }
            Divider(
                modifier = Modifier.padding(bottom = 10.dp),
                color = Color.LightGray
            )
            Text(
                text = stringResource(id = R.string.indicators_per_day),
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp
            )
            for (it in indicatorCardList) {
                if (it.image2 != null) {
                    IndicatorCard(
                        image = it.image,
                        date = it.date ?: "",
                        title = it.title,
                        painterContent = it.image2,
                        percentage = it.percentage
                    )
                    Spacer(modifier = Modifier.padding(9.dp))
                }
            }
            Divider(
                modifier = Modifier.padding(
                    top = 20.dp,
                    bottom = 20.dp
                ),
                color = Color.LightGray
            )
            Text(
                text = stringResource(id = R.string.critical_case_indicators),
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp
            )
        }
        Column(Modifier.fillMaxSize()) {
            WorkAroundExample(criticalIndicatorsList)
        }
    }
}