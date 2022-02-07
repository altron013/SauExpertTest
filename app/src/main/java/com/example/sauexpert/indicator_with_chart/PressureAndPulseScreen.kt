package com.example.sauexpert.indicator_with_chart

import android.graphics.Paint
import androidx.compose.animation.core.FloatTweenSpec
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FlashOn
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import com.example.sauexpert.R
import com.example.sauexpert.bracelet_indicator.*
import com.example.sauexpert.model.ListNumberOfYForTableData
import com.example.sauexpert.model.PressureData
import com.example.sauexpert.model.PulseData
import com.example.sauexpert.ui.theme.Gray30

@Composable
fun PressureAndPulseScreen() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .padding(top = 24.dp, bottom = 10.dp)
    ) {
        PressureAndPulsewithBarChart()
        Spacer(modifier = Modifier.height(24.dp))
        AnalysisPressureAndPulseSection()
    }
}

@Composable
fun PressureAndPulsewithBarChart(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = Color.White,
                shape = RoundedCornerShape(10.dp)
            ).padding(16.dp)
    ) {
        PressureAndPulseTitle()
        Spacer(modifier = Modifier.height(12.dp))
        BarChartForPressureAndPulse(
            PressureData = listOf(
                PressureData(
                    positionOnX = 20f,
                    pressureInAverage = 200f,
                    dateName = "16.12",
                    startPoint = 250f
                ),
                PressureData(
                    positionOnX = 120f,
                    pressureInAverage = 300f,
                    dateName = "17.12",
                    startPoint = 200f
                ),
                PressureData(
                    positionOnX = 220f,
                    pressureInAverage = 190f,
                    dateName = "18.12",
                    startPoint = 250f
                ),
                PressureData(
                    positionOnX = 320f,
                    pressureInAverage = 180f,
                    dateName = "19.12",
                    startPoint = 250f
                ),
                PressureData(
                    positionOnX = 420f,
                    pressureInAverage = 220f,
                    dateName = "20.12",
                    startPoint = 230f
                ),
                PressureData(
                    positionOnX = 520f,
                    pressureInAverage = 240f,
                    dateName = "21.12",
                    startPoint = 250f
                ),
                PressureData(
                    positionOnX = 620f,
                    pressureInAverage = 50f,
                    dateName = "22.12",
                    startPoint = 280f
                )
            ),

            PulseData = listOf(
                PulseData(positionOnX = 50f, pulseInMinuteAverage = 350f),
                PulseData(positionOnX = 150f, pulseInMinuteAverage = 370f),
                PulseData(positionOnX = 250f, pulseInMinuteAverage = 350f),
                PulseData(positionOnX = 350f, pulseInMinuteAverage = 370f),
                PulseData(positionOnX = 450f, pulseInMinuteAverage = 250f),
                PulseData(positionOnX = 550f, pulseInMinuteAverage = 290f),
                PulseData(positionOnX = 650f, pulseInMinuteAverage = 300f),
            ),

            ListNumberData = listOf(
                ListNumberOfYForTableData("240"),
                ListNumberOfYForTableData("200"),
                ListNumberOfYForTableData("160"),
                ListNumberOfYForTableData("120"),
                ListNumberOfYForTableData("80"),
                ListNumberOfYForTableData("40"),
                ListNumberOfYForTableData("0"),
            )


        )
        Spacer(modifier = Modifier.height(12.dp))
        TextWithIconForGraph(color = Color.Red, text = stringResource(id = R.string.pressure))
        TextWithIconForGraph(color = Color.Blue, text = stringResource(id = R.string.pulse))
    }
}

@Composable
fun PressureAndPulseTitle(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Text(
            text = stringResource(id = R.string.pressure_pulse),
            style = MaterialTheme.typography.caption
        )

        TextWithBigValueAndDateForGraph(
            textValue = 150,
            text = stringResource(R.string.mmhg_average),
            textDate = "18-20 ноября 2021"
        )

        Spacer(modifier = Modifier.height(12.dp))

        TextWithBigValueAndDateForGraph(
            textValue = 150,
            text = stringResource(R.string.pulse_in_minute_average),
            textDate = "18-20 ноября 2021"
        )
    }
}

@Composable
fun BarChartForPressureAndPulse(
    PressureData: List<PressureData>,
    PulseData: List<PulseData>,
    ListNumberData: List<ListNumberOfYForTableData>
) {
    val scale by remember { mutableStateOf(1f) }
    val path = Path()
    var start by remember { mutableStateOf(false) }
    val visible = remember { mutableStateOf(false) }
    val itemID = remember { mutableStateOf(1) }
    val positionOfX = remember { mutableStateOf(1) }
    val positionOfY = remember { mutableStateOf(1) }


    val heightPre by animateFloatAsState(
        targetValue = if (start) 1f else 0f,
        animationSpec = FloatTweenSpec(duration = 1000)
    )

    InfoDialogForBarChartOfPressureAndPulse(
        visible = visible,
        itemID = itemID,
        xPosition = positionOfX,
        yPosition = positionOfY,
        PressureData = PressureData,
        PulseData = PulseData
    )

    for ((index, item) in PulseData.withIndex()) {
        if (index == 0) {
            path.moveTo(0f * scale, item.pulseInMinuteAverage)
            path.lineTo(item.positionOnX * scale, item.pulseInMinuteAverage)
        } else {
            path.lineTo(item.positionOnX * scale, item.pulseInMinuteAverage)
        }
    }



    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .height(240.dp)
            .pointerInput(Unit) {
                detectTapGestures(
                    onTap = {
                        itemID.value =
                            identifyClickItemForPressureAndPulse(PressureData, it.x, it.y)
                        ResetColorInsideDataClassForPressureAndPusle(
                            pressureData = PressureData,
                            pulseData = PulseData
                        )
                        positionOfX.value = it.x.toInt()
                        positionOfY.value = it.y.toInt()
                        if (itemID.value != -1) {
                            visible.value = true
                            PressureData[itemID.value].colorFocus = Color.Red
                            PulseData[itemID.value].colorFocus = Color.Red
                        }
                    }
                )
            }
    ) {
        var height = 0
        var wight = 0
        val paint = Paint().apply {
            textAlign = Paint.Align.CENTER
            textSize = 13.sp.toPx()
            color = Gray30.toArgb()
        }

        for (i in ListNumberData) {
            drawLine(
                start = Offset(x = 0f, y = height.dp.toPx()),
                end = Offset(x = 780f, y = height.dp.toPx()),
                color = Gray30,
                strokeWidth = 2f
            )

            drawContext.canvas.nativeCanvas.drawText(
                i.number,
                320.dp.toPx(),
                (10 + height).dp.toPx(),
                paint
            )

            height += 35
        }

        start = true

        for (p in PressureData) {
            drawLine(
                start = Offset(wight.dp.toPx(), (height - 34).dp.toPx()),
                end = Offset(wight.dp.toPx(), 0f),
                color = Gray30,
                strokeWidth = 2f
            )

            drawRect(
                color = p.colorFocus,
                topLeft = Offset(p.positionOnX, p.startPoint * heightPre),
                size = Size(24.dp.toPx(), p.pressureInAverage * heightPre)

            )

            drawContext.canvas.nativeCanvas.drawText(
                "${p.dateName}",
                p.positionOnX + 32f,
                (height - 15).dp.toPx(),
                paint
            )
            wight += 38

        }

        drawPath(
            path = path,
            color = Color.Blue.copy(alpha = 0.3f),
            style = Stroke(width = 5f)
        )

        for (i in PulseData) {
            drawCircle(
                color = Color.White,
                radius = 13f,
                center = Offset(i.positionOnX, i.pulseInMinuteAverage - 1f)
            )

            drawCircle(
                color = i.colorFocus,
                radius = 10f,
                center = Offset(i.positionOnX, i.pulseInMinuteAverage - 1f)
            )
        }

        drawLine(
            start = Offset(wight.dp.toPx(), (height - 34).dp.toPx()),
            end = Offset(wight.dp.toPx(), 0f),
            color = Gray30,
            strokeWidth = 2f
        )

    }
}

private fun identifyClickItemForPressureAndPulse(
    dataList: List<PressureData>,
    x: Float,
    y: Float
): Int {
    for ((index, dataList) in dataList.withIndex()) {
        if (x > dataList.positionOnX
            && x < dataList.positionOnX + 70
            && y > dataList.startPoint
            && y < dataList.pressureInAverage + dataList.startPoint
        ) {
            return index
        }
    }
    return -1
}

private fun ResetColorInsideDataClassForPressureAndPusle(
    pressureData: List<PressureData>,
    pulseData: List<PulseData>
) {
    for (p in pressureData) {
        p.colorFocus = Color(250, 218, 221)
    }

    for (i in pulseData) {
        i.colorFocus = Color.Blue
    }
}


@Composable
fun InfoDialogForBarChartOfPressureAndPulse(
    visible: MutableState<Boolean>,
    itemID: MutableState<Int>,
    xPosition: MutableState<Int>,
    yPosition: MutableState<Int>,
    PressureData: List<PressureData>,
    PulseData: List<PulseData>,
    modifier: Modifier = Modifier
) {
    if (visible.value) {
        Box {
            Popup(
                alignment = Alignment.Center,
                IntOffset(xPosition.value, yPosition.value - 70)
            ) {
                Box(
                    modifier = Modifier
                        .size(width = 140.dp, height = 40.dp)
                        .background(Color.White, RoundedCornerShape(10.dp))
                ) {
                    if (itemID.value == -1) {
                        visible.value = false
                    } else {
                        Text(
                            text = "${itemID.value} | " +
                                    "${PressureData[itemID.value].pressureInAverage} | " +
                                    "${PulseData[itemID.value].pulseInMinuteAverage} | " +
                                    "${PressureData[itemID.value].dateName}",
                            style = MaterialTheme.typography.h5,
                            modifier = modifier
                                .align(alignment = Alignment.Center)
                                .clickable {
                                    visible.value = false
                                    ResetColorInsideDataClassForPressureAndPusle(
                                        pressureData = PressureData,
                                        pulseData = PulseData
                                    )
                                }
                        )
                    }
                }
            }
        }
    }
}


@Composable
fun AnalysisPressureAndPulseSection(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = Color.White,
                shape = RoundedCornerShape(10.dp)
            )
    ) {
        AnalysisFieldWithIconAtEnd(
            title = stringResource(R.string.highest_value),
            value = "18",
            imageVector = Icons.Filled.FlashOn
        )
        Divider(
            color = Gray30.copy(alpha = 0.19f),
            thickness = 1.dp,
            modifier = modifier
                .padding(horizontal = 16.dp)
        )
        AnalysisField(
            title = stringResource(R.string.lowest_value),
            value = "18"
        )
        Divider(
            color = Gray30.copy(alpha = 0.19f),
            thickness = 1.dp,
            modifier = modifier
                .padding(horizontal = 16.dp)
        )
        AnalysisField(
            title = stringResource(R.string.average_value),
            value = "18"
        )
        Divider(
            color = Gray30.copy(alpha = 0.19f),
            thickness = 1.dp,
            modifier = modifier
                .padding(horizontal = 16.dp)
        )
        AnalysisField(
            title = stringResource(R.string.last_value),
            value = "18"
        )
    }
}


