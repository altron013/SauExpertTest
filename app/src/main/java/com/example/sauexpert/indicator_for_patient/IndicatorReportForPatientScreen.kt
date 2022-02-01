package com.example.sauexpert.indicator_for_patient

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.runtime.Composable
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
import com.example.sauexpert.profile.CircularProgressBar
import com.example.sauexpert.ui.theme.Gray30
import com.example.sauexpert.ui.theme.Green117259
import com.google.accompanist.flowlayout.FlowMainAxisAlignment
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.flowlayout.SizeMode


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

    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .background(
                color = Gray30.copy(alpha = 0.19f)
            ),
    ) {
        TopBarForIndicatorForPatient()

        Spacer(modifier = Modifier.height(16.dp))

        Column(
            modifier = Modifier.padding(horizontal = 20.dp)
        ) {

            GraphReportSection()

            Divider(
                modifier = Modifier.padding(bottom = 10.dp),
                color = Color.LightGray
            )

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
fun GraphReportSection(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Text(
            text = stringResource(id = R.string.graphic_indicators),
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp
        )

        Spacer(modifier = Modifier.height(28.dp))

        IndicatorCardForReport(
            image = painterResource(R.drawable.ic_report_glukoza),
            title = stringResource(id = R.string.glucose_level),
            date = "22.08.2020 | 14:00",
            percentage = 0.06f,
            showFullProgress = true
        )

        Spacer(modifier = Modifier.height(17.dp))

        IndicatorCardForReport(
            image = painterResource(R.drawable.ic_report_davlenie),
            title = stringResource(id = R.string.arterial_pressure),
            date = "22.08.2020 | 14:00",
            percentage = 120f,
            secondPercentage = 80f,
            showDividedNumber = true
        )

        Spacer(modifier = Modifier.height(17.dp))

        IndicatorCardForReport(
            image = painterResource(R.drawable.ic_report_pulse),
            title = stringResource(id = R.string.pulse),
            date = "22.08.2020 | 14:00",
            percentage = 0.86f,
            showFullProgress = true
        )

        Spacer(modifier = Modifier.height(17.dp))

        IndicatorCardForReport(
            image = painterResource(R.drawable.ic_report_shagi),
            title = stringResource(id = R.string.steps),
            percentage = 0.68f,
            progressBarPercentage = 0.68f,
            progressBarValue = 6700
        )
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

        IndicatorCardForReport(
            image = painterResource(R.drawable.ic_report_samochuvstvie),
            title = stringResource(id = R.string.steps),
            painterContent = painterResource(R.drawable.ic_report_samochuvstvie),
            subtitleForDate = stringResource(R.string.last_mark),
            date = "22.08.2020 | 21:00",
            showArrowForwardIcon = false
        )

        Spacer(modifier = Modifier.height(17.dp))

        IndicatorCardForReport(
            image = painterResource(R.drawable.ic_report_list_check),
            title = stringResource(id = R.string.task_completing),
            percentage = 0.2f,
            painterContent = painterResource(R.drawable.ic_report_list_check),
            subtitleForDate = stringResource(R.string.average_for_week),
            date = "85%",
            showArrowForwardIcon = false
        )

        Spacer(modifier = Modifier.height(17.dp))

        IndicatorCardForReport(
            image = painterResource(R.drawable.ic_report_wes),
            title = stringResource(id = R.string.weight),
            percentage = 0.75f,
            painterContent = painterResource(R.drawable.ic_report_wes),
            subtitleForDate = stringResource(R.string.last_measurements),
            date = "22.08.2020 | 14:00",
            showArrowForwardIcon = false,
            showFullProgress = true
        )
    }
}


@Composable
fun IndicatorCardForReport(
    image: Painter,
    date: String? = null,
    subtitleForDate: String = stringResource(R.string.last_measurements),
    title: String,
    percentage: Float? = null,
    secondPercentage: Float? = null,
    progressBarPercentage: Float? = null,
    progressBarValue: Int? = null,
    painterContent: Painter? = null,
    showArrowForwardIcon: Boolean = true,
    showFullProgress: Boolean = false,
    showDividedNumber: Boolean = false,
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
                horizontalAlignment = Alignment.End,
                modifier = modifier.weight(0.9f)
            ) {
                IndicatorReportWithProgress(
                    numberPercentage = percentage ?: 0f,
                    secondNumberPercentage = secondPercentage ?: 0f,
                    content = title,
                    image = image,
                    showPercentage = true,
                    painter = painterContent,
                    showFullProgress = showFullProgress,
                    showDividedNumber = showDividedNumber
                )

                Spacer(modifier = Modifier.height(18.dp))

                if (progressBarPercentage != null && progressBarValue != null) {

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = modifier.fillMaxWidth()
                    ) {

                        Text(
                            text = "$progressBarValue",
                            style = MaterialTheme.typography.subtitle2,
                            fontSize = 22.sp,
                            color = Green117259
                        )

                        Text(
                            text = "10000",
                            style = MaterialTheme.typography.subtitle2,
                            fontSize = 22.sp
                        )

                    }

                    Spacer(modifier = Modifier.height(8.dp))
                    LinearProgressIndicator(
                        progress = progressBarPercentage,
                        color = Green117259,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(height = 6.dp)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = stringResource(R.string.goal_for_today),
                        style = MaterialTheme.typography.h5,
                        fontSize = 13.sp,
                        color = Gray30
                    )
                } else {
                    Text(
                        text = "$subtitleForDate $date",
                        fontSize = 13.sp,
                        textAlign = TextAlign.Center
                    )
                }
            }

            if (showArrowForwardIcon) {
                Spacer(modifier = Modifier.width(10.dp))
                Icon(
                    imageVector = Icons.Default.ArrowForwardIos,
                    contentDescription = null,
                    tint = Color.LightGray,
                    modifier = Modifier.weight(0.1f).size(width = 10.dp, height = 16.dp)
                )
            }
        }
    }
}


@Composable
fun IndicatorReportWithProgress(
    content: String,
    numberPercentage: Float,
    secondNumberPercentage: Float,
    showPercentage: Boolean = false,
    showDividedNumber: Boolean = false,
    showFullProgress: Boolean = false,
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

        Spacer(modifier = Modifier.width(12.dp))

        Text(
            text = content,
            style = MaterialTheme.typography.subtitle2,
            modifier = Modifier.weight(3f)
        )
        Spacer(modifier = Modifier.weight(1f))
        if (numberPercentage != 0f && !showFullProgress && !showDividedNumber) {
            CircularProgressBar(
                percentage = numberPercentage,
                number = 100,
                showPercentage = showPercentage,
                radius = 20.dp
            )
        } else if (numberPercentage != 0f && showFullProgress) {
            CircularProgressBarWithFullProgress(
                number = (numberPercentage * 100).toInt(),
                showPercentage = true,
                radius = 20.dp
            )

        } else if (numberPercentage != 0f && showDividedNumber) {
            DividedNumber(numberPercentage.toInt(), secondNumberPercentage.toInt())
        } else {
            painter?.let {
                Icon(
                    painter = it,
                    contentDescription = null,
                    tint = Color.Black,
                    modifier = modifier.size(20.dp)
                )
            }
        }
    }
}


@Composable
fun DividedNumber(
    number: Int,
    number2: Int,

    ) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = number.toString(),
            style = MaterialTheme.typography.body1,
            color = Green117259
        )

        Divider(
            modifier = Modifier.size(width = 31.dp, height = 2.dp),
            color = Green117259
        )

        Text(
            text = number2.toString(),
            style = MaterialTheme.typography.body1,
            color = Green117259
        )
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