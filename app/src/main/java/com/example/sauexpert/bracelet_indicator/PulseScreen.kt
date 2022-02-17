package com.example.sauexpert.bracelet_indicator

import android.graphics.Paint
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import com.example.sauexpert.R
import com.example.sauexpert.model.ListNumberOfYForTableData
import com.example.sauexpert.model.PulseData
import com.example.sauexpert.model.TextOfTabData
import com.example.sauexpert.ui.theme.Blue4285
import com.example.sauexpert.ui.theme.Gray30

@Composable
fun PulseScreen() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .padding(top = 24.dp, bottom = 10.dp)
    ) {
        PulsewithBarChart()
        Spacer(modifier = Modifier.height(24.dp))
        AnalysisPulseSection()
        Spacer(modifier = Modifier.height(16.dp))
        RangeCustomizeSection()
    }
}


@Composable
fun PulsewithBarChart(
    modifier: Modifier = Modifier
) {
    val configuration = LocalConfiguration.current
    val screenWidth = dpToPxValue((configuration.screenWidthDp.dp - 70.dp) / 7)

    val listNumberData = listOf(
        ListNumberOfYForTableData(240),
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
            textTitle = stringResource(id = R.string.pulse),
            TextOfTab = listOf(
                TextOfTabData(stringResource(R.string.week_short).toUpperCase(Locale.current)),
                TextOfTabData(stringResource(R.string.month_short).toUpperCase(Locale.current)),
                TextOfTabData(
                    stringResource(R.string.choose).toUpperCase(Locale.current),
                    painter = painterResource(R.drawable.ic_calendar_icon)
                )
            ),
            weight = 0.3f
        )
        Spacer(modifier = Modifier.height(12.dp))
        LineChartForPulse(
            PulseData = listOf(
                PulseData(
                    positionOnX = 10f,
                    pulseInMinuteAverage = identifyHeightForYPoint(
                        dataList = listNumberData,
                        number = 200
                    ),
                    dateName = "16"
                ),
                PulseData(
                    positionOnX = screenWidth,
                    pulseInMinuteAverage = identifyHeightForYPoint(
                        dataList = listNumberData,
                        number = 240
                    ),
                    dateName = "17"
                ),
                PulseData(
                    positionOnX = (screenWidth * 2),
                    pulseInMinuteAverage = identifyHeightForYPoint(
                        dataList = listNumberData,
                        number = 170
                    ),
                    dateName = "18"
                ),
                PulseData(
                    positionOnX = (screenWidth * 3),
                    pulseInMinuteAverage = identifyHeightForYPoint(
                        dataList = listNumberData,
                        number = 160
                    ),
                    dateName = "19"
                ),
                PulseData(
                    positionOnX = (screenWidth * 4),
                    pulseInMinuteAverage = identifyHeightForYPoint(
                        dataList = listNumberData,
                        number = 120
                    ),
                    dateName = "20"
                ),
                PulseData(
                    positionOnX = (screenWidth * 5),
                    pulseInMinuteAverage = identifyHeightForYPoint(
                        dataList = listNumberData,
                        number = 90
                    ),
                    dateName = "21"
                ),
                PulseData(
                    positionOnX = (screenWidth * 6),
                    pulseInMinuteAverage = identifyHeightForYPoint(
                        dataList = listNumberData,
                        number = 10
                    ),
                    dateName = "22"
                ),

                ),
            ListNumberData = listNumberData
        )
    }
}


@Composable
fun LineChartForPulse(
    PulseData: List<PulseData>,
    ListNumberData: List<ListNumberOfYForTableData>
) {
    val scale by remember { mutableStateOf(1f) }
    val path = Path()
    val listSize = PulseData.size - 1
    val heightForGraph = ((ListNumberData.size - 1) * 35).dp

    val visible = remember { mutableStateOf(false) }
    val itemID = remember { mutableStateOf(1) }
    val positionOfX = remember { mutableStateOf(1) }
    val positionOfY = remember { mutableStateOf(1) }

    InfoDialogForBarChartOfPulse(
        visible = visible,
        itemID = itemID,
        xPosition = positionOfX,
        yPosition = positionOfY,
        dataList = PulseData
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
            .height(heightForGraph)
            .pointerInput(Unit) {
                detectTapGestures(
                    onTap = {
                        itemID.value = identifyClickItemForPulse(
                            dataList = PulseData,
                            x = it.x,
                            y = it.y,
                            size = 3.dp.toPx()
                        )
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

        drawPath(
            path = path,
            color = Blue4285,
            style = Stroke(width = 2.dp.toPx())
        )


        for (i in PulseData) {
            drawCircle(
                color = Color.White,
                radius = 4.dp.toPx(),
                center = Offset(i.positionOnX, i.pulseInMinuteAverage - 1f)
            )

            drawCircle(
                color = i.colorFocus,
                radius = 3.dp.toPx(),
                center = Offset(i.positionOnX, i.pulseInMinuteAverage - 1f)
            )

            drawContext.canvas.nativeCanvas.drawText(
                "${i.dateName}",
                i.positionOnX,
                (height - 35).dp.toPx(),
                paint
            )
        }
    }
}

private fun identifyClickItemForPulse(
    dataList: List<PulseData>,
    x: Float,
    y: Float,
    size: Float
): Int {
    for ((index, dataList) in dataList.withIndex()) {
        if (x > dataList.positionOnX - size
            && x < dataList.positionOnX + size
            && y > dataList.pulseInMinuteAverage - size
            && y < dataList.pulseInMinuteAverage + size
        ) {
            return index
        }
    }
    return -1
}

private fun ResetColorInsideDataClassForPulse(dataList: List<PulseData>) {
    for (p in dataList) {
        p.colorFocus = Blue4285
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
fun AnalysisPulseSection(modifier: Modifier = Modifier) {
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
            value = "18",
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