package com.example.sauexpert.indicator_for_patient

import android.graphics.Paint
import androidx.compose.animation.core.FloatTweenSpec
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sauexpert.R
import com.example.sauexpert.model.ListNumberOfYForTableData
import com.example.sauexpert.model.PressureData
import com.example.sauexpert.model.PulseData
import com.example.sauexpert.model.StepsData
import com.example.sauexpert.ui.theme.Gray30

@Composable
fun IndicatorGraphForPatientScreen() {

    Column(
        modifier = Modifier.fillMaxSize()
            .background(
                color = Gray30.copy(alpha = 0.19f)
            )
    ) {
        TopBarForIndicatorForPatient()
        Spacer(modifier = Modifier.height(32.dp))
        TitleForIndicatorGraphForPatient()
        Spacer(modifier = Modifier.height(24.dp))
        BloodPressureAndPulseWithBarChart()
        Spacer(modifier = Modifier.height(25.dp))
        StepsTakenWithBarChart()
    }
}

@Composable
fun TopBarForIndicatorForPatient(
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 8.dp, top = 30.dp)
            .clickable {
            }
    ) {

        Icon(
            imageVector = Icons.Default.KeyboardArrowLeft,
            contentDescription = "Back",
            tint = Color(17, 114, 89),
        )

        Text(
            text = stringResource(id = R.string.back),
            style = MaterialTheme.typography.subtitle2,
            color = Color(17, 114, 89),
        )
    }
}

@Composable
fun TitleForIndicatorGraphForPatient(
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
    ) {
        Text(
            text = stringResource(id = R.string.graphs),
            style = MaterialTheme.typography.h4,
            fontWeight = FontWeight.W700,
            fontSize = 30.sp
        )

        Text(
            text = "За последние 7 дней",
            style = MaterialTheme.typography.subtitle2,
            fontWeight = FontWeight.W700,
            fontSize = 15.sp
        )
    }
}

@Composable
fun BloodPressureAndPulseWithBarChart(
    modifier: Modifier = Modifier
) {

    Column {
        Text(
            text = stringResource(id = R.string.blood_pressure_pulse),
            style = MaterialTheme.typography.subtitle2,
            modifier = modifier.padding(horizontal = 20.dp)
        )

        Spacer(modifier = Modifier.height(10.dp))

        Column(
            modifier = modifier
                .fillMaxWidth()
                .background(
                    color = Color.White,
                )
                .padding(vertical = 30.dp, horizontal = 19.dp)
        ) {
            BarChartForBloodPressureAndPulse(
                PressureData = listOf(
                    PressureData(
                        positionOnX = 50f,
                        pressureInAverage = 80f,
                        dateName = "16.12",
                        startPoint = 20f
                    ),
                    PressureData(
                        positionOnX = 150f,
                        pressureInAverage = 100f,
                        startPoint = 100f
                    ),
                    PressureData(
                        positionOnX = 250f,
                        pressureInAverage = 190f,
                        dateName = "18.12",
                        startPoint = 10f
                    ),
                    PressureData(
                        positionOnX = 350f,
                        pressureInAverage = 150f,
                        startPoint = 110f
                    ),
                    PressureData(
                        positionOnX = 450f,
                        pressureInAverage = 220f,
                        dateName = "20.12",
                        startPoint = 30f
                    ),
                    PressureData(
                        positionOnX = 550f,
                        pressureInAverage = 140f,
                        startPoint = 50f
                    ),
                    PressureData(
                        positionOnX = 650f,
                        pressureInAverage = 50f,
                        dateName = "22.12",
                        startPoint = 190f
                    )
                ),

                PulseData = listOf(
                    PulseData(positionOnX = 150f, pulseInMinuteAverage = 100f),
                    PulseData(positionOnX = 250f, pulseInMinuteAverage = 30f),
                    PulseData(positionOnX = 350f, pulseInMinuteAverage = 50f),
                    PulseData(positionOnX = 450f, pulseInMinuteAverage = 70f),
                    PulseData(positionOnX = 550f, pulseInMinuteAverage = 50f),
                    PulseData(positionOnX = 650f, pulseInMinuteAverage = 90f),
                    PulseData(positionOnX = 750f, pulseInMinuteAverage = 90f),
                ),

                ListNumberData = listOf(
                    ListNumberOfYForTableData("140"),
                    ListNumberOfYForTableData("120"),
                    ListNumberOfYForTableData("80"),
                    ListNumberOfYForTableData("50"),
                    ListNumberOfYForTableData("30"),
                )
            )
        }
    }
}


