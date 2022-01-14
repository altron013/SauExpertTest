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
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
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
import com.example.sauexpert.model.SleepData
import com.example.sauexpert.ui.theme.Gray30


@Composable
fun HRVScreen() {
    Column(
        modifier = Modifier
            .fillMaxWidth().verticalScroll(rememberScrollState())
    ) {
        HRVwithBarChart()
        Spacer(modifier = Modifier.height(24.dp))
        AnalysisHRVStat()
    }
}


@Composable
fun HRVwithBarChart(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = Color.White,
                shape = RoundedCornerShape(7.dp)
            ).padding(16.dp)
    ) {
        HRVStat()
        Spacer(modifier = Modifier.height(12.dp))
        BarChartForHRV(
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
            )
        )
        Spacer(modifier = Modifier.height(280.dp))
    }
}

@Composable
fun HRVStat(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Text(
            text = stringResource(id = R.string.hrv),
            style = MaterialTheme.typography.caption
        )

        Text(
            text = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        color = Color.Black,
                        fontSize = 34.sp
                    )
                ) {
                    append("150 ")
                }

                append(stringResource(R.string.milliseconds_average))
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
fun BarChartForHRV(
    SleepData: List<SleepData>,
) {
    var start by remember { mutableStateOf(false) }
    val heightPre by animateFloatAsState(
        targetValue = if (start) 1f else 0f,
        animationSpec = FloatTweenSpec(duration = 1000)
    )
    Canvas(
        modifier = Modifier
            .fillMaxWidth()
    ) {

        val paint = Paint().apply {
            textAlign = Paint.Align.CENTER
            textSize = 34f
//            color = Color(0xFF0018A8).toArgb()
        }


        drawLine(
            start = Offset(10f, 238.dp.toPx()),
            end = Offset(10f, 0f),
            color = Gray30,
            strokeWidth = 2f
        )


        drawLine(
            start = Offset(10f, 0f),
            end = Offset(780f, 0f),
            color = Gray30,
            strokeWidth = 2f
        )

        drawContext.canvas.nativeCanvas.drawText(
            "22:00",
            850f,
            10.dp.toPx(),
            paint
        )

        drawLine(
            start = Offset(10f, 34.dp.toPx()),
            end = Offset(780f, 34.dp.toPx()),
            color = Gray30,
            strokeWidth = 2f
        )

        drawContext.canvas.nativeCanvas.drawText(
            "00:00",
            850f,
            44.dp.toPx(),
            paint
        )

        drawLine(
            start = Offset(10f, 68.dp.toPx()),
            end = Offset(780f, 68.dp.toPx()),
            color = Gray30,
            strokeWidth = 2f
        )

        drawContext.canvas.nativeCanvas.drawText(
            "02:00",
            850f,
            78.dp.toPx(),
            paint
        )

        drawLine(
            start = Offset(10f, 102.dp.toPx()),
            end = Offset(780f, 102.dp.toPx()),
            color = Gray30,
            strokeWidth = 2f
        )

        drawContext.canvas.nativeCanvas.drawText(
            "04:00",
            850f,
            112.dp.toPx(),
            paint
        )

        drawLine(
            start = Offset(10f, 136.dp.toPx()),
            end = Offset(780f, 136.dp.toPx()),
            color = Gray30,
            strokeWidth = 2f
        )

        drawContext.canvas.nativeCanvas.drawText(
            "06:00",
            850f,
            146.dp.toPx(),
            paint
        )

        drawLine(
            start = Offset(10f, 170.dp.toPx()),
            end = Offset(780f, 170.dp.toPx()),
            color = Gray30,
            strokeWidth = 2f
        )

        drawContext.canvas.nativeCanvas.drawText(
            "08:00",
            850f,
            180.dp.toPx(),
            paint
        )

        drawLine(
            start = Offset(10f, 204.dp.toPx()),
            end = Offset(780f, 204.dp.toPx()),
            color = Gray30,
            strokeWidth = 2f
        )

        drawContext.canvas.nativeCanvas.drawText(
            "10:00",
            850f,
            214.dp.toPx(),
            paint
        )

        drawLine(
            start = Offset(10f, 238.dp.toPx()),
            end = Offset(780f, 238.dp.toPx()),
            color = Color.Black,
            strokeWidth = 2f
        )

        drawContext.canvas.nativeCanvas.drawText(
            "12:00",
            850f,
            248.dp.toPx(),
            paint
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
                254.dp.toPx(),
                paint
            )
        }
    }
}


@Composable
fun AnalysisHRVStat(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = Color.White,
                shape = RoundedCornerShape(7.dp)
            )
    ) {
        AnalysisStatField(title = stringResource(R.string.highest_value), value = "18")
        Divider(
            color = Gray30.copy(alpha = 0.19f),
            thickness = 1.dp,
            modifier = modifier
                .padding(horizontal = 16.dp)
        )
        AnalysisStatField(title = stringResource(R.string.lowest_value), value = "18")
        Divider(
            color = Gray30.copy(alpha = 0.19f),
            thickness = 1.dp,
            modifier = modifier
                .padding(horizontal = 16.dp)
        )
        AnalysisStatField(title = stringResource(R.string.average_value), value = "18")
        Divider(
            color = Gray30.copy(alpha = 0.19f),
            thickness = 1.dp,
            modifier = modifier
                .padding(horizontal = 16.dp)
        )
        AnalysisStatField(title = stringResource(R.string.last_value), value = "18")
    }
}