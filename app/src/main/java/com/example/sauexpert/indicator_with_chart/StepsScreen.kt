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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import com.example.sauexpert.R
import com.example.sauexpert.bracelet_indicator.CustomTextRadioGroup
import com.example.sauexpert.bracelet_indicator.RangeCustomizeSection
import com.example.sauexpert.bracelet_indicator.TextWithBigValueAndDateForGraph
import com.example.sauexpert.model.ListNumberOfYForTableData
import com.example.sauexpert.model.StepsData
import com.example.sauexpert.model.TextOfTabData
import com.example.sauexpert.ui.theme.Gray30
import com.example.sauexpert.ui.theme.Gray50

@Composable
fun StepsScreen() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .padding(top = 24.dp, bottom = 10.dp)
    ) {
        StepswithBarChart()
        Spacer(modifier = Modifier.height(16.dp))
        RangeCustomizeSection()
    }
}

@Composable
fun StepswithBarChart(
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
        StepsTitle()
        Spacer(modifier = Modifier.height(12.dp))
        BarChartForSteps(
            StepsData = listOf(
                StepsData(positionOnX = 10f, stepsPerDay = 200f, dateName = "16"),
                StepsData(positionOnX = 110f, stepsPerDay = 30f, dateName = "17"),
                StepsData(positionOnX = 210f, stepsPerDay = 190f, dateName = "18"),
                StepsData(positionOnX = 310f, stepsPerDay = 180f, dateName = "19"),
                StepsData(positionOnX = 410f, stepsPerDay = 220f, dateName = "20"),
                StepsData(positionOnX = 510f, stepsPerDay = 240f, dateName = "21"),
                StepsData(positionOnX = 610f, stepsPerDay = 30f, dateName = "22")
            ),
            ListNumberData = listOf(
                ListNumberOfYForTableData("4 500"),
                ListNumberOfYForTableData("4 000"),
                ListNumberOfYForTableData("3 500"),
                ListNumberOfYForTableData("3 000"),
                ListNumberOfYForTableData("2 500"),
                ListNumberOfYForTableData("2 000"),
            )
        )
    }
}

@Composable
fun StepsTitle(
    modifier: Modifier = Modifier
) {
    var selectedTabIndex by remember {
        mutableStateOf(1)
    }

    var textDate = "18-20 ноября 2021"


    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier.fillMaxWidth()
        ) {
            Text(
                text = stringResource(id = R.string.steps),
                style = MaterialTheme.typography.caption
            )


            CustomTextRadioGroup(
                TextOfTab = listOf(
                    TextOfTabData(stringResource(R.string.week)),
                    TextOfTabData(stringResource(R.string.month)),
                )
            ) {
                selectedTabIndex = it
            }
            when (selectedTabIndex) {
                0 -> textDate = "18-20 ноября 2021"
                1 -> textDate = "Ноября 2021"

            }
        }

        TextWithBigValueAndDateForGraph(
            textValue = 3320,
            text = stringResource(R.string.steps_per_day),
            textDate = "18-20 ноября 2021"
        )
    }
}


@Composable
fun BarChartForSteps(
    StepsData: List<StepsData>,
    ListNumberData: List<ListNumberOfYForTableData>
) {
    var start by remember { mutableStateOf(false) }
    val heightPre by animateFloatAsState(
        targetValue = if (start) 1f else 0f,
        animationSpec = FloatTweenSpec(duration = 1000)
    )

    val listSize = StepsData.size - 1
    val heightForGraph = (ListNumberData.size * 35).dp

    val visible = remember { mutableStateOf(false) }
    val itemID = remember { mutableStateOf(1) }
    val positionOfX = remember { mutableStateOf(1) }
    val positionOfY = remember { mutableStateOf(1) }

    InfoDialogForBarChartOfSteps(
        visible = visible,
        itemID = itemID,
        xPosition = positionOfX,
        yPosition = positionOfY,
        StepsData = StepsData
    )

    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .height(heightForGraph)
            .pointerInput(Unit) {
                detectTapGestures(
                    onTap = {
                        itemID.value = identifyClickItemForSteps(StepsData, it.x, it.y)
                        ResetColorInsideDataClassForSteps(StepsData = StepsData)
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
        val paint = Paint().apply {
            textAlign = Paint.Align.CENTER
            textSize = 13.sp.toPx()
            color = Gray30.toArgb()
        }

        for (i in ListNumberData) {
            drawContext.canvas.nativeCanvas.drawText(
                i.number,
                StepsData[listSize].positionOnX + 38.dp.toPx(),
                height.dp.toPx(),
                paint
            )

            height += 35
        }

        start = true
        for (p in StepsData) {
            drawRect(
                color = Gray30,
                topLeft = Offset(
                    x = p.positionOnX,
                    y = (height - 35).dp.toPx() - ((height - 35).dp.toPx() - 75f) * heightPre
                ),
                size = Size(
                    width = 8.dp.toPx(),
                    height = ((height - 35).dp.toPx() - 75f) * heightPre
                )
            )

            drawRect(
                color = p.colorFocus,
                topLeft = Offset(
                    x = p.positionOnX,
                    y = (height - 35).dp.toPx() - ((height - 35).dp.toPx() - p.stepsPerDay) * heightPre
                ),
                size = Size(
                    width = 8.dp.toPx(),
                    height = ((height - 35).dp.toPx() - p.stepsPerDay) * heightPre
                )
            )

            drawContext.canvas.nativeCanvas.drawText(
                "${p.dateName}",
                p.positionOnX + 8,
                (height - 15).dp.toPx(),
                paint
            )
        }
    }
}

private fun identifyClickItemForSteps(dataList: List<StepsData>, x: Float, y: Float): Int {
    for ((index, dataList) in dataList.withIndex()) {
        if (x > dataList.positionOnX
            && x < dataList.positionOnX + 20
            && y > dataList.stepsPerDay
        ) {
            return index
        }
    }
    return -1
}

private fun ResetColorInsideDataClassForSteps(StepsData: List<StepsData>) {
    for (p in StepsData) {
        p.colorFocus = Gray50
    }
}

@Composable
fun InfoDialogForBarChartOfSteps(
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
                                    ResetColorInsideDataClassForSteps(StepsData = StepsData)
                                }
                        )
                    }
                }
            }
        }
    }
}