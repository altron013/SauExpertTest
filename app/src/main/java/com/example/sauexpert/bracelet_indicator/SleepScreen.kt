package com.example.sauexpert.bracelet_indicator

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
import androidx.compose.material.Divider
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
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
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sauexpert.R
import com.example.sauexpert.model.ListNumberOfYForTableData
import com.example.sauexpert.model.SleepData
import com.example.sauexpert.model.TextOfTabData
import com.example.sauexpert.ui.theme.Gray30
import com.example.sauexpert.ui.theme.Gray50


@Composable
fun SleepScreen() {
    val visible = remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .padding(top = 24.dp, bottom = 10.dp)
    ) {

        SleepwithBarChart(
            visible = visible
        )

        Spacer(modifier = Modifier.height(20.dp))

        SleepStatisticsSection(wakeUpStatistics = 3, visible = visible)

        Spacer(modifier = Modifier.height(16.dp))

        RangeCustomizeSection()

    }


}


@Composable
fun SleepwithBarChart(
    visible: MutableState<Boolean>,
    modifier: Modifier = Modifier
) {
    val configuration = LocalConfiguration.current
    val screenWidth = dpToPxValue((configuration.screenWidthDp.dp - 70.dp) / 7)

    val listNumberData = listOf(
        ListNumberOfYForTableData(10),
        ListNumberOfYForTableData(8),
        ListNumberOfYForTableData(6),
        ListNumberOfYForTableData(4),
        ListNumberOfYForTableData(2),
        ListNumberOfYForTableData(0),
    )


    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = Color.White,
                shape = RoundedCornerShape(10.dp)
            )
            .padding(16.dp)
    ) {
        TitleForGraph(
            textTitle = stringResource(id = R.string.sleep),
            TextOfTab = listOf(
                TextOfTabData(stringResource(R.string.week_short).toUpperCase(Locale.current)),
                TextOfTabData(stringResource(R.string.month_short).toUpperCase(Locale.current)),
            ),
            weight = 0.3f
        )
        Spacer(modifier = Modifier.height(12.dp))
        BarChartForSleep(
            ListNumberData = listNumberData,

            SleepData = listOf(
                SleepData(
                    positionOnX = (screenWidth * 0),
                    hourOfSleep = identifyHeightForYPoint(dataList = listNumberData, number = 7),
                    dateName = "16"
                ),
                SleepData(
                    positionOnX = (screenWidth * 1),
                    hourOfSleep = identifyHeightForYPoint(dataList = listNumberData, number = 4),
                    dateName = "17"
                ),
                SleepData(
                    positionOnX = (screenWidth * 2),
                    hourOfSleep = identifyHeightForYPoint(dataList = listNumberData, number = 5),
                    dateName = "18"
                ),
                SleepData(
                    positionOnX = (screenWidth * 3),
                    hourOfSleep = identifyHeightForYPoint(dataList = listNumberData, number = 6),
                    dateName = "19"
                ),
                SleepData(
                    positionOnX = (screenWidth * 4),
                    hourOfSleep = identifyHeightForYPoint(dataList = listNumberData, number = 2),
                    dateName = "20"
                ),
                SleepData(
                    positionOnX = (screenWidth * 5),
                    hourOfSleep = identifyHeightForYPoint(dataList = listNumberData, number = 3),
                    dateName = "21"
                ),
                SleepData(
                    positionOnX = (screenWidth * 6),
                    hourOfSleep = identifyHeightForYPoint(dataList = listNumberData, number = 1),
                    dateName = "22"
                )
            ),

            visible = visible

        )
    }
}


