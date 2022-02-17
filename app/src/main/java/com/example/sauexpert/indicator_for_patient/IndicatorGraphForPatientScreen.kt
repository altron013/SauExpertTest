package com.example.sauexpert.indicator_for_patient

import android.graphics.Paint
import androidx.compose.animation.core.FloatTweenSpec
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Stop
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
import com.example.sauexpert.model.*
import com.example.sauexpert.ui.theme.Blue4285
import com.example.sauexpert.ui.theme.Gray30
import com.example.sauexpert.ui.theme.Green57C3A7
import com.example.sauexpert.ui.theme.Red4294

@Composable
fun IndicatorGraphForPatientScreen() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(
                color = Gray30.copy(alpha = 0.19f)
            )
            .padding(bottom = 20.dp)
    ) {
        TopBarForIndicatorForPatient()
        Spacer(modifier = Modifier.height(32.dp))
        TitleForIndicatorGraphForPatient()
        Spacer(modifier = Modifier.height(24.dp))
        GlucoseWithBarChartForIndicatorGraph()
        Spacer(modifier = Modifier.height(25.dp))
        BloodPressureAndPulseWithBarChart()
        Spacer(modifier = Modifier.height(25.dp))
        StepsTakenWithBarChart()
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
fun GlucoseWithBarChartForIndicatorGraph(
    modifier: Modifier = Modifier
) {
    val checkedStateBeforeFood = remember { mutableStateOf(true) }
    val checkedStateAfterFood = remember { mutableStateOf(true) }

    Column {
        Text(
            text = stringResource(id = R.string.glucose_level),
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
            BarChartForGlucoseForIndicatorGraph(
                glucoseData = listOf(
                    GlucoseData(
                        positionOnX = 50f,
                        positionOnYBeforeFood = 200f,
                        positionOnYAfterFood = 200f,
                        dateName = "16.12"
                    ),
                    GlucoseData(
                        positionOnX = 150f,
                        positionOnYBeforeFood = 200f,
                        positionOnYAfterFood = 200f,
                    ),
                    GlucoseData(
                        positionOnX = 250f,
                        positionOnYBeforeFood = 200f,
                        positionOnYAfterFood = 200f,
                        dateName = "18.12"
                    ),
                    GlucoseData(
                        positionOnX = 350f,
                        positionOnYBeforeFood = 200f,
                        positionOnYAfterFood = 200f,
                    ),
                    GlucoseData(
                        positionOnX = 450f,
                        positionOnYBeforeFood = 200f,
                        positionOnYAfterFood = 200f,
                        dateName = "20.12",
                    ),
                    GlucoseData(
                        positionOnX = 550f,
                        positionOnYBeforeFood = 200f,
                        positionOnYAfterFood = 200f,
                    ),
                    GlucoseData(
                        positionOnX = 650f,
                        positionOnYBeforeFood = 200f,
                        positionOnYAfterFood = 200f,
                        dateName = "22.12"
                    )
                ),

                ListNumberData = listOf(
                    ListNumberOfYForTableData(7),
                    ListNumberOfYForTableData(6),
                    ListNumberOfYForTableData(5),
                    ListNumberOfYForTableData(4),
                    ListNumberOfYForTableData(0),
                ),
                checkedStateBeforeFood = checkedStateBeforeFood,
                checkedStateAfterFood = checkedStateAfterFood

            )

            Spacer(modifier = Modifier.height(22.dp))

            Text(
                text = stringResource(id = R.string.show_measurements),
                style = MaterialTheme.typography.body1,
            )

            Spacer(modifier = Modifier.height(22.dp))

            SwitchRowWithIcon(
                checkedState = checkedStateBeforeFood,
                color = Red4294.copy(alpha = 0.79f),
                text = stringResource(R.string.glucose_after_meal)
            )

            Divider(
                color = Gray30.copy(alpha = 0.19f),
                thickness = 1.dp,
                modifier = modifier
                    .padding(horizontal = 16.dp, vertical = 12.dp)
            )

            SwitchRowWithIcon(
                checkedState = checkedStateAfterFood,
                color = Green57C3A7,
                text = stringResource(R.string.glucose_after_meal)
            )


        }
    }
}

@Composable
fun SwitchRowWithIcon(
    modifier: Modifier = Modifier,
    checkedState: MutableState<Boolean>,
    color: Color,
    text: String
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .fillMaxWidth()
    ) {
        Icon(
            imageVector = Icons.Default.Stop,
            contentDescription = "",
            tint = color,
            modifier = modifier.size(20.dp)
        )

        Spacer(modifier = Modifier.width(3.dp))

        Text(
            text = text,
            style = MaterialTheme.typography.body1,
        )

        Spacer(modifier = Modifier.weight(1f))


        Switch(
            checked = checkedState.value,
            onCheckedChange = { checkedState.value = it }
        )

    }


}


