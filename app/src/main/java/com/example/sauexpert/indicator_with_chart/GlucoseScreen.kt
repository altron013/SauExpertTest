package com.example.sauexpert.indicator_with_chart

import android.graphics.Paint
import androidx.compose.animation.core.FloatTweenSpec
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
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
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import com.example.sauexpert.R
import com.example.sauexpert.bracelet_indicator.TextWithBigValueAndDateForGraph
import com.example.sauexpert.bracelet_indicator.TextWithIconForGraph
import com.example.sauexpert.model.ListNumberOfYForTableData
import com.example.sauexpert.model.StepsData
import com.example.sauexpert.ui.theme.Gray30

@Composable
fun GlucoseScreen() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .padding(top = 24.dp, bottom = 10.dp)
    ) {
        GlucosewithBarChart()
    }
}

@Composable
fun GlucosewithBarChart(
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
        GlucoseTitle()
        Spacer(modifier = Modifier.height(12.dp))
        BarChartForGlucose(
            StepsData = listOf(
                StepsData(positionOnX = 9f, stepsPerDay = 200f, dateName = "16.12"),
                StepsData(positionOnX = 108f, stepsPerDay = 30f, dateName = "17.12"),
                StepsData(positionOnX = 208f, stepsPerDay = 190f, dateName = "18.12"),
                StepsData(positionOnX = 308f, stepsPerDay = 180f, dateName = "19.12"),
                StepsData(positionOnX = 408f, stepsPerDay = 220f, dateName = "20.12"),
                StepsData(positionOnX = 508f, stepsPerDay = 240f, dateName = "21.12"),
                StepsData(positionOnX = 608f, stepsPerDay = 30f, dateName = "22.12")
            ),
            ListNumberData = listOf(
                ListNumberOfYForTableData("8.5"),
                ListNumberOfYForTableData("8.0"),
                ListNumberOfYForTableData("7.5"),
                ListNumberOfYForTableData("7.0"),
                ListNumberOfYForTableData("6.5"),
                ListNumberOfYForTableData("6.0"),
                ListNumberOfYForTableData("5.5"),
            )
        )

        MeasurementChangeForGlucose()
        Spacer(modifier = Modifier.height(12.dp))
        TextWithIconForGraph(
            color = Color.Red,
            text = stringResource(id = R.string.level_of_glucose_before_food)
        )
        TextWithIconForGraph(
            color = Color.Red,
            text = stringResource(id = R.string.level_of_glucose_after_food)
        )
    }
}

@Composable
fun GlucoseTitle(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Text(
            text = stringResource(id = R.string.glucose),
            style = MaterialTheme.typography.caption
        )

        TextWithBigValueAndDateForGraph(
            textValue = 6,
            text = stringResource(R.string.millimoles_per_liter_average),
            textDate = "18-20 ноября 2021"
        )
    }
}


@Composable
fun BarChartForGlucose(
    StepsData: List<StepsData>,
    ListNumberData: List<ListNumberOfYForTableData>
) {
    var start by remember { mutableStateOf(false) }
    val heightPre by animateFloatAsState(
        targetValue = if (start) 1f else 0f,
        animationSpec = FloatTweenSpec(duration = 1000)
    )

    val visible = remember { mutableStateOf(false) }
    val itemID = remember { mutableStateOf(1) }
    val positionOfX = remember { mutableStateOf(1) }
    val positionOfY = remember { mutableStateOf(1) }

    InfoDialogForBarChartOfGlucose(
        visible = visible,
        itemID = itemID,
        xPosition = positionOfX,
        yPosition = positionOfY,
        StepsData = StepsData
    )

    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .height(240.dp)
            .pointerInput(Unit) {
                detectTapGestures(
                    onTap = {
                        itemID.value = identifyClickItemForGlucose(StepsData, it.x, it.y)
                        ResetColorInsideDataClassForGlucose(StepsData = StepsData)
                        positionOfX.value = it.x.toInt()
                        positionOfY.value = it.y.toInt()
                        if (itemID.value != -1) {
                            visible.value = true
                            StepsData[itemID.value].colorFocus = Color.Red
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
        for (p in StepsData) {
            drawLine(
                start = Offset(wight.dp.toPx(), (height - 34).dp.toPx()),
                end = Offset(wight.dp.toPx(), 0f),
                color = Gray30,
                strokeWidth = 2f
            )

            drawRect(
                color = Color(251, 241, 243),
                topLeft = Offset(
                    x = p.positionOnX,
                    y = (height - 35).dp.toPx() - ((height - 35).dp.toPx() - 85f) * heightPre
                ),
                size = Size(
                    width = 32.dp.toPx(),
                    height = ((height - 35).dp.toPx() - 85f) * heightPre
                )
            )

            drawRect(
                color = p.colorFocus,
                topLeft = Offset(
                    x = p.positionOnX,
                    y = (height - 35).dp.toPx() - ((height - 35).dp.toPx() - p.stepsPerDay) * heightPre
                ),
                size = Size(
                    width = 32.dp.toPx(),
                    height = ((height - 35).dp.toPx() - p.stepsPerDay) * heightPre
                )
            )

            drawContext.canvas.nativeCanvas.drawText(
                "${p.dateName}",
                p.positionOnX + 38,
                (height - 15).dp.toPx(),
                paint
            )

            wight += 38
        }
    }
}

private fun identifyClickItemForGlucose(dataList: List<StepsData>, x: Float, y: Float): Int {
    for ((index, dataList) in dataList.withIndex()) {
        if (x > dataList.positionOnX && x < dataList.positionOnX + 80 && y > dataList.stepsPerDay) {
            return index
        }
    }
    return -1
}

private fun ResetColorInsideDataClassForGlucose(StepsData: List<StepsData>) {
    for (p in StepsData) {
        p.colorFocus = Color(250, 218, 221)
    }
}

@Composable
fun InfoDialogForBarChartOfGlucose(
    visible: MutableState<Boolean>,
    itemID: MutableState<Int>,
    xPosition: MutableState<Int>,
    yPosition: MutableState<Int>,
    StepsData: List<StepsData>,
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
                            text = "${itemID.value} | ${StepsData[itemID.value].stepsPerDay} | " +
                                    "${StepsData[itemID.value].dateName}",
                            style = MaterialTheme.typography.h5,
                            modifier = modifier
                                .align(alignment = Alignment.Center)
                                .clickable {
                                    visible.value = false
                                    ResetColorInsideDataClassForGlucose(StepsData = StepsData)
                                }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun MeasurementChangeForGlucose(
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = Gray30.copy(alpha = 0.19f),
                shape = RoundedCornerShape(10.dp)
            )
            .padding(16.dp)
    ) {
        Text(
            text = stringResource(R.string.show_measurements),
            style = MaterialTheme.typography.button,
            fontSize = 15.sp,
        )

        Text(
            text = "3 раза",
            style = MaterialTheme.typography.subtitle2,
            fontSize = 15.sp,
        )
    }
}