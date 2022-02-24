package com.example.sauexpert.general_report

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
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.example.sauexpert.R
import com.example.sauexpert.bracelet_indicator.*
import com.example.sauexpert.dimensions.Dimensions
import com.example.sauexpert.dimensions.smallDimensions
import com.example.sauexpert.dimensions.sw360Dimensions
import com.example.sauexpert.model.ListNumberOfYForTableData
import com.example.sauexpert.model.SleepData
import com.example.sauexpert.ui.theme.*
import com.example.sauexpert.widgets.compose.Toolbars.ActionToolBarWithSubtitle

@Composable
fun SleepReportScreen() {
    val visible = remember { mutableStateOf(false) }
    val itemID = remember { mutableStateOf(0) }
    val configuration = LocalConfiguration.current
    val screenWidth = dpToPxValue((configuration.screenWidthDp.dp - 70.dp) / 7)
    val dimensions = if (configuration.screenWidthDp <= 360) smallDimensions else sw360Dimensions


    val listNumberData = listOf(
        ListNumberOfYForTableData(10),
        ListNumberOfYForTableData(8),
        ListNumberOfYForTableData(6),
        ListNumberOfYForTableData(4),
        ListNumberOfYForTableData(2),
        ListNumberOfYForTableData(0),
    )

    val SleepData = listOf(
        SleepData(
            positionOnX = (screenWidth * 0),
            hourOfSleep = identifyHeightForYPoint(dataList = listNumberData, number = 7),
            dateName = "16",
            hourOfDeepSleep = 5,
            hourOfLightSleep = 3,
            hourOfRemSleep = 2
        ),
        SleepData(
            positionOnX = (screenWidth * 1),
            hourOfSleep = identifyHeightForYPoint(dataList = listNumberData, number = 4),
            dateName = "17",
            hourOfDeepSleep = 5,
            hourOfLightSleep = 4,
            hourOfRemSleep = 3
        ),
        SleepData(
            positionOnX = (screenWidth * 2),
            hourOfSleep = identifyHeightForYPoint(dataList = listNumberData, number = 5),
            dateName = "18",
            hourOfDeepSleep = 6,
            hourOfLightSleep = 4,
            hourOfRemSleep = 2
        ),
        SleepData(
            positionOnX = (screenWidth * 3),
            hourOfSleep = identifyHeightForYPoint(dataList = listNumberData, number = 6),
            dateName = "19",
            hourOfDeepSleep = 5,
            hourOfLightSleep = 2,
            hourOfRemSleep = 2
        ),
        SleepData(
            positionOnX = (screenWidth * 4),
            hourOfSleep = identifyHeightForYPoint(dataList = listNumberData, number = 2),
            dateName = "20",
            hourOfDeepSleep = 5,
            hourOfLightSleep = 3,
            hourOfRemSleep = 2
        ),
        SleepData(
            positionOnX = (screenWidth * 5),
            hourOfSleep = identifyHeightForYPoint(dataList = listNumberData, number = 3),
            dateName = "21",
            hourOfDeepSleep = 3,
            hourOfLightSleep = 3,
            hourOfRemSleep = 3
        ),
        SleepData(
            positionOnX = (screenWidth * 6),
            hourOfSleep = identifyHeightForYPoint(dataList = listNumberData, number = 1),
            dateName = "22",
            hourOfDeepSleep = 5,
            hourOfLightSleep = 3,
            hourOfRemSleep = 2
        )
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = Gray30.copy(alpha = 0.19f)
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            ActionToolBarWithSubtitle(
                titleText = stringResource(R.string.sleep),
                subtitleText = "Декабрь 2021",
                iconBackClick = Icons.Default.ArrowBack,
                sizeText = dimensions.fontSizeSubtitle_2,
                sizeSubtitleText = dimensions.fontSizeBody_2,
                sizeIcon = dimensions.iconSize_2,
                onBackClick = {},
                onRightClick = {}
            )

            Spacer(modifier = Modifier.height(dimensions.grid_2))

            SleepReportWithBarChart(
                visible = visible,
                itemID = itemID,
                listNumberData = listNumberData,
                SleepData = SleepData,
                dimensions = dimensions
            )

            Spacer(modifier = Modifier.height(dimensions.grid_2))

            SleepStatisticsReportSection(
                visible = visible,
                itemID = itemID,
                SleepData = SleepData,
                dimensions = dimensions
            )

        }
    }
}


