package com.example.sauexpert.patient_card_screen

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sauexpert.R
import com.example.sauexpert.ui.theme.Gray30
import com.example.sauexpert.ui.theme.Orange4294
import com.example.sauexpert.ui.theme.Pink20p
import com.example.sauexpert.ui.theme.Pink4294

@Composable
fun IndicatorPatientCardScreen() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .padding(bottom = 70.dp, top = 24.dp, start = 16.dp, end = 16.dp)
    ) {
        BraceletIndicatorCell()
        Spacer(modifier = Modifier.height(24.dp))
        IndicatorInfromationSection()
        Spacer(modifier = Modifier.height(24.dp))
        DailyReportInfromation()


    }
}


@Composable
fun BraceletIndicatorCell(
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .fillMaxWidth()
            .clickable { }
            .background(
                color = Gray30.copy(alpha = 0.19f),
                shape = RoundedCornerShape(10.dp)
            )
            .padding(horizontal = 24.dp, vertical = 27.dp)

    ) {
        Icon(
            painter = painterResource(R.drawable.ic_applewatch),
            contentDescription = "",
            tint = Color.Black,
            modifier = modifier.size(20.dp)
        )

        Spacer(modifier = Modifier.width(15.dp))


        Text(
            text = stringResource(R.string.bracelet_indicator),
            style = MaterialTheme.typography.subtitle2
        )

        Spacer(modifier = Modifier.weight(1f))


        Icon(
            imageVector = Icons.Filled.KeyboardArrowRight,
            contentDescription = "",
            tint = Color.Black,
            modifier = modifier.size(20.dp)
        )
    }
}

