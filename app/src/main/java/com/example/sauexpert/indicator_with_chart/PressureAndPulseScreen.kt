package com.example.sauexpert.indicator_with_chart

import android.graphics.Paint
import androidx.compose.animation.core.FloatTweenSpec
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Circle
import androidx.compose.material.icons.filled.FlashOn
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sauexpert.R
import com.example.sauexpert.bracelet_indicator.*
import com.example.sauexpert.model.ListNumberOfYForTableData
import com.example.sauexpert.model.PressureData
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
                    positionOnX = 15f,
                    pressureInAverage = 200f,
                    dateName = "16.12",
                    startPoint = 250f
                ),
                PressureData(
                    positionOnX = 110f,
                    pressureInAverage = 300f,
                    dateName = "17.12",
                    startPoint = 200f
                ),
                PressureData(
                    positionOnX = 205f,
                    pressureInAverage = 190f,
                    dateName = "18.12",
                    startPoint = 250f
                ),
                PressureData(
                    positionOnX = 300f,
                    pressureInAverage = 180f,
                    dateName = "19.12",
                    startPoint = 250f
                ),
                PressureData(
                    positionOnX = 395f,
                    pressureInAverage = 220f,
                    dateName = "20.12",
                    startPoint = 230f
                ),
                PressureData(
                    positionOnX = 490f,
                    pressureInAverage = 240f,
                    dateName = "21.12",
                    startPoint = 250f
                ),
                PressureData(
                    positionOnX = 585f,
                    pressureInAverage = 50f,
                    dateName = "22.12",
                    startPoint = 280f
                )
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
    ListNumberData: List<ListNumberOfYForTableData>
) {
    var start by remember { mutableStateOf(false) }
    val visible = remember { mutableStateOf(false) }
    val itemID = remember { mutableStateOf(1) }
    val positionOfX = remember { mutableStateOf(1) }
    val positionOfY = remember { mutableStateOf(1) }


    val heightPre by animateFloatAsState(
        targetValue = if (start) 1f else 0f,
        animationSpec = FloatTweenSpec(duration = 1000)
    )

    InfoDialogForBarChartOfPressure(
        visible = visible,
        itemID = itemID,
        xPosition = positionOfX,
        yPosition = positionOfY,
        dataList = PressureData
    )


    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .height(240.dp)
            .pointerInput(Unit) {
                detectTapGestures(
                    onTap = {
                        itemID.value =
                            identifyClickItemForPressureAndPulse(PressureData, it.x, it.y)
                        ResetColorInsideDataClassForPressureAndPusle(dataList = PressureData)
                        positionOfX.value = it.x.toInt()
                        positionOfY.value = it.y.toInt()
                        if (itemID.value != -1) {
                            visible.value = true
                            PressureData[itemID.value].colorFocus = Color.Red
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
            wight += 36

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

private fun ResetColorInsideDataClassForPressureAndPusle(dataList: List<PressureData>) {
    for (p in dataList) {
        p.colorFocus = Color(250, 218, 221)
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
        AnalysisStatFieldWithIconAtEnd(
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
        AnalysisStatField(
            title = stringResource(R.string.lowest_value),
            value = "18"
        )
        Divider(
            color = Gray30.copy(alpha = 0.19f),
            thickness = 1.dp,
            modifier = modifier
                .padding(horizontal = 16.dp)
        )
        AnalysisStatField(
            title = stringResource(R.string.average_value),
            value = "18"
        )
        Divider(
            color = Gray30.copy(alpha = 0.19f),
            thickness = 1.dp,
            modifier = modifier
                .padding(horizontal = 16.dp)
        )
        AnalysisStatField(
            title = stringResource(R.string.last_value),
            value = "18"
        )
    }
}
