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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FlashOn
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
import com.example.sauexpert.model.HRVData
import com.example.sauexpert.model.PressureData
import com.example.sauexpert.ui.theme.Gray30

@Composable
fun PressureScreen() {
    Column(
        modifier = Modifier
            .fillMaxWidth().verticalScroll(rememberScrollState())
    ) {
        PressureStatwithBarChart()
        Spacer(modifier = Modifier.height(24.dp))
        AnalysisPressureStat()
    }
}


@Composable
fun PressureStatwithBarChart(
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
        PressureStat()
        Spacer(modifier = Modifier.height(12.dp))
        BarChartForPressure(
            PressureData = listOf(
                PressureData(positionOnX = 10f, pressureInAverage = 200f, dateName = "16.12"),
                PressureData(positionOnX = 110f, pressureInAverage = 370f, dateName = "17.12"),
                PressureData(positionOnX = 210f, pressureInAverage = 190f, dateName = "18.12"),
                PressureData(positionOnX = 310f, pressureInAverage = 180f, dateName = "19.12"),
                PressureData(positionOnX = 410f, pressureInAverage = 220f, dateName = "20.12"),
                PressureData(positionOnX = 510f, pressureInAverage = 240f, dateName = "21.12"),
                PressureData(positionOnX = 610f, pressureInAverage = 30f, dateName = "22.12")
            )
        )
    }
}

@Composable
fun PressureStat(
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
fun BarChartForPressure(
    PressureData: List<PressureData>,
) {
    var start by remember { mutableStateOf(false) }
    val heightPre by animateFloatAsState(
        targetValue = if (start) 1f else 0f,
        animationSpec = FloatTweenSpec(duration = 1000)
    )
    Canvas(
        modifier = Modifier
            .fillMaxWidth().height(155.dp)
    ) {

        val paint = Paint().apply {
            textAlign = Paint.Align.CENTER
            textSize = 34f
//            color = Color(0xFF0018A8).toArgb()
        }


        drawLine(
            start = Offset(10f, 140.dp.toPx()),
            end = Offset(10f, 0f),
            color = Gray30,
            strokeWidth = 2f
        )


        drawLine(
            start = Offset(10f, 0.dp.toPx()),
            end = Offset(780f, 0.dp.toPx()),
            color = Gray30,
            strokeWidth = 2f
        )

        drawContext.canvas.nativeCanvas.drawText(
            "240",
            320.dp.toPx(),
            10.dp.toPx(),
            paint
        )



        drawLine(
            start = Offset(10f, 35.dp.toPx()),
            end = Offset(780f, 35.dp.toPx()),
            color = Gray30,
            strokeWidth = 2f
        )

        drawContext.canvas.nativeCanvas.drawText(
            "200",
            320.dp.toPx(),
            45.dp.toPx(),
            paint
        )


        drawLine(
            start = Offset(10f, 70.dp.toPx()),
            end = Offset(780f, 70.dp.toPx()),
            color = Gray30,
            strokeWidth = 2f
        )

        drawContext.canvas.nativeCanvas.drawText(
            "160",
            320.dp.toPx(),
            80.dp.toPx(),
            paint
        )



        drawLine(
            start = Offset(10f, 105.dp.toPx()),
            end = Offset(780f, 105.dp.toPx()),
            color = Gray30,
            strokeWidth = 2f
        )

        drawContext.canvas.nativeCanvas.drawText(
            "120",
            320.dp.toPx(),
            115.dp.toPx(),
            paint
        )



        drawLine(
            start = Offset(10f, 140.dp.toPx()),
            end = Offset(780f, 140.dp.toPx()),
            color = Gray30,
            strokeWidth = 2f
        )

        drawContext.canvas.nativeCanvas.drawText(
            "80",
            320.dp.toPx(),
            150.dp.toPx(),
            paint
        )

        start = true
        for (p in PressureData) {
            drawRect(
                color = Color.Red,
                topLeft = Offset(
                    p.positionOnX + 20,
                    140.dp.toPx() - (140.dp.toPx() - p.pressureInAverage) * heightPre),
                size = Size(
                    60f,
                    (140.dp.toPx() - p.pressureInAverage) * heightPre)

            )

            drawContext.canvas.nativeCanvas.drawText(
                "${p.dateName}",
                p.positionOnX + 45,
                160.dp.toPx(),
                paint
            )
        }
    }
}


@Composable
fun AnalysisPressureStat(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = Color.White,
                shape = RoundedCornerShape(7.dp)
            )
    ) {
        AnalysisStatFieldWithIconAtEnd(
            title = stringResource(R.string.highest_value),
            value = "18",
            imageVector = Icons.Filled.FlashOn)
        Divider(
            color = Gray30.copy(alpha = 0.19f),
            thickness = 1.dp,
            modifier = modifier
                .padding(horizontal = 16.dp)
        )
        AnalysisStatField(
            title = stringResource(R.string.lowest_value),
            value = "18")
        Divider(
            color = Gray30.copy(alpha = 0.19f),
            thickness = 1.dp,
            modifier = modifier
                .padding(horizontal = 16.dp)
        )
        AnalysisStatField(
            title = stringResource(R.string.average_value),
            value = "18")
        Divider(
            color = Gray30.copy(alpha = 0.19f),
            thickness = 1.dp,
            modifier = modifier
                .padding(horizontal = 16.dp)
        )
        AnalysisStatField(
            title = stringResource(R.string.last_value),
            value = "18")
    }
}