@Composable
fun BarChartForGlucoseForIndicatorGraph(
    glucoseData: List<GlucoseData>,
    ListNumberData: List<ListNumberOfYForTableData>,
    checkedStateBeforeFood: MutableState<Boolean>,
    checkedStateAfterFood: MutableState<Boolean>,
) {
    var start by remember { mutableStateOf(false) }
    val heightPre by animateFloatAsState(
        targetValue = if (start) 1f else 0f,
        animationSpec = FloatTweenSpec(duration = 1000)
    )


    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .height(110.dp)
    ) {
        var height = 0
        val paint = Paint().apply {
            textAlign = Paint.Align.CENTER
            textSize = 13.sp.toPx()
            color = Gray30.toArgb()
        }

        for (i in ListNumberData) {
            drawContext.canvas.nativeCanvas.drawText(
                i.number.toString(),
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
        for (p in glucoseData) {
            if (checkedStateBeforeFood.value) {
                drawRect(
                    color = Red4294.copy(alpha = 0.79f),
                    topLeft = Offset(
                        x = p.positionOnX,
                        y = (height - 26).dp.toPx() - ((height - 26).dp.toPx() - p.glucoseBeforeFood) * heightPre
                    ),
                    size = Size(
                        width = 12.dp.toPx(),
                        height = ((height - 26).dp.toPx() - p.glucoseBeforeFood) * heightPre
                    )
                )
            }

            if (checkedStateAfterFood.value) {
                drawRect(
                    color = Green57C3A7,
                    topLeft = Offset(
                        x = p.positionOnX + 35,
                        y = (height - 26).dp.toPx() - ((height - 26).dp.toPx() - p.glucoseAfterFood) * heightPre
                    ),
                    size = Size(
                        width = 12.dp.toPx(),
                        height = ((height - 26).dp.toPx() - p.glucoseAfterFood) * heightPre
                    )
                )
            }

            drawContext.canvas.nativeCanvas.drawText(
                "${p.dateName}",
                12.dp.toPx() + p.positionOnX,
                (height - 5).dp.toPx(),
                paint
            )
        }
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
                        endPoint = 80f,
                        dateName = "16.12",
                        startPoint = 20f
                    ),
                    PressureData(
                        positionOnX = 150f,
                        endPoint = 100f,
                        startPoint = 100f
                    ),
                    PressureData(
                        positionOnX = 250f,
                        endPoint = 190f,
                        dateName = "18.12",
                        startPoint = 10f
                    ),
                    PressureData(
                        positionOnX = 350f,
                        endPoint = 150f,
                        startPoint = 110f
                    ),
                    PressureData(
                        positionOnX = 450f,
                        endPoint = 220f,
                        dateName = "20.12",
                        startPoint = 30f
                    ),
                    PressureData(
                        positionOnX = 550f,
                        endPoint = 140f,
                        startPoint = 50f
                    ),
                    PressureData(
                        positionOnX = 650f,
                        endPoint = 50f,
                        dateName = "22.12",
                        startPoint = 190f
                    )
                ),

                PulseData = listOf(
                    PulseData(positionOnX = 150f, positionOnY = 100f),
                    PulseData(positionOnX = 250f, positionOnY = 30f),
                    PulseData(positionOnX = 350f, positionOnY = 50f),
                    PulseData(positionOnX = 450f, positionOnY = 70f),
                    PulseData(positionOnX = 550f, positionOnY = 50f),
                    PulseData(positionOnX = 650f, positionOnY = 90f),
                    PulseData(positionOnX = 750f, positionOnY = 90f),
                ),

                ListNumberData = listOf(
                    ListNumberOfYForTableData(140),
                    ListNumberOfYForTableData(120),
                    ListNumberOfYForTableData(80),
                    ListNumberOfYForTableData(50),
                    ListNumberOfYForTableData(30),
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
            path.moveTo(50f, item.positionOnY)
            path.lineTo(item.positionOnX, item.positionOnY)
        } else {
            path.lineTo(item.positionOnX, item.positionOnY)
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
                i.number.toString(),
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
                color = Green57C3A7,
                topLeft = Offset(
                    x = p.positionOnX,
                    y = p.startPoint * heightPre
                ),
                size = Size(
                    width = 12.dp.toPx(),
                    height = p.endPoint * heightPre
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
                    StepsData(positionOnX = 70f, positionOnY = 200f, dateName = "16.12"),
                    StepsData(positionOnX = 170f, positionOnY = 30f, dateName = "17.12"),
                    StepsData(positionOnX = 270f, positionOnY = 190f, dateName = "18.12"),
                    StepsData(positionOnX = 370f, positionOnY = 180f, dateName = "19.12"),
                    StepsData(positionOnX = 470f, positionOnY = 220f, dateName = "20.12"),
                    StepsData(positionOnX = 570f, positionOnY = 240f, dateName = "21.12"),
                    StepsData(positionOnX = 670f, positionOnY = 30f, dateName = "22.12")
                ),

                ListNumberData = listOf(
                    ListNumberOfYForTableData(12000),
                    ListNumberOfYForTableData(10000),
                    ListNumberOfYForTableData(8000),
                    ListNumberOfYForTableData(6000),
                    ListNumberOfYForTableData(0),
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
                i.number.toString(),
                0f,
                (5 + height).dp.toPx(),
                paint
            )

            drawLine(
                start = Offset(x = 20.dp.toPx(), y = height.dp.toPx()),
                end = Offset(x = 346.dp.toPx(), y = height.dp.toPx()),
                color = Gray30,
                strokeWidth = 2f
            )



            height += 26
        }

        start = true

        for (p in StepsData) {

            drawRect(
                brush = SolidColor(Blue4285),
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
                color = Blue4285,
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