@Composable
fun SleepReportWithBarChart(
    SleepData: List<SleepData>,
    listNumberData: List<ListNumberOfYForTableData>,
    visible: MutableState<Boolean>,
    itemID: MutableState<Int>,
    dimensions: Dimensions,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = Color.White,
                shape = RoundedCornerShape(10.dp)
            )
            .padding(16.dp)
    ) {

        Text(
            text = stringResource(id = R.string.sleep),
            style = MaterialTheme.typography.caption,
            fontSize = dimensions.fontSizeCaption
        )

        Spacer(modifier = Modifier.height(dimensions.grid_1_5))

        BarChartForSleepReport(
            ListNumberData = listNumberData,
            SleepData = SleepData,
            itemID = itemID,
            visible = visible,
            dimensions = dimensions
        )
    }
}


@Composable
fun BarChartForSleepReport(
    SleepData: List<SleepData>,
    visible: MutableState<Boolean>,
    itemID: MutableState<Int>,
    ListNumberData: List<ListNumberOfYForTableData>,
    dimensions: Dimensions
) {
    var start by remember { mutableStateOf(false) }
    val listSize = SleepData.size - 1
    val heightForGraph = (ListNumberData.size * 35).dp

    val heightPre by animateFloatAsState(
        targetValue = if (start) 1f else 0f,
        animationSpec = FloatTweenSpec(duration = 1000)
    )

    val positionOfX = remember { mutableStateOf(1) }
    val positionOfY = remember { mutableStateOf(1) }

    setRedColorInsideDataClassForSleep(
        sleepData = SleepData,
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
                        itemID.value = identifyClickItem(
                            dataList = SleepData,
                            x = it.x,
                            y = it.y,
                            size = 8.dp.toPx()
                        )
                        visible.value = false
                        ResetColorInsideDataClassForSleepReport(dataList = SleepData)
                        positionOfX.value = it.x.toInt()
                        positionOfY.value = it.y.toInt()
                        if (itemID.value != -1) {
                            visible.value = true
                            setRedColorInsideDataClassForSleep(
                                sleepData = SleepData,
                                itemID = itemID,
                                visible = visible
                            )
                            SleepData[itemID.value].colorFocus = Color.Red
                        } else {
                            visible.value = false
                        }
                    }
                )
            }
    ) {
        var height = 0
        val paint = Paint().apply {
            textAlign = Paint.Align.CENTER
            textSize = dimensions.fontSizeCustom_3.toPx()
            color = Gray30.toArgb()
        }



        for (i in ListNumberData) {
            drawContext.canvas.nativeCanvas.drawText(
                i.number.toString(),
                SleepData[listSize].positionOnX + 38.dp.toPx(),
                (height + 4).dp.toPx(),
                paint
            )

            height += 35
        }

        start = true
        for (p in SleepData) {
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
                color = p.lightSleepColor,
                topLeft = Offset(
                    x = p.positionOnX,
                    y = (height - 35).dp.toPx() - ((height - 35).dp.toPx() - p.hourOfSleep) * heightPre
                ),
                size = Size(
                    width = 8.dp.toPx(),
                    height = ((height - 35).dp.toPx() - p.hourOfSleep) * heightPre
                )
            )

            drawContext.canvas.nativeCanvas.drawText(
                "${p.dateName}",
                p.positionOnX + 3.5.dp.toPx(),
                (height - 15).dp.toPx(),
                paint
            )

        }
    }
}


private fun ResetColorInsideDataClassForSleepReport(dataList: List<SleepData>) {
    for (p in dataList) {
        p.deepSleepColor = Gray50
        p.lightSleepColor = Gray50
        p.remSleepColor = Gray50
    }
}