@Composable
fun IndicatorInfromationSection(
    modifier: Modifier = Modifier
) {

    val configuration = LocalConfiguration.current
    val screenWidth = (configuration.screenWidthDp.dp / 2) - 25.dp


    Column(modifier = modifier.fillMaxWidth()) {

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
            CardItemForPatientCard(
                title = stringResource(R.string.glucose_before_meal),
                subtitle = stringResource(R.string.millimoles_per_liter),
                textValue = "7.5",
                textStatus = stringResource(R.string.fine),
                dateText = "15 Октября 15:00",
                modifier = modifier.width(screenWidth),

                )

            CardItemForPatientCard(
                title = stringResource(R.string.glucose_after_meal),
                subtitle = stringResource(R.string.millimoles_per_liter),
                textValue = "8.0",
                textStatus = stringResource(R.string.low),
                dateText = "15 Октября 15:00",
                modifier = modifier.width(screenWidth),
                color = Orange4294
            )
        }

        Spacer(modifier = Modifier.height(13.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = modifier.fillMaxWidth()
        ) {
            CardItemForPatientCard(
                title = stringResource(R.string.pulse),
                subtitle = stringResource(R.string.pulse_in_minute),
                textValue = "7.5",
                textStatus = stringResource(R.string.high),
                dateText = "15 Октября 15:00",
                modifier = modifier.width(screenWidth),
                color = Orange4294
            )

            CardItemForPatientCard(
                title = stringResource(R.string.arterial_pressure),
                subtitle = stringResource(R.string.mmhg),
                textValue = "8.0",
                textStatus = stringResource(R.string.low),
                dateText = "15 Октября 15:00",
                modifier = modifier.width(screenWidth),
                color = Orange4294
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        ProgressBarForSteps(stepPercent = 0.5f, stepValue = 2000)

    }
}


@Composable
fun ProgressBarForSteps(
    modifier: Modifier = Modifier,
    stepValue: Int = 0,
    stepPercent: Float = 0f,
) {
    Card(
        shape = RoundedCornerShape(10.dp),
    ) {
        Column(
            modifier = modifier.fillMaxWidth()
                .border(
                    width = 1.dp,
                    color = Gray30.copy(alpha = 0.35f),
                    shape = RoundedCornerShape(10.dp)
                )
                .padding(16.dp)
        ) {
            Text(
                text = stringResource(R.string.steps),
                style = MaterialTheme.typography.h5,
                fontSize = 13.sp,
            )

            Spacer(modifier = Modifier.height(8.dp))


            Text(
                text = "$stepValue из 10 000",
                style = MaterialTheme.typography.subtitle2,
                fontSize = 22.sp,
            )

            Spacer(modifier = Modifier.height(8.dp))


            LinearProgressIndicator(
                progress = stepPercent,
                color = Color.Black,
                modifier = modifier
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
    }
}


@Composable
fun DailyReportInfromation(
    modifier: Modifier = Modifier
) {

    val configuration = LocalConfiguration.current
    val screenWidth = (configuration.screenWidthDp.dp / 2) - 25.dp


    Column(modifier = modifier.fillMaxWidth()) {

        Text(
            text = stringResource(R.string.daily_reports),
            style = MaterialTheme.typography.subtitle2,
        )

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = modifier.fillMaxWidth()
        ) {
            CardItemWithIconForPatientCard(
                title = stringResource(R.string.well_being),
                icon = Icons.Filled.ThumbUp,
                textValue = "Отлично",
                dateText = "15 Октября 15:00",
                modifier = modifier.width(screenWidth).height(240.dp)
            )


            Column(

            ) {
                CardItemForPatientCard(
                    title = stringResource(R.string.fulfillment_prescription),
                    subtitle = "",
                    textValue = "70%",
                    modifier = modifier.width(screenWidth).height(112.dp)
                )

                Spacer(modifier = Modifier.height(13.dp))

                CardItemWithGraphForPatientCard(
                    title = stringResource(R.string.weight),
                    subtitle = stringResource(R.string.kg),
                    textValue = "75",
                    textValue2 = "+2.3",
                    ListNumberData = listOf(10f, 15f, 13f, 25f, 30f),
                    dateText = "15 Октября 15:00",
                    modifier = modifier.width(screenWidth).height(112.dp)

                )
            }
        }

        Spacer(modifier = Modifier.height(13.dp))

        CriticalCaseCell(month = "сентябрь")

    }
}

@Composable
fun CardItemForPatientCard(
    title: String,
    subtitle: String,
    textValue: String,
    textStatus: String? = null,
    dateText: String? = null,
    color: Color = Color.Green,
    modifier: Modifier = Modifier
) {
    Card(
        shape = RoundedCornerShape(10.dp),
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = modifier
                .border(
                    width = 1.dp,
                    color = Gray30.copy(alpha = 0.35f),
                    shape = RoundedCornerShape(10.dp)
                )
                .height(160.dp)
                .padding(16.dp)
        ) {
            Text(
                text = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            color = Color.Black,
                            fontSize = 13.sp
                        )
                    ) {
                        append(title)
                    }

                    append(" $subtitle")
                },
                style = MaterialTheme.typography.button,
                fontSize = 13.sp,
                color = Gray30
            )

            Text(
                text = textValue,
                style = MaterialTheme.typography.caption,
            )

            textStatus?.let {
                Text(
                    text = textStatus,
                    style = MaterialTheme.typography.body2,
                    fontSize = 13.sp,
                    color = color
                )
            }

            dateText?.let {
                Text(
                    text = dateText,
                    style = MaterialTheme.typography.button,
                    fontSize = 13.sp,
                    color = Gray30
                )
            }
        }
    }
}


@Composable
fun CardItemWithIconForPatientCard(
    title: String,
    icon: ImageVector,
    textValue: String,
    dateText: String? = null,
    modifier: Modifier = Modifier
) {
    Card(
        shape = RoundedCornerShape(10.dp),
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = modifier
                .border(
                    width = 1.dp,
                    color = Gray30.copy(alpha = 0.35f),
                    shape = RoundedCornerShape(10.dp)
                )
                .height(160.dp)
                .padding(16.dp)
        ) {

            Text(
                text = title,
                style = MaterialTheme.typography.button,
                fontSize = 13.sp,
            )

            Spacer(modifier = Modifier.weight(1f))

            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = Color.Black,
            )

            Spacer(modifier = Modifier.height(12.dp))


            Text(
                text = textValue,
                style = MaterialTheme.typography.caption,
            )

            Spacer(modifier = Modifier.weight(1f))

            dateText?.let {
                Text(
                    text = dateText,
                    style = MaterialTheme.typography.button,
                    fontSize = 13.sp,
                    color = Gray30
                )
            }
        }
    }
}

