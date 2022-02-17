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
import com.example.sauexpert.model.ListNumberOfYForTableData
import com.example.sauexpert.model.PressureData
import com.example.sauexpert.model.PulseData
import com.example.sauexpert.model.TextOfTabData
import com.example.sauexpert.ui.theme.Blue4285
import com.example.sauexpert.ui.theme.Gray30
import com.example.sauexpert.ui.theme.Gray50

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
    val visible = remember { mutableStateOf(false) }

    val configuration = LocalConfiguration.current
    val screenWidth = dpToPxValue((configuration.screenWidthDp.dp - 70.dp) / 7)

    val listNumberData = listOf(
        ListNumberOfYForTableData(200),
        ListNumberOfYForTableData(160),
        ListNumberOfYForTableData(120),
        ListNumberOfYForTableData(80),
        ListNumberOfYForTableData(40),
        ListNumberOfYForTableData(0),
    )

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = Color.White,
                shape = RoundedCornerShape(10.dp)
            ).padding(16.dp)
    ) {
        TitleForGraph(
            textTitle = stringResource(id = R.string.pressure_pulse),
            TextOfTab = listOf(
                TextOfTabData(stringResource(R.string.week_short).toUpperCase(Locale.current)),
                TextOfTabData(stringResource(R.string.month_short).toUpperCase(Locale.current)),
            )
        )
        Spacer(modifier = Modifier.height(12.dp))
        BarChartForPressureAndPulse(
            PressureData = listOf(
                PressureData(
                    positionOnX = (screenWidth * 0),
                    pressureInAverage = "120/80",
                    dateName = "16",
                    startPoint = identifyHeightForYPoint(dataList = listNumberData, number = 120),
                    endPoint = identifyHeightForYPoint(dataList = listNumberData, number = 80),
                ),
                PressureData(
                    positionOnX = (screenWidth * 1),
                    pressureInAverage = "120/80",
                    dateName = "17",
                    startPoint = identifyHeightForYPoint(dataList = listNumberData, number = 120),
                    endPoint = identifyHeightForYPoint(dataList = listNumberData, number = 80),
                ),
                PressureData(
                    positionOnX = (screenWidth * 2),
                    pressureInAverage = "120/80",
                    dateName = "18",
                    startPoint = identifyHeightForYPoint(dataList = listNumberData, number = 120),
                    endPoint = identifyHeightForYPoint(dataList = listNumberData, number = 80),
                ),
                PressureData(
                    positionOnX = (screenWidth * 3),
                    pressureInAverage = "120/80",
                    dateName = "19",
                    startPoint = identifyHeightForYPoint(dataList = listNumberData, number = 120),
                    endPoint = identifyHeightForYPoint(dataList = listNumberData, number = 80),
                ),
                PressureData(
                    positionOnX = (screenWidth * 4),
                    pressureInAverage = "120/80",
                    dateName = "20",
                    startPoint = identifyHeightForYPoint(dataList = listNumberData, number = 120),
                    endPoint = identifyHeightForYPoint(dataList = listNumberData, number = 80),
                ),
                PressureData(
                    positionOnX = (screenWidth * 5),
                    pressureInAverage = "120/80",
                    dateName = "21",
                    startPoint = identifyHeightForYPoint(dataList = listNumberData, number = 120),
                    endPoint = identifyHeightForYPoint(dataList = listNumberData, number = 80),
                ),
                PressureData(
                    positionOnX = (screenWidth * 6),
                    pressureInAverage = "120/80",
                    dateName = "22",
                    startPoint = identifyHeightForYPoint(dataList = listNumberData, number = 120),
                    endPoint = identifyHeightForYPoint(dataList = listNumberData, number = 80),
                )
            ),

            PulseData = listOf(
                PulseData(
                    positionOnX = 10f,
                    pulseInMinuteAverage = 200,
                    positionOnY = identifyHeightForYPoint(dataList = listNumberData, number = 200),
                ),
                PulseData(
                    positionOnX = (screenWidth * 1 + 5f),
                    pulseInMinuteAverage = 240,
                    positionOnY = identifyHeightForYPoint(dataList = listNumberData, number = 240),
                ),
                PulseData(
                    positionOnX = (screenWidth * 2 + 5f),
                    pulseInMinuteAverage = 170,
                    positionOnY = identifyHeightForYPoint(dataList = listNumberData, number = 170),
                ),
                PulseData(
                    positionOnX = (screenWidth * 3 + 5f),
                    pulseInMinuteAverage = 160,
                    positionOnY = identifyHeightForYPoint(dataList = listNumberData, number = 160),
                ),
                PulseData(
                    positionOnX = (screenWidth * 4 + 5f),
                    pulseInMinuteAverage = 120,
                    positionOnY = identifyHeightForYPoint(dataList = listNumberData, number = 120),
                ),
                PulseData(
                    positionOnX = (screenWidth * 5 + 5f),
                    pulseInMinuteAverage = 90,
                    positionOnY = identifyHeightForYPoint(dataList = listNumberData, number = 90),
                ),
                PulseData(
                    positionOnX = (screenWidth * 6 + 5f),
                    pulseInMinuteAverage = 10,
                    positionOnY = identifyHeightForYPoint(dataList = listNumberData, number = 10),
                ),
            ),

            ListNumberData = listNumberData,
            visible = visible


        )
        Spacer(modifier = Modifier.height(20.dp))
        TextWithIconForGraph(
            color =
            if (visible.value) Color.Red else Gray50,
            text = stringResource(id = R.string.pressure).toUpperCase(Locale.current)
        )
        TextWithIconForGraph(
            color = Blue4285,
            text = stringResource(id = R.string.pulse).toUpperCase(Locale.current)
        )
    }
}

