package com.example.sauexpert.indicator_for_patient

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sauexpert.R
import com.example.sauexpert.patients_bracelet_indicators.*
import com.example.sauexpert.patients_bracelet_indicators.WorkAroundExample
import com.example.sauexpert.profile.CircularProgressBar
import com.example.sauexpert.profile.ProfileForInspection
import com.example.sauexpert.profile.RoundImage
import com.example.sauexpert.ui.theme.Gray30
import com.example.sauexpert.ui.theme.Green117259
import com.example.sauexpert.ui.theme.SurfaceE5E5E5
import com.example.sauexpert.widgets.compose.Toolbars.MainToolbar
import com.google.accompanist.flowlayout.FlowMainAxisAlignment
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.flowlayout.SizeMode


data class IndicatorCardFields(
    val image: Painter,
    val title: String,
    val date: String? = null,
    val percentage: Float? = null,
    val progressBarPercentage: Float? = null,
    val progressBarValue: Int? = null,
    val image2: Painter? = null
)

data class CriticalCaseIndicators(
    val diseaseName: String,
    val lastDate: String,
    val lastDatePercentage: Int,
    val firstDatePercentage: Int,
    val color: Color = Green117259
)


@Composable
fun IndicatorReportForPatientScreen() {
    val criticalIndicatorsList = listOf(
        CriticalCaseIndicators(
            diseaseName = stringResource(id = R.string.hyperglycemia),
            lastDate = "22.08.2020 | 21:00",
            lastDatePercentage = 6,
            firstDatePercentage = 22
        ),
        CriticalCaseIndicators(
            diseaseName = stringResource(id = R.string.hyperglycemia),
            lastDate = "02.08.2021 | 21:00",
            lastDatePercentage = 5,
            firstDatePercentage = 23,
            color = Color.Red
        ),
        CriticalCaseIndicators(
            diseaseName = stringResource(id = R.string.hypertension),
            lastDate = "18.02.2020 | 21:00",
            lastDatePercentage = 8,
            firstDatePercentage = 22
        ),
        CriticalCaseIndicators(
            diseaseName = stringResource(id = R.string.hypertension),
            lastDate = "22.05.2022 | 21:00",
            lastDatePercentage = 2,
            firstDatePercentage = 22,
            color = Color.Red
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
//            Text(
//                text = stringResource(id = R.string.indicators_per_day),
//                fontWeight = FontWeight.Bold,
//                fontSize = 24.sp
//            )
//            for (it in indicatorCardList) {
//                if (it.image2 != null) {
//                    IndicatorCardForDailyReport(
//                        image = it.image,
//                        date = it.date ?: "",
//                        title = it.title,
//                        painterContent = it.image2,
//                        percentage = it.percentage
//                    )
//                    Spacer(modifier = Modifier.padding(9.dp))
//                }
//            }

            DailyReportSection()
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
            ReportForCriticalCase(criticalIndicatorsList)
        }
    }
}


@Composable
fun DailyReportSection(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {

        Text(
            text = stringResource(id = R.string.indicators_per_day),
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp
        )

        Spacer(modifier = Modifier.height(28.dp))

        IndicatorCardForDailyReport(
            image = painterResource(R.drawable.ic_report_samochuvstvie),
            title = stringResource(id = R.string.steps),
            painterContent = painterResource(R.drawable.ic_report_samochuvstvie),
            subtitleForDate = stringResource(R.string.last_mark),
            date = "22.08.2020 | 21:00"
        )

        Spacer(modifier = Modifier.height(17.dp))

        IndicatorCardForDailyReport(
            image = painterResource(R.drawable.ic_report_list_check),
            title = stringResource(id = R.string.task_completing),
            percentage = 0.2f,
            painterContent = painterResource(R.drawable.ic_report_list_check),
            subtitleForDate = stringResource(R.string.average_for_week),
            date = "85%"
        )

        Spacer(modifier = Modifier.height(17.dp))

        IndicatorCardForDailyReport(
            image = painterResource(R.drawable.ic_report_wes),
            title = stringResource(id = R.string.weight),
            percentage = 0.7f,
            painterContent = painterResource(R.drawable.ic_report_wes),
            subtitleForDate = stringResource(R.string.last_measurements),
            date = "22.08.2020 | 14:00"
        )
    }

}