@Composable
fun BarChartForBloodPressureAndPulse(
    PressureData: List<PressureData>,
    PulseData: List<PulseData>,
    ListNumberData: List<ListNumberOfYForTableData>
) {

    val path = Path()
    var start by remember { mutableStateOf(false) }


    val heightPre by animateFloatAsState(
        targetValue = if (start) 1f else 0f,
        animationSpec = FloatTweenSpec(duration = 1000)
    )


    for ((index, item) in PulseData.withIndex()) {
        if (index == 0) {
            path.moveTo(50f, item.pulseInMinuteAverage)
            path.lineTo(item.positionOnX, item.pulseInMinuteAverage)
        } else {
            path.lineTo(item.positionOnX, item.pulseInMinuteAverage)
        }
    }



    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
    ) {
        var height = 0
        val paint = Paint().apply {
            textAlign = Paint.Align.CENTER
            textSize = 13.sp.toPx()
            color = Gray30.toArgb()
        }

        for (i in ListNumberData) {

            drawContext.canvas.nativeCanvas.drawText(
                i.number,
                0f,
                (5 + height).dp.toPx(),
                paint
            )

            drawLine(
                start = Offset(x = 18.dp.toPx(), y = height.dp.toPx()),
                end = Offset(x = 346.dp.toPx(), y = height.dp.toPx()),
                color = Gray30,
                strokeWidth = 2f
            )



            height += 26
        }

        start = true

        for (p in PressureData) {
            drawRect(
                color = Color(87, 195, 167),
                topLeft = Offset(
                    x = p.positionOnX,
                    y = p.startPoint * heightPre
                ),
                size = Size(
                    width = 12.dp.toPx(),
                    height = p.pressureInAverage * heightPre
                )

            )

            drawContext.canvas.nativeCanvas.drawText(
                "${p.dateName}",
                6.dp.toPx() + p.positionOnX,
                (height - 5).dp.toPx(),
                paint
            )
        }

        drawPath(
            path = path,
            color = Color.Red.copy(alpha = 0.5f),
            style = Stroke(width = 5f)
        )
    }
}


@Composable
fun StepsTakenWithBarChart(
    modifier: Modifier = Modifier
) {

    Column {
        Text(
            text = stringResource(id = R.string.steps_taken),
            style = MaterialTheme.typography.subtitle2,
            modifier = modifier.padding(horizontal = 20.dp)
        )

        Spacer(modifier = Modifier.height(10.dp))

        Column(
            modifier = modifier
                .fillMaxWidth()
                .background(
                    color = Color.White,
                )
                .padding(vertical = 30.dp, horizontal = 19.dp)
        ) {
            BarChartForStepsTaken(
                StepsData = listOf(
                    StepsData(positionOnX = 70f, stepsPerDay = 200f, dateName = "16.12"),
                    StepsData(positionOnX = 170f, stepsPerDay = 30f, dateName = "17.12"),
                    StepsData(positionOnX = 270f, stepsPerDay = 190f, dateName = "18.12"),
                    StepsData(positionOnX = 370f, stepsPerDay = 180f, dateName = "19.12"),
                    StepsData(positionOnX = 470f, stepsPerDay = 220f, dateName = "20.12"),
                    StepsData(positionOnX = 570f, stepsPerDay = 240f, dateName = "21.12"),
                    StepsData(positionOnX = 670f, stepsPerDay = 30f, dateName = "22.12")
                ),

                ListNumberData = listOf(
                    ListNumberOfYForTableData("12000"),
                    ListNumberOfYForTableData("10000"),
                    ListNumberOfYForTableData("8000"),
                    ListNumberOfYForTableData("6000"),
                    ListNumberOfYForTableData("0"),
                )
            )
        }
    }
}


@Composable
fun BarChartForStepsTaken(
    StepsData: List<StepsData>,
    ListNumberData: List<ListNumberOfYForTableData>
) {
    var start by remember { mutableStateOf(false) }


    val heightPre by animateFloatAsState(
        targetValue = if (start) 1f else 0f,
        animationSpec = FloatTweenSpec(duration = 1000)
    )



    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
    ) {
        var height = 0
        val paint = Paint().apply {
            textAlign = Paint.Align.CENTER
            textSize = 13.sp.toPx()
            color = Gray30.toArgb()
        }

        for (i in ListNumberData) {

            drawContext.canvas.nativeCanvas.drawText(
                i.number,
                0f,
                (5 + height).dp.toPx(),
                paint
            )

            drawLine(
                start = Offset(x = 18.dp.toPx(), y = height.dp.toPx()),
                end = Offset(x = 346.dp.toPx(), y = height.dp.toPx()),
                color = Gray30,
                strokeWidth = 2f
            )



            height += 26
        }

        start = true

        for (p in StepsData) {

            drawRect(
                brush = SolidColor(Color.Blue),
                topLeft = Offset(
                    x = p.positionOnX + 1f,
                    y = (height - 26).dp.toPx() - ((height - 26).dp.toPx() - 70f) * heightPre
                ),
                size = Size(
                    width = 9.dp.toPx(),
                    height = ((height - 26).dp.toPx() - 70f) * heightPre
                ),
                style = Stroke(width = 3f)
            )


            drawRect(
                color = Color.Blue,
                topLeft = Offset(
                    x = p.positionOnX,
                    y = (height - 26).dp.toPx() - ((height - 26).dp.toPx() - p.stepsPerDay) * heightPre
                ),
                size = Size(
                    width = 10.dp.toPx(),
                    height = ((height - 26).dp.toPx() - p.stepsPerDay) * heightPre
                )

            )

            drawContext.canvas.nativeCanvas.drawText(
                "${p.dateName}",
                6.dp.toPx() + p.positionOnX,
                (height - 5).dp.toPx(),
                paint
            )
        }
    }
}