@Composable
fun CardItemWithGraphForPatientCard(
    title: String,
    subtitle: String,
    textValue: String,
    textValue2: String,
    dateText: String? = null,
    ListNumberData: List<Float>,
    modifier: Modifier = Modifier
) {
    val scale by remember { mutableStateOf(1f) }
    var wight = 0f
    val path = Path()
    for ((index, item) in ListNumberData.withIndex()) {
        if (index == 0) {
            path.moveTo(0f * scale, 70f - item)
            wight += 20f
        } else {
            path.lineTo(wight * scale, 70f - item)
            wight += 20f
        }
    }


    Card(
        shape = RoundedCornerShape(10.dp),
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = modifier
                .border(
                    width = 1.dp,
                    color = Gray30.copy(alpha = 0.35f),
                    shape = RoundedCornerShape(10.dp)
                )
                .height(160.dp)
                .padding(16.dp)
        ) {
            Text(
                text = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            color = Color.Black,
                            fontSize = 13.sp
                        )
                    ) {
                        append(title)
                    }

                    append(" $subtitle")
                },
                style = MaterialTheme.typography.button,
                fontSize = 13.sp,
                color = Gray30
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
//                horizontalArrangement = Arrangement.SpaceEvenly,
            ) {
                Text(
                    text = textValue,
                    style = MaterialTheme.typography.caption,
                )

                Spacer(modifier = Modifier.width(12.dp))

                Canvas(
                    modifier = Modifier
                        .width(36.dp)
                        .height(36.dp)
                        .background(Color.White)
                ) {

                    drawPath(
                        path = path,
                        color = Color.Red,
                        style = Stroke(width = 2f)
                    )
                }

                Spacer(modifier = Modifier.width(4.dp))


                Text(
                    text = textValue2,
                    style = MaterialTheme.typography.body2,
                    fontSize = 13.sp,
                    color = Gray30
                )

            }



            dateText?.let {
                Text(
                    text = dateText,
                    style = MaterialTheme.typography.button,
                    fontSize = 13.sp,
                    color = Gray30
                )
            }
        }
    }
}


@Composable
fun CriticalCaseCell(
    month: String,
    modifier: Modifier = Modifier
) {
    Card(
        shape = RoundedCornerShape(10.dp),
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .border(
                    width = 1.dp,
                    color = Gray30.copy(alpha = 0.35f),
                    shape = RoundedCornerShape(10.dp)
                )
                .padding(16.dp)
        ) {

            Text(
                text = stringResource(R.string.critical_case),
                style = MaterialTheme.typography.subtitle2,
            )

            Spacer(modifier = Modifier.height(16.dp))

            CriticalCaseStat(
                text = stringResource(R.string.hypoglycemia),
                textValue = 5
            )

            Spacer(modifier = Modifier.height(14.dp))

            CriticalCaseStat(
                text = stringResource(R.string.hyperglycemia),
                textValue = 4
            )

            Spacer(modifier = Modifier.height(14.dp))

            CriticalCaseStat(
                text = stringResource(R.string.hypertension),
                textValue = 8
            )

            Spacer(modifier = Modifier.height(14.dp))

            CriticalCaseStat(
                text = stringResource(R.string.hypotension),
                textValue = 3
            )

            Spacer(modifier = Modifier.height(19.dp))


            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = modifier
                    .fillMaxWidth()
                    .background(
                        color = Gray30.copy(alpha = 0.19f),
                        shape = RoundedCornerShape(10.dp)
                    )
                    .padding(horizontal = 15.dp, vertical = 11.dp)
            ) {
                Text(
                    text = stringResource(R.string.data_for),
                    style = MaterialTheme.typography.body1
                )


                Text(
                    text = month,
                    style = MaterialTheme.typography.body1,
                    color = Gray30
                )
            }

        }
    }
}


@Composable
fun CriticalCaseStat(
    text: String,
    textValue: Int,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier.fillMaxWidth()
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.h6,
            fontSize = 11.sp,
            modifier = Modifier.drawPinkBar((24 * textValue)).padding(start = 11.dp),
        )

        Text(
            text = "$textValue",
            style = MaterialTheme.typography.h6,
            fontSize = 13.sp,
            color = Gray30
        )
    }

}


fun Modifier.drawPinkBar(width: Int) = this.drawBehind {
    val colorOfLine = Color.Red.copy(alpha = 0.1f)

    drawRect(
        color = colorOfLine,
        topLeft = Offset(
            x = 0.dp.toPx(),
            y = -4.dp.toPx(),
        ),
        size = Size(
            width = width.dp.toPx(),
            height = 24.dp.toPx()
        )
    )
}