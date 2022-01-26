package com.example.sauexpert.bracelet_indicator

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
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import com.example.sauexpert.R
import com.example.sauexpert.model.ListNumberOfYForTableData
import com.example.sauexpert.model.PulseData
import com.example.sauexpert.ui.theme.Gray30

@Composable
fun PulseScreen() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .padding(top = 24.dp, bottom = 10.dp)
    ) {
        PulseStatwithBarChart()
        Spacer(modifier = Modifier.height(24.dp))
        AnalysisPulseStat()
    }
}


@Composable
fun PulseStatwithBarChart(
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
        PulseStat()
        Spacer(modifier = Modifier.height(12.dp))
        BarChartForPulse(
            PulseData = listOf(
                PulseData(positionOnX = 10f, pulseInMinuteAverage = 200f, dateName = "16.12"),
                PulseData(positionOnX = 105f, pulseInMinuteAverage = 370f, dateName = "17.12"),
                PulseData(positionOnX = 200f, pulseInMinuteAverage = 190f, dateName = "18.12"),
                PulseData(positionOnX = 295f, pulseInMinuteAverage = 180f, dateName = "19.12"),
                PulseData(positionOnX = 390f, pulseInMinuteAverage = 220f, dateName = "20.12"),
                PulseData(positionOnX = 485f, pulseInMinuteAverage = 240f, dateName = "21.12"),
                PulseData(positionOnX = 580f, pulseInMinuteAverage = 30f, dateName = "22.12")
            ),
            ListNumberData = listOf(
                ListNumberOfYForTableData("280"),
                ListNumberOfYForTableData("240"),
                ListNumberOfYForTableData("200"),
                ListNumberOfYForTableData("160"),
                ListNumberOfYForTableData("120"),
                ListNumberOfYForTableData("80"),
                ListNumberOfYForTableData("60"),
            )
        )
    }
}

@Composable
fun PulseStat(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Text(
            text = stringResource(id = R.string.pulse),
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

                append(stringResource(R.string.pulse_in_minute_average))
            },
            style = MaterialTheme.typography.subtitle1,
            fontWeight = FontWeight.Bold,
            color = Gray30
        )

        Text(
            text = "18-20 ноября 2021",
            style = MaterialTheme.typography.h6,
            fontSize = 15.sp,
            color = Gray30
        )
    }
}


@Composable
fun BarChartForPulse(
    PulseData: List<PulseData>,
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

    InfoDialogForBarChartOfPulse(
        visible = visible,
        itemID = itemID,
        xPosition = positionOfX,
        yPosition = positionOfY,
        dataList = PulseData
    )


    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
            .pointerInput(Unit) {
                detectTapGestures(
                    onTap = {
                        itemID.value = identifyClickItemForPulse(PulseData, it.x, it.y)
                        ResetColorInsideDataClassForPulse(dataList = PulseData)
                        positionOfX.value = it.x.toInt()
                        positionOfY.value = it.y.toInt()
                        if (itemID.value != -1) {
                            visible.value = true
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
                start = Offset(x = 10f, y = height.dp.toPx()),
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

            height += 34
        }

        start = true
        for (p in PulseData) {
            drawLine(
                start = Offset(wight.dp.toPx(), (height - 34).dp.toPx()),
                end = Offset(wight.dp.toPx(), 0f),
                color = Gray30,
                strokeWidth = 2f
            )

            drawRect(
                color = p.colorFocus,
                topLeft = Offset(
                    x = p.positionOnX,
                    y = (height - 34).dp.toPx() - ((height - 34).dp.toPx()
                            - p.pulseInMinuteAverage) * heightPre
                ),
                size = Size(
                    width = 75f,
                    height = (height.dp.toPx() - 34.dp.toPx() - p.pulseInMinuteAverage) * heightPre
                )
            )

            drawContext.canvas.nativeCanvas.drawText(
                "${p.dateName}",
                p.positionOnX + 38,
                (height - 14).dp.toPx(),
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

private fun identifyClickItemForPulse(dataList: List<PulseData>, x: Float, y: Float): Int {
    for ((index, dataList) in dataList.withIndex()) {
        if (x > dataList.positionOnX && x < dataList.positionOnX + 70 && y > dataList.pulseInMinuteAverage) {
            return index
        }
    }
    return -1
}

private fun ResetColorInsideDataClassForPulse(dataList: List<PulseData>) {
    for (p in dataList) {
        p.colorFocus = Color(250, 218, 221)
    }
}

@Composable
fun InfoDialogForBarChartOfPulse(
    visible: MutableState<Boolean>,
    itemID: MutableState<Int>,
    xPosition: MutableState<Int>,
    yPosition: MutableState<Int>,
    dataList: List<PulseData>,
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
                            text = "${itemID.value} | ${dataList[itemID.value].pulseInMinuteAverage} | " +
                                    "${dataList[itemID.value].dateName}",
                            style = MaterialTheme.typography.h5,
                            modifier = modifier
                                .align(alignment = Alignment.Center)
                                .clickable {
                                    visible.value = false
                                    ResetColorInsideDataClassForPulse(dataList = dataList)
                                }
                        )
                    }
                }
            }
        }
    }
}


@Composable
fun AnalysisPulseStat(modifier: Modifier = Modifier) {
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