@Composable
fun BarChartForPressureAndPulse(
    PressureData: List<PressureData>,
    PulseData: List<PulseData>,
    ListNumberData: List<ListNumberOfYForTableData>,
    visible: MutableState<Boolean>,
) {
    val scale by remember { mutableStateOf(1f) }
    val path = Path()
    var start by remember { mutableStateOf(false) }
    val itemID = remember { mutableStateOf(1) }
    val positionOfX = remember { mutableStateOf(1) }
    val positionOfY = remember { mutableStateOf(1) }
    val listSize = PulseData.size - 1
    val heightForGraph = ((ListNumberData.size - 1) * 35).dp


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
            path.moveTo(0f * scale, item.positionOnY)
            path.lineTo(item.positionOnX * scale, item.positionOnY)
        } else {
            path.lineTo(item.positionOnX * scale, item.positionOnY)
        }
    }

    setRedColorInsideDataClassForPressureAndPulse(
        pressureData = PressureData,
        pulseData = PulseData,
        itemID = itemID,
        visible = visible
    )



    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .height(heightForGraph)
            .pointerInput(Unit) {
                detectTapGestures(
                    onTap = {
                        itemID.value =
                            identifyClickItemForPressureAndPulse(
                                dataList = PressureData,
                                x = it.x,
                                y = it.y,
                                size = 8.dp.toPx()
                            )
                        ResetColorInsideDataClassForPressureAndPulse(
                            pressureData = PressureData,
                            pulseData = PulseData
                        )
                        positionOfX.value = it.x.toInt()
                        positionOfY.value = it.y.toInt()
                        if (itemID.value != -1) {
                            visible.value = true
                            setRedColorInsideDataClassForPressureAndPulse(
                                pressureData = PressureData,
                                pulseData = PulseData,
                                itemID = itemID,
                                visible = visible
                            )
//                            PressureData[itemID.value].colorFocus = Color.Red
//                            PulseData[itemID.value].colorFocus = Color.Red

                        }
                    }
                )
            }
    ) {
        var height = 0
        var width = 0
        val paint = Paint().apply {
            textAlign = Paint.Align.CENTER
            textSize = 13.sp.toPx()
            color = Gray30.toArgb()
        }

        for (i in ListNumberData) {
            drawContext.canvas.nativeCanvas.drawText(
                i.number.toString(),
                PulseData[listSize].positionOnX + 38.dp.toPx(),
                height.dp.toPx(),
                paint
            )

            height += 35
        }

        drawRect(
            color = Color.Green.copy(alpha = 0.05f),
            topLeft = Offset(
                x = 0f,
                y = 70.dp.toPx()
            ),
            size = Size(
                width = PulseData[listSize].positionOnX + 20.dp.toPx(),
                height = 35.dp.toPx()
            )
        )

        for (i in 0 until listSize * 7) {
            drawLine(
                Gray30.copy(alpha = 0.5f),
                Offset(
                    x = (width + 6).dp.toPx(),
                    y = 70.dp.toPx()
                ),
                Offset(
                    x = width.dp.toPx(),
                    y = 105.dp.toPx()
                )
            )

            width += 6
        }

        start = true

        for (p in PressureData) {
            drawRect(
                color = p.colorFocus,
                topLeft = Offset(
                    x = p.positionOnX,
                    y = p.startPoint - 5.dp.toPx() * heightPre
                ),
                size = Size(
                    width = 8.dp.toPx(),
                    height = p.endPoint - p.startPoint + 8.dp.toPx() * heightPre
                )

            )

            drawContext.canvas.nativeCanvas.drawText(
                "${p.dateName}",
                p.positionOnX + 3.2.dp.toPx(),
                (height - 35).dp.toPx(),
                paint
            )
        }

        drawPath(
            path = path,
            color = Blue4285,
            style = Stroke(width = 2.dp.toPx())
        )

        for (i in PulseData) {
            drawCircle(
                color = Color.White,
                radius = 4.dp.toPx(),
                center = Offset(i.positionOnX, i.positionOnY - 1f)
            )

            drawCircle(
                color = i.colorFocus,
                radius = 3.dp.toPx(),
                center = Offset(i.positionOnX, i.positionOnY - 1f)
            )
        }

    }
}

