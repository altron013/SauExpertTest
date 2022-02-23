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
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import com.example.sauexpert.R
import com.example.sauexpert.bracelet_indicator.*
import com.example.sauexpert.dimensions.Dimensions
import com.example.sauexpert.dimensions.smallDimensions
import com.example.sauexpert.dimensions.sw360Dimensions
import com.example.sauexpert.model.ListNumberOfYForTableData
import com.example.sauexpert.model.StepsData
import com.example.sauexpert.model.TextOfTabData
import com.example.sauexpert.ui.theme.Gray30
import com.example.sauexpert.ui.theme.Gray50

@Composable
fun StepsScreen() {
    val configuration = LocalConfiguration.current
    val dimensions = if (configuration.screenWidthDp <= 360) smallDimensions else sw360Dimensions

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .padding(top = 24.dp, bottom = 10.dp)
    ) {
        StepswithBarChart(dimensions = dimensions)
        Spacer(modifier = Modifier.height(16.dp))
        RangeCustomizeSection()
    }
}

@Composable
fun StepswithBarChart(
    dimensions: Dimensions,
    modifier: Modifier = Modifier
) {
    val configuration = LocalConfiguration.current
    val screenWidth = dpToPxValue((configuration.screenWidthDp.dp - 70.dp) / 7)

    val listNumberData = listOf(
        ListNumberOfYForTableData(4500),
        ListNumberOfYForTableData(4000),
        ListNumberOfYForTableData(3500),
        ListNumberOfYForTableData(3000),
        ListNumberOfYForTableData(2500),
        ListNumberOfYForTableData(2000),
    )

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = Color.White,
                shape = RoundedCornerShape(10.dp)
            ).padding(16.dp)
    ) {
        StepsTitle(dimensions = dimensions)
        Spacer(modifier = Modifier.height(12.dp))
        BarChartForSteps(
            StepsData = listOf(
                StepsData(
                    positionOnX = (screenWidth * 0),
                    stepsPerDay = 4500,
                    positionOnY = identifyHeightForYPoint(dataList = listNumberData, number = 4500),
                    dateName = "16"
                ),
                StepsData(
                    positionOnX = (screenWidth * 1),
                    stepsPerDay = 4100,
                    positionOnY = identifyHeightForYPoint(dataList = listNumberData, number = 4100),
                    dateName = "17"
                ),
                StepsData(
                    positionOnX = (screenWidth * 2),
                    stepsPerDay = 5000,
                    positionOnY = identifyHeightForYPoint(dataList = listNumberData, number = 5000),
                    dateName = "18"
                ),
                StepsData(
                    positionOnX = (screenWidth * 3),
                    stepsPerDay = 2500,
                    positionOnY = identifyHeightForYPoint(dataList = listNumberData, number = 2500),
                    dateName = "19"
                ),
                StepsData(
                    positionOnX = (screenWidth * 4),
                    stepsPerDay = 2000,
                    positionOnY = identifyHeightForYPoint(dataList = listNumberData, number = 2000),
                    dateName = "20"
                ),
                StepsData(
                    positionOnX = (screenWidth * 5),
                    stepsPerDay = 3700,
                    positionOnY = identifyHeightForYPoint(dataList = listNumberData, number = 3700),
                    dateName = "21"
                ),
                StepsData(
                    positionOnX = (screenWidth * 6),
                    stepsPerDay = 3000,
                    positionOnY = identifyHeightForYPoint(dataList = listNumberData, number = 3000),
                    dateName = "22"
                )
            ),
            ListNumberData = listNumberData
        )
    }
}

@Composable
fun StepsTitle(
    dimensions: Dimensions,
    modifier: Modifier = Modifier
) {
    var selectedTabIndex by remember {
        mutableStateOf(1)
    }

    val date = remember { mutableStateOf("") }


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
                    TextOfTabData(stringResource(R.string.week_short).toUpperCase(Locale.current)),
                    TextOfTabData(stringResource(R.string.month_short).toUpperCase(Locale.current)),
                ),
                dimensions = dimensions
            ) {
                selectedTabIndex = it
            }
            when (selectedTabIndex) {
                0 -> date.value = "18-20 ноября 2021"
                1 -> date.value = "Ноября 2021"

            }
        }

        TextWithBigValueAndDateForGraph(
            textValue = 3320,
            text = stringResource(R.string.steps_per_day),
            textDate = date.value
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
                        itemID.value = identifyClickItem(
                            dataList = StepsData,
                            x = it.x,
                            y = it.y,
                            size = 8.dp.toPx()
                        )
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
                i.number.toString(),
                StepsData[listSize].positionOnX + 38.dp.toPx(),
                (height + 4).dp.toPx(),
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
                    y = (height - 35).dp.toPx() - ((height - 35).dp.toPx() - 35.dp.toPx()) * heightPre
                ),
                size = Size(
                    width = 8.dp.toPx(),
                    height = ((height - 35).dp.toPx() - 35.dp.toPx()) * heightPre
                )
            )

            drawRect(
                color = p.colorFocus,
                topLeft = Offset(
                    x = p.positionOnX,
                    y = (height - 35).dp.toPx() - ((height - 35).dp.toPx() - p.positionOnY) * heightPre
                ),
                size = Size(
                    width = 8.dp.toPx(),
                    height = ((height - 35).dp.toPx() - p.positionOnY) * heightPre
                )
            )

            drawContext.canvas.nativeCanvas.drawText(
                "${p.dateName}",
                p.positionOnX + 3.2.dp.toPx(),
                (height - 15).dp.toPx(),
                paint
            )
        }
    }
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