@Composable
fun IndicatorCardForDailyReport(
    image: Painter,
    subtitleForDate: String,
    date: String,
    title: String,
    percentage: Float? = null,
    painterContent: Painter? = null,
    modifier: Modifier = Modifier
) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = modifier
                .fillMaxWidth()
                .clickable { }
                .padding(16.dp)

        ) {
            Column(
                modifier = modifier.fillMaxWidth()
            ) {
                IndicatorReportWithProgress(
                    text = percentage ?: 0f,
                    content = title,
                    image = image,
                    showPercentage = true,
                    painter = painterContent
                )

                Spacer(modifier = Modifier.height(18.dp))

                Text(
                    text = "$subtitleForDate $date",
                    fontSize = 13.sp,
                    textAlign = TextAlign.Center,
                    modifier = modifier
//                        .padding(start = 29.dp)
                )

            }
        }
    }
}


@Composable
fun IndicatorReportWithProgress(
    content: String,
    text: Float,
    showPercentage: Boolean = false,
    modifier: Modifier = Modifier,
    image: Painter,
    painter: Painter? = null
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier.fillMaxWidth()
    ) {

        Image(
            painter = image,
            contentDescription = null,
            modifier = modifier.size(30.dp)
        )

        Spacer(modifier = Modifier.width(8.dp))

        Text(
            text = content,
            style = MaterialTheme.typography.subtitle2,
            modifier = Modifier.weight(3f)
        )
        Spacer(modifier = Modifier.weight(1f))
        if (text != 0f) {
            CircularProgressBar(
                percentage = text,
                number = 100,
                showPercentage = showPercentage
            )
        } else {
            painter?.let {
                Icon(
                    painter = it,
                    contentDescription = "",
                    tint = Color.Black,
                    modifier = modifier.size(20.dp)
                )
            }
        }
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ReportForCriticalCase(items: List<CriticalCaseIndicators>) {
    val itemSize: Dp = LocalConfiguration.current.screenWidthDp.dp / 2
    FlowRow(
        mainAxisSize = SizeMode.Expand,
        mainAxisAlignment = FlowMainAxisAlignment.SpaceBetween
    ) {
        for (i in items) {
            DiseaseCardForCriticalCase(
                diseaseName = i.diseaseName,
                lastDatePercentage = i.lastDatePercentage,
                firstDatePercentage = i.firstDatePercentage,
                date = i.lastDate,
                color = i.color,
                modifier = Modifier
                    .size(itemSize)
                    .padding(10.dp)
            )
        }
    }
}

@Composable
fun DiseaseCardForCriticalCase(
    lastDatePercentage: Int,
    firstDatePercentage: Int,
    diseaseName: String,
    date: String,
    color: Color,
    modifier: Modifier
) {
    Card(modifier = modifier.clickable {})
    {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = diseaseName, fontWeight = FontWeight.Bold, fontSize = 17.sp)
            Text(text = stringResource(id = R.string.last_case), fontSize = 13.sp)
            Text(text = date, fontSize = 13.sp)
            Spacer(modifier = Modifier.padding(4.dp))
            Row(
                modifier = Modifier,
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                CircularProgressBarWithFullProgress(
                    number = lastDatePercentage,
                    showPercentage = true,
                    color = color,
                    radius = 20.dp
                )
                Spacer(modifier = Modifier.padding(15.dp))
                Text(
                    text = "Случаи в июне",
                    fontSize = 13.sp
                )
            }
            Spacer(modifier = Modifier.padding(4.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                CircularProgressBarWithFullProgress(
                    number = firstDatePercentage,
                    color = color,
                    showPercentage = true,
                    radius = 20.dp
                )
                Spacer(modifier = Modifier.padding(15.dp))
                Text(
                    text = "Случаи в июне",
                    fontSize = 13.sp
                )
            }
        }
    }
}

@Composable
fun CircularProgressBarWithFullProgress(
    number: Int,
    radius: Dp = 12.dp,
    color: Color = Green117259,
    strokeWidth: Dp = 3.dp,
    showPercentage: Boolean = false
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.size(radius * 2f)
    ) {
        Canvas(modifier = Modifier.size(radius * 2f)) {

            drawArc(
                color = color,
                -90f,
                360f,
                useCenter = false,
                style = Stroke(strokeWidth.toPx(), cap = StrokeCap.Round)
            )
        }
        if (showPercentage) {
            Text(
                text = "$number",
                style = MaterialTheme.typography.h5,
                color = color,
            )
        }
    }
}