private fun identifyClickItemForPressureAndPulse(
    dataList: List<PressureData>,
    x: Float,
    y: Float,
    size: Float,
): Int {
    for ((index, dataList) in dataList.withIndex()) {
        if (x > dataList.positionOnX
            && x < dataList.positionOnX + size
            && y > dataList.startPoint
            && y < dataList.endPoint + dataList.startPoint
        ) {
            return index
        }
    }
    return -1
}

private fun ResetColorInsideDataClassForPressureAndPulse(
    pressureData: List<PressureData>,
    pulseData: List<PulseData>
) {
    for (p in pressureData) {
        p.colorFocus = Gray50
    }

    for (i in pulseData) {
        i.colorFocus = Blue4285
    }
}

private fun setRedColorInsideDataClassForPressureAndPulse(
    pressureData: List<PressureData>,
    pulseData: List<PulseData>,
    itemID: MutableState<Int>,
    visible: MutableState<Boolean>
) {
    if (itemID.value != -1 && visible.value) {
        ResetColorInsideDataClassForPressureAndPulse(
            pressureData = pressureData,
            pulseData = pulseData
        )
        pressureData[itemID.value].colorFocus = Color.Red
        pulseData[itemID.value].colorFocus = Color.Red
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
                                    ResetColorInsideDataClassForPressureAndPulse(
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
    var selectedTabIndex by remember {
        mutableStateOf(1)
    }

    var textDate = "18"


    Column {

        Text(
            text = "Показатели за 21 декабря 2021",
            style = MaterialTheme.typography.h6,
            fontSize = 15.sp,
            color = Gray30
        )

        Spacer(modifier = Modifier.height(8.dp))

        CustomTextRadioGroup(
            TextOfTab = listOf(
                TextOfTabData(stringResource(R.string.pressure).toUpperCase(Locale.current)),
                TextOfTabData(stringResource(R.string.pulse).toUpperCase(Locale.current)),
            ),
            backgroundColor = Color.Black,
            textColor = Color.White
        ) {
            selectedTabIndex = it
        }
        when (selectedTabIndex) {
            0 -> textDate = "20"
            1 -> textDate = "18"

        }

        Spacer(modifier = Modifier.height(12.dp))

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
                value = textDate,
                dateData = "19 Декабря в 23:13",
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
                value = textDate,
                dateData = "19 Декабря в 23:13",

                )
            Divider(
                color = Gray30.copy(alpha = 0.19f),
                thickness = 1.dp,
                modifier = modifier
                    .padding(horizontal = 16.dp)
            )
            Divider(
                color = Gray30.copy(alpha = 0.19f),
                thickness = 1.dp,
                modifier = modifier
                    .padding(horizontal = 16.dp)
            )
            AnalysisField(
                title = stringResource(R.string.last_value),
                value = "18",
                dateData = "20 Декабря в 23:13",
            )
        }
    }
}


