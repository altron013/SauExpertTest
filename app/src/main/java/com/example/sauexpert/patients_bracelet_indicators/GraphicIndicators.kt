package com.example.sauexpert.patients_bracelet_indicators

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import com.example.sauexpert.profile.ProfileForInspection
import com.example.sauexpert.ui.theme.Gray30
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
    val lastDatePercentage: Float,
    val firstDatePercentage: Float
)

@Composable
fun GraphicIndicators() {
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
//            .background(SurfaceE5E5E5),
    ) {
        MainToolbar(onBackClick = {})
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


@Composable
fun IndicatorCard(
    image: Painter,
    date: String,
    title: String,
    percentage: Float? = null,
    progressBarPercentage: Float? = null,
    progressBarValue: Int? = null,
    painterContent: Painter? = null
) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Column(
                modifier = Modifier
                    .weight(9f)
                    .padding(8.dp)
            ) {
                ProfileForInspection(
                    text = percentage ?: 0f,
                    content = title,
                    image = image,
                    showPercentage = true,
                    painter = painterContent
                )
                if (progressBarPercentage != null && progressBarValue != null) {
                    Column(
                        modifier = Modifier
                            .padding(start = 30.dp)
                    ) {
                        Text(
                            text = "$progressBarValue из 100",
                            style = MaterialTheme.typography.subtitle2,
                            fontSize = 22.sp
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        LinearProgressIndicator(
                            progress = progressBarPercentage,
                            color = Color.Black,
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
                    }
                } else {
                    Text(
                        text = "Последний замер $date",
                        fontSize = 13.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(start = 29.dp)
                    )
                }
            }
            Icon(
                imageVector = Icons.Default.ArrowForwardIos,
                contentDescription = null,
                tint = Color.LightGray,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
fun DiseaseCard(
    lastDatePercentage: Float,
    firstDatePercentage: Float,
    diseaseName: String,
    date: String,
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
                CircularProgressBar(
                    percentage = lastDatePercentage,
                    number = 10,
                    showPercentage = true,
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
                CircularProgressBar(
                    percentage = firstDatePercentage,
                    number = 10,
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

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun WorkAroundExample(items: List<CriticalCaseIndicators>) {
    val itemSize: Dp = LocalConfiguration.current.screenWidthDp.dp / 2
    FlowRow(
        mainAxisSize = SizeMode.Expand,
        mainAxisAlignment = FlowMainAxisAlignment.SpaceBetween
    ) {
        for (i in items) {
            DiseaseCard(
                diseaseName = i.diseaseName,
                lastDatePercentage = i.lastDatePercentage,
                firstDatePercentage = i.firstDatePercentage,
                date = i.lastDate,
                modifier = Modifier
                    .size(itemSize)
                    .padding(10.dp)
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun WellBeingCards(items: List<CriticalCaseIndicators>) {
    val itemSize: Dp = LocalConfiguration.current.screenWidthDp.dp / 2
    FlowRow(
        mainAxisSize = SizeMode.Expand,
        mainAxisAlignment = FlowMainAxisAlignment.SpaceBetween
    ) {
        for (i in items) {
            DiseaseCard(
                diseaseName = i.diseaseName,
                lastDatePercentage = i.lastDatePercentage,
                firstDatePercentage = i.firstDatePercentage,
                date = i.lastDate,
                modifier = Modifier
                    .size(itemSize)
                    .padding(10.dp,bottom=0.dp)
            )
        }
    }
}


