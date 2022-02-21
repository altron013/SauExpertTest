package com.example.sauexpert.bracelet_indicator

import android.graphics.Paint
import androidx.appcompat.app.AppCompatActivity
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
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
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
import com.example.sauexpert.model.PressureData
import com.example.sauexpert.model.TextOfTabData
import com.example.sauexpert.ui.theme.Gray30
import com.example.sauexpert.ui.theme.Gray50

@Composable
fun PressureScreen() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .padding(top = 24.dp, bottom = 10.dp)
    ) {
        PressurewithBarChart()
        Spacer(modifier = Modifier.height(24.dp))
        AnalysisPressureSection()
        Spacer(modifier = Modifier.height(16.dp))
        RangeCustomizeSection()
    }
}


@Composable
fun PressurewithBarChart(
    modifier: Modifier = Modifier
) {
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
        PressureTitle()
        Spacer(modifier = Modifier.height(12.dp))
        BarChartForPressure(
            PressureData = listOf(
                PressureData(
                    positionOnX = (screenWidth * 0),
                    pressureInAverage = "100/90",
                    dateName = "16",
                    startPoint = identifyHeightForYPoint(dataList = listNumberData, number = 100),
                    endPoint = identifyHeightForYPoint(dataList = listNumberData, number = 90),
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
                    pressureInAverage = "160/70",
                    dateName = "18",
                    startPoint = identifyHeightForYPoint(dataList = listNumberData, number = 160),
                    endPoint = identifyHeightForYPoint(dataList = listNumberData, number = 70),
                ),
                PressureData(
                    positionOnX = (screenWidth * 3),
                    pressureInAverage = "140/90",
                    dateName = "19",
                    startPoint = identifyHeightForYPoint(dataList = listNumberData, number = 140),
                    endPoint = identifyHeightForYPoint(dataList = listNumberData, number = 90),
                ),
                PressureData(
                    positionOnX = (screenWidth * 4),
                    pressureInAverage = "120/60",
                    dateName = "20",
                    startPoint = identifyHeightForYPoint(dataList = listNumberData, number = 120),
                    endPoint = identifyHeightForYPoint(dataList = listNumberData, number = 60),
                ),
                PressureData(
                    positionOnX = (screenWidth * 5),
                    pressureInAverage = "110/80",
                    dateName = "21",
                    startPoint = identifyHeightForYPoint(dataList = listNumberData, number = 110),
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
            ListNumberData = listNumberData
        )
    }
}

@Composable
fun PressureTitle(
    modifier: Modifier = Modifier
) {
    var selectedTabIndex by remember {
        mutableStateOf(1)
    }


    val date = remember { mutableStateOf("") }
    val activity = LocalContext.current as AppCompatActivity

    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Text(
            text = stringResource(id = R.string.pressure),
            style = MaterialTheme.typography.caption
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "${date.value}",
            style = MaterialTheme.typography.h6,
            fontSize = 15.sp,
            color = Gray30
        )

        Spacer(modifier = Modifier.height(14.dp))


        CustomTextRadioGroup(
            TextOfTab = listOf(
                TextOfTabData(stringResource(R.string.week_short).toUpperCase(Locale.current)),
                TextOfTabData(stringResource(R.string.month_short).toUpperCase(Locale.current)),
                TextOfTabData(
                    stringResource(R.string.choose).toUpperCase(Locale.current),
                    painter = painterResource(R.drawable.ic_calendar)
                )
            ),
            activity = activity,
            dateText = date
        ) {
            selectedTabIndex = it
        }
        when (selectedTabIndex) {
            0 -> date.value = "18-20 ноября 2021"
            1 -> date.value = "Ноября 2021"

        }
    }
}


@Composable
fun BarChartForPressure(
    PressureData: List<PressureData>,
    ListNumberData: List<ListNumberOfYForTableData>
) {
    var start by remember { mutableStateOf(false) }
    val visible = remember { mutableStateOf(false) }
    val itemID = remember { mutableStateOf(1) }
    val positionOfX = remember { mutableStateOf(1) }
    val positionOfY = remember { mutableStateOf(1) }
    val listSize = PressureData.size - 1

    val heightPre by animateFloatAsState(
        targetValue = if (start) 1f else 0f,
        animationSpec = FloatTweenSpec(duration = 1000)
    )
    val heightForGraph = ((ListNumberData.size - 1) * 35).dp

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
            .height(heightForGraph)
            .pointerInput(Unit) {
                detectTapGestures(
                    onTap = {
                        itemID.value = identifyClickItem(
                            dataList = PressureData,
                            x = it.x,
                            y = it.y,
                            size = 8.dp.toPx()
                        )
                        ResetColorInsideDataClassForPressure(dataList = PressureData)
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
        var width = 0
        val paint = Paint().apply {
            textAlign = Paint.Align.CENTER
            textSize = 13.sp.toPx()
            color = Gray30.toArgb()
        }

        for (i in ListNumberData) {
            drawContext.canvas.nativeCanvas.drawText(
                i.number.toString(),
                PressureData[listSize].positionOnX + 38.dp.toPx(),
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
                width = PressureData[listSize].positionOnX + 8.dp.toPx(),
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
    }
}


private fun ResetColorInsideDataClassForPressure(dataList: List<PressureData>) {
    for (p in dataList) {
        p.colorFocus = Gray50
    }
}

@Composable
fun InfoDialogForBarChartOfPressure(
    visible: MutableState<Boolean>,
    itemID: MutableState<Int>,
    xPosition: MutableState<Int>,
    yPosition: MutableState<Int>,
    dataList: List<PressureData>,
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
                            text = "${itemID.value} | ${dataList[itemID.value].pressureInAverage} | " +
                                    "${dataList[itemID.value].dateName}",
                            style = MaterialTheme.typography.h5,
                            modifier = modifier
                                .align(alignment = Alignment.Center)
                                .clickable {
                                    visible.value = false
                                    ResetColorInsideDataClassForPressure(dataList = dataList)
                                }
                        )
                    }
                }
            }
        }
    }
}


@Composable
fun AnalysisPressureSection(modifier: Modifier = Modifier) {
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