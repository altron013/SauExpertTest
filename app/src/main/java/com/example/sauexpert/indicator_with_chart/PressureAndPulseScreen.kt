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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import com.example.sauexpert.R
import com.example.sauexpert.bracelet_indicator.*
import com.example.sauexpert.model.*
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
                    positionOnX = 10f,
                    pressureInAverage = 150f,
                    dateName = "16",
                    startPoint = 200f
                ),
                PressureData(
                    positionOnX = 110f,
                    pressureInAverage = 230f,
                    dateName = "17",
                    startPoint = 170f
                ),
                PressureData(
                    positionOnX = 210f,
                    pressureInAverage = 190f,
                    dateName = "18",
                    startPoint = 190f
                ),
                PressureData(
                    positionOnX = 310f,
                    pressureInAverage = 180f,
                    dateName = "19",
                    startPoint = 150f
                ),
                PressureData(
                    positionOnX = 410f,
                    pressureInAverage = 220f,
                    dateName = "20",
                    startPoint = 130f
                ),
                PressureData(
                    positionOnX = 510f,
                    pressureInAverage = 240f,
                    dateName = "21",
                    startPoint = 150f
                ),
                PressureData(
                    positionOnX = 610f,
                    pressureInAverage = 50f,
                    dateName = "22",
                    startPoint = 280f
                )
            ),

            PulseData = listOf(
                PulseData(positionOnX = 20f, pulseInMinuteAverage = 350f),
                PulseData(positionOnX = 120f, pulseInMinuteAverage = 370f),
                PulseData(positionOnX = 220f, pulseInMinuteAverage = 350f),
                PulseData(positionOnX = 320f, pulseInMinuteAverage = 370f),
                PulseData(positionOnX = 420f, pulseInMinuteAverage = 250f),
                PulseData(positionOnX = 520f, pulseInMinuteAverage = 290f),
                PulseData(positionOnX = 620f, pulseInMinuteAverage = 300f),
            ),

            ListNumberData = listOf(
                ListNumberOfYForTableData("200"),
                ListNumberOfYForTableData("160"),
                ListNumberOfYForTableData("120"),
                ListNumberOfYForTableData("80"),
                ListNumberOfYForTableData("40"),
                ListNumberOfYForTableData("0"),
            ),
            visible = visible


        )
        Spacer(modifier = Modifier.height(20.dp))
        TextWithIconForGraph(
            color =
            if (visible.value) Color.Red else Gray50,
            text = stringResource(id = R.string.pressure)
        )
        TextWithIconForGraph(color = Blue4285, text = stringResource(id = R.string.pulse))
    }
}

@Composable
fun PressureAndPulseTitle(
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
                text = stringResource(id = R.string.pressure_pulse),
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

        Spacer(modifier = Modifier.height(16.dp))


        Text(
            text = textDate,
            style = MaterialTheme.typography.h6,
            fontSize = 15.sp,
            color = Gray30
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
            path.moveTo(0f * scale, item.pulseInMinuteAverage)
            path.lineTo(item.positionOnX * scale, item.pulseInMinuteAverage)
        } else {
            path.lineTo(item.positionOnX * scale, item.pulseInMinuteAverage)
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
                            identifyClickItemForPressureAndPulse(PressureData, it.x, it.y)
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
        val paint = Paint().apply {
            textAlign = Paint.Align.CENTER
            textSize = 13.sp.toPx()
            color = Gray30.toArgb()
        }

        for (i in ListNumberData) {
            drawContext.canvas.nativeCanvas.drawText(
                i.number,
                PulseData[listSize].positionOnX + 38.dp.toPx(),
                height.dp.toPx(),
                paint
            )

            height += 35
        }

        start = true

        for (p in PressureData) {
            drawRect(
                color = p.colorFocus,
                topLeft = Offset(
                    x = p.positionOnX,
                    y = p.startPoint * heightPre
                ),
                size = Size(
                    width = 8.dp.toPx(),
                    height = p.pressureInAverage * heightPre
                )

            )

            drawContext.canvas.nativeCanvas.drawText(
                "${p.dateName}",
                p.positionOnX + 8,
                (height - 35).dp.toPx(),
                paint
            )
        }

        drawPath(
            path = path,
            color = Blue4285,
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

    }
}

private fun identifyClickItemForPressureAndPulse(
    dataList: List<PressureData>,
    x: Float,
    y: Float
): Int {
    for ((index, dataList) in dataList.withIndex()) {
        if (x > dataList.positionOnX
            && x < dataList.positionOnX + 20
            && y > dataList.startPoint
            && y < dataList.pressureInAverage + dataList.startPoint
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