private fun setRedColorInsideDataClassForSleep(
    sleepData: List<SleepData>,
    itemID: MutableState<Int>,
    visible: MutableState<Boolean>
) {
    if (itemID.value != -1 && visible.value) {
        ResetColorInsideDataClassForSleepReport(dataList = sleepData)
        sleepData[itemID.value].deepSleepColor = Color.Blue
        sleepData[itemID.value].lightSleepColor = Blue007AFF
        sleepData[itemID.value].remSleepColor = Blue4289
    } else {
        ResetColorInsideDataClassForSleepReport(dataList = sleepData)
    }
}


@Composable
fun SleepStatisticsReportSection(
    visible: MutableState<Boolean>,
    itemID: MutableState<Int>,
    SleepData: List<SleepData>,
    dimensions: Dimensions,
    modifier: Modifier = Modifier
) {

    if (visible.value) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(10.dp)
                )
        ) {
            ProgressBarForSleepReport(
                deepSleepPercent = SleepData[itemID.value].hourOfDeepSleep,
                lightSleepPercent = SleepData[itemID.value].hourOfLightSleep,
                remSleepPercent = SleepData[itemID.value].hourOfRemSleep,
                sizeText = dimensions.fontSizeCustom_1
            )

        }
    }
}

@Composable
fun ProgressBarForSleepReport(
    modifier: Modifier = Modifier,
    deepSleepPercent: Int = 0,
    lightSleepPercent: Int = 0,
    remSleepPercent: Int = 0,
    sizeText: TextUnit
) {

    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp - 64.dp
    val sleepSum = deepSleepPercent + lightSleepPercent + remSleepPercent

    val widthForProgressBar = screenWidth / sleepSum.dp

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Column(
            modifier = modifier.width((deepSleepPercent * widthForProgressBar).dp)
        ) {
            LinearProgressIndicator(
                progress = 1f,
                color = Color.Blue,
                modifier = Modifier
                    .size(
                        height = 6.dp,
                        width = (deepSleepPercent * widthForProgressBar).dp
                    )
            )

            Spacer(modifier = Modifier.height(7.dp))

            Text(
                text = stringResource(R.string.deep_sleep),
                style = MaterialTheme.typography.button,
                fontSize = sizeText,
                color = Color.Blue,
                maxLines = 1
            )

            Text(
                text = "$deepSleepPercent часа",
                style = MaterialTheme.typography.button,
                fontSize = sizeText,
            )

        }

        Spacer(modifier = Modifier.width(2.dp))

        Column(
            modifier = modifier.width((lightSleepPercent * widthForProgressBar).dp)
        ) {
            LinearProgressIndicator(
                progress = 1f,
                color = Blue007AFF,
                modifier = Modifier
                    .size(
                        height = 6.dp,
                        width = (lightSleepPercent * widthForProgressBar).dp
                    )
            )

            Spacer(modifier = Modifier.height(7.dp))

            Text(
                text = stringResource(R.string.light_sleep),
                style = MaterialTheme.typography.button,
                fontSize = sizeText,
                color = Blue007AFF,
                maxLines = 1
            )

            Text(
                text = "$lightSleepPercent часа",
                style = MaterialTheme.typography.button,
                fontSize = sizeText,
            )

        }

        Spacer(modifier = Modifier.width(2.dp))

        Column(
            modifier = modifier.width((remSleepPercent * widthForProgressBar).dp)
        ) {
            LinearProgressIndicator(
                progress = 1f,
                color = Blue4289,
                modifier = Modifier
                    .size(
                        height = 6.dp,
                        width = (remSleepPercent * widthForProgressBar).dp
                    )
            )

            Spacer(modifier = Modifier.height(7.dp))

            Text(
                text = stringResource(R.string.rem_sleep),
                style = MaterialTheme.typography.button,
                fontSize = sizeText,
                color = Blue4289,
                maxLines = 1
            )

            Text(
                text = "$remSleepPercent часа",
                style = MaterialTheme.typography.button,
                fontSize = sizeText,
            )
        }
    }
}