@Composable
fun BarChartForSleep(
    SleepData: List<SleepData>,
    visible: MutableState<Boolean>,
    ListNumberData: List<ListNumberOfYForTableData>
) {
    var start by remember { mutableStateOf(false) }
    val listSize = SleepData.size - 1
    val heightForGraph = (ListNumberData.size * 35).dp

    val heightPre by animateFloatAsState(
        targetValue = if (start) 1f else 0f,
        animationSpec = FloatTweenSpec(duration = 1000)
    )

    val itemID = remember { mutableStateOf(1) }
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
                        ResetColorInsideDataClassForSleep(dataList = SleepData)
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
            textSize = 13.sp.toPx()
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
                color = p.colorFocus,
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


private fun ResetColorInsideDataClassForSleep(dataList: List<SleepData>) {
    for (p in dataList) {
        p.colorFocus = Gray50
    }
}

private fun setRedColorInsideDataClassForSleep(
    sleepData: List<SleepData>,
    itemID: MutableState<Int>,
    visible: MutableState<Boolean>
) {
    if (itemID.value != -1 && visible.value) {
        ResetColorInsideDataClassForSleep(dataList = sleepData)
        sleepData[itemID.value].colorFocus = Color.Red
    } else {
        ResetColorInsideDataClassForSleep(dataList = sleepData)
    }
}


@Composable
fun SleepStatisticsSection(
    wakeUpStatistics: Int,
    visible: MutableState<Boolean>,
    modifier: Modifier = Modifier
) {

    if (visible.value) {
        Column {

            Text(
                text = "Показатели за 21 декабря 2021",
                style = MaterialTheme.typography.h6,
                fontSize = 15.sp,
                color = Gray30
            )

            Spacer(modifier = Modifier.height(8.dp))


            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .background(
                        color = Color.White,
                        shape = RoundedCornerShape(10.dp)
                    )
            ) {

                AnalysisField(
                    title = stringResource(R.string.woke_up_in_middle),
                    value = "$wakeUpStatistics раза",
                )
                Divider(
                    color = Gray30.copy(alpha = 0.19f),
                    thickness = 1.dp,
                    modifier = modifier
                        .padding(horizontal = 16.dp)
                )
                ProgressBarForSleep(
                    deepSleepPercent = 40,
                    lightSleepPercent = 35,
                    remSleepPercent = 25,
                )

            }
        }
    }
}

@Composable
fun ProgressBarForSleep(
    modifier: Modifier = Modifier,
    deepSleepPercent: Int = 0,
    lightSleepPercent: Int = 0,
    remSleepPercent: Int = 0,
) {

    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp

    val widthForProgressBar = (screenWidth - 70.dp) / 100.dp

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Column() {
            LinearProgressIndicator(
                progress = 1f,
                color = Color.Blue,
                modifier = Modifier
                    .size(
                        height = 6.dp,
                        width = (deepSleepPercent.dp * widthForProgressBar)
                    )
            )

            Spacer(modifier = Modifier.height(7.dp))

            Text(
                text = stringResource(R.string.deep_sleep),
                style = MaterialTheme.typography.button,
                fontSize = 15.sp,
                color = Color.Blue
            )

            Text(
                text = "$deepSleepPercent%",
                style = MaterialTheme.typography.button,
                fontSize = 15.sp,
            )

        }

        Column() {
            LinearProgressIndicator(
                progress = 1f,
                color = Color.Cyan,
                modifier = Modifier
                    .size(
                        height = 6.dp,
                        width = (lightSleepPercent.dp * widthForProgressBar)
                    )
            )

            Spacer(modifier = Modifier.height(7.dp))

            Text(
                text = stringResource(R.string.light_sleep),
                style = MaterialTheme.typography.button,
                fontSize = 15.sp,
                color = Color.Cyan
            )

            Text(
                text = "$lightSleepPercent%",
                style = MaterialTheme.typography.button,
                fontSize = 15.sp,
            )

        }

        Column() {
            LinearProgressIndicator(
                progress = 1f,
                color = Color.Gray,
                modifier = Modifier
                    .size(
                        height = 6.dp,
                        width = (remSleepPercent.dp * widthForProgressBar)
                    )
            )

            Spacer(modifier = Modifier.height(7.dp))

            Text(
                text = stringResource(R.string.rem_sleep),
                style = MaterialTheme.typography.button,
                fontSize = 15.sp,
                color = Color.Gray
            )

            Text(
                text = "$remSleepPercent%",
                style = MaterialTheme.typography.button,
                fontSize = 15.sp,
            )
        }
    }
}