package com.example.sauexpert.bracelet_indicator

import android.graphics.Paint
import androidx.compose.animation.core.FloatTweenSpec
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Circle
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sauexpert.R
import com.example.sauexpert.model.ListNumberOfYForTableData
import com.example.sauexpert.model.SleepData
import com.example.sauexpert.ui.theme.Gray30


@Composable
fun SleepScreen() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .background(
                color = Color.White,
                shape = RoundedCornerShape(7.dp)
            )
            .padding(16.dp).padding(bottom = 60.dp)
    ) {
        SleepStat()
        Spacer(modifier = Modifier.height(12.dp))
        BarChartForSleep(
            SleepData = listOf(
                SleepData(positionOnX = 10f, hourOfSleep = 140f, dateName = "Пн"),
                SleepData(positionOnX = 100f, hourOfSleep = 200f, dateName = "Вт"),
                SleepData(
                    positionOnX = 190f,
                    hourOfSleep = 190f,
                    startTime = 40f,
                    dateName = "Ср"
                ),
                SleepData(
                    positionOnX = 280f,
                    hourOfSleep = 180f,
                    startTime = 60f,
                    dateName = "Чт"
                ),
                SleepData(positionOnX = 370f, hourOfSleep = 220f, dateName = "Пт"),
                SleepData(
                    positionOnX = 460f,
                    hourOfSleep = 240f,
                    startTime = 80f,
                    dateName = "Сб"
                ),
                SleepData(positionOnX = 550f, hourOfSleep = 370f, dateName = "Вс")
            ),
            ListNumberData = listOf(
                ListNumberOfYForTableData("22:00"),
                ListNumberOfYForTableData("00:00"),
                ListNumberOfYForTableData("02:00"),
                ListNumberOfYForTableData("04:00"),
                ListNumberOfYForTableData("06:00"),
                ListNumberOfYForTableData("08:00"),
                ListNumberOfYForTableData("10:00"),
                ListNumberOfYForTableData("12:00"),
            )

        )
        Spacer(modifier = Modifier.height(25.dp))
        SleepStat2()
        Spacer(modifier = Modifier.height(16.dp))
        ProgressBar(
            deepSleepValue = 120,
            deepSleepPercent = 40,
            lightSleepValue = 115,
            lightSleepPercent = 30
        )

    }
}

@Composable
fun SleepStat(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Text(
            text = stringResource(id = R.string.sleep),
            style = MaterialTheme.typography.caption
        )

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
                .fillMaxWidth()
        ) {
            Icon(
                imageVector = Icons.Filled.Circle,
                contentDescription = "",
                tint = Color.Red.copy(alpha = 0.25f),
                modifier = modifier.size(9.dp)
            )

            Spacer(modifier = Modifier.width(2.dp))

            Text(
                text = stringResource(id = R.string.sleep_duration),
                style = MaterialTheme.typography.subtitle1,
                fontWeight = FontWeight.Bold,
                color = Gray30,
//                inlineContent = inlineContent
            )

        }

        Text(
            text = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        color = Color.Black,
                        fontSize = 34.sp
                    )
                ) {
                    append("6")
                }
                append("ч ")
                withStyle(
                    style = SpanStyle(
                        color = Color.Black,
                        fontSize = 34.sp
                    )
                ) {
                    append("36")
                }
                append("мин")
            },
            style = MaterialTheme.typography.subtitle1,
            fontWeight = FontWeight.Bold,
            color = Gray30
        )

        Text(
            text = "18-20 ноября 2021",
            style = MaterialTheme.typography.subtitle1,
            fontWeight = FontWeight.Bold,
            color = Gray30
        )

    }

}


@Composable
fun BarChartForSleep(
    SleepData: List<SleepData>,
    ListNumberData: List<ListNumberOfYForTableData>
) {
    var start by remember { mutableStateOf(false) }
    val heightPre by animateFloatAsState(
        targetValue = if (start) 1f else 0f,
        animationSpec = FloatTweenSpec(duration = 1000)
    )

    Canvas(
        modifier = Modifier
            .fillMaxWidth().height(250.dp)
    ) {
        var height = 0
        val paint = Paint().apply {
            textAlign = Paint.Align.CENTER
            textSize = 34f
//            color = Color(0xFF0018A8).toArgb()
        }



        for (i in ListNumberData) {
            drawLine(
                start = Offset(10f, height.dp.toPx()),
                end = Offset(780f, height.dp.toPx()),
                color = Gray30,
                strokeWidth = 2f
            )

            drawContext.canvas.nativeCanvas.drawText(
                "${i.number}",
                320.dp.toPx(),
                (10 + height).dp.toPx(),
                paint
            )

            height += 34
        }

        drawLine(
            start = Offset(10f, (height - 34).dp.toPx()),
            end = Offset(10f, 0f),
            color = Gray30,
            strokeWidth = 2f
        )

        start = true
        for (p in SleepData) {
            drawRect(
                color = Color.Red,
                topLeft = Offset(p.positionOnX + 20, p.startTime * heightPre),
                size = Size(60f, p.hourOfSleep * heightPre)

            )

            drawContext.canvas.nativeCanvas.drawText(
                "${p.dateName}",
                p.positionOnX + 45,
                (height - 14).dp.toPx(),
                paint
            )
        }
    }
}

@Composable
fun SleepStat2(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .size(width = 300.dp, height = 50.dp)
            .background(
                color = Gray30.copy(alpha = 0.19f),
                shape = RoundedCornerShape(7.dp)
            )
    ) {
        Text(
            text = stringResource(R.string.woke_up_in_middle),
            style = MaterialTheme.typography.button,
            modifier = modifier
                .align(Alignment.CenterStart)
                .padding(horizontal = 16.dp)
        )

        Text(
            text = "3 раза",
            style = MaterialTheme.typography.subtitle2,
            fontSize = 15.sp,
            modifier = modifier
                .align(Alignment.CenterEnd)
                .padding(horizontal = 16.dp)
        )
    }
}

@Composable
fun ProgressBar(
    modifier: Modifier = Modifier,
    deepSleepValue: Int = 0,
    deepSleepPercent: Int = 0,
    lightSleepValue: Int = 0,
    lightSleepPercent: Int = 0,

    ) {

    Row(
        modifier = modifier.fillMaxWidth()
    ) {
        Column() {
            LinearProgressIndicator(
                progress = 1f,
                color = Color.Blue,
                modifier = modifier.size(height = 6.dp, width = deepSleepValue.dp)
            )

            Text(
                text = stringResource(R.string.deep_sleep),
                style = MaterialTheme.typography.button,
                color = Color.Blue
            )

            Text(
                text = "$deepSleepPercent%",
                style = MaterialTheme.typography.button,
            )

        }

        Column() {
            LinearProgressIndicator(
                progress = 1f,
                color = Color.Cyan,
                modifier = modifier.size(height = 6.dp, width = lightSleepValue.dp)
            )

            Text(
                text = stringResource(R.string.light_sleep),
                style = MaterialTheme.typography.button,
                color = Color.Cyan
            )

            Text(
                text = "$lightSleepPercent%",
                style = MaterialTheme.typography.button,
            )

        }

        Column() {
            LinearProgressIndicator(
                progress = 1f,
                color = Color.Gray,
                modifier = modifier.size(height = 6.dp, width = 115.dp)
            )

            Text(
                text = stringResource(R.string.light_sleep),
                style = MaterialTheme.typography.button,
                color = Color.Gray
            )

            Text(
                text = "30%",
                style = MaterialTheme.typography.button,
            )
        }
    }
}