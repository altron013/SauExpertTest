package com.example.sauexpert.bracelet_indicator

import android.graphics.Paint
import androidx.appcompat.app.AppCompatActivity
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import com.example.sauexpert.R
import com.example.sauexpert.model.HRVData
import com.example.sauexpert.model.ListNumberOfYForTableData
import com.example.sauexpert.model.TextOfTabData
import com.example.sauexpert.ui.theme.Gray30
import com.example.sauexpert.ui.theme.Gray50


@Composable
fun HRVScreen() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .padding(top = 24.dp, bottom = 10.dp)
    ) {
        HRVwithBarChart()
    }
}


@Composable
fun HRVwithBarChart(
    modifier: Modifier = Modifier
) {
    val configuration = LocalConfiguration.current
    val screenWidth = dpToPxValue((configuration.screenWidthDp.dp - 70.dp) / 7)


    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = Color.White,
                shape = RoundedCornerShape(10.dp)
            ).padding(16.dp)
    ) {
        TitleForGraph(
            textTitle = stringResource(id = R.string.hrv),
            TextOfTab = listOf(
                TextOfTabData(stringResource(R.string.week)),
                TextOfTabData(stringResource(R.string.month)),
                TextOfTabData(
                    stringResource(R.string.choose),
                    painter = painterResource(R.drawable.ic_calendar_icon)
                )
            ),
            weight = 0.3f
        )

        Spacer(modifier = Modifier.height(12.dp))
        BarChartForHRV(
            HRVData = listOf(
                HRVData(
                    positionOnX = 0f,
                    hourOfHRV = dpToPxValue(20.dp),
                    dateName = "16"
                ),
                HRVData(
                    positionOnX = screenWidth,
                    hourOfHRV = dpToPxValue(120.dp),
                    dateName = "17"
                ),
                HRVData(
                    positionOnX = (screenWidth * 2),
                    hourOfHRV = dpToPxValue(100.dp),
                    dateName = "18"
                ),
                HRVData(
                    positionOnX = (screenWidth * 3),
                    hourOfHRV = dpToPxValue(80.dp),
                    dateName = "19"
                ),
                HRVData(
                    positionOnX = (screenWidth * 4),
                    hourOfHRV = dpToPxValue(120.dp),
                    dateName = "20"
                ),
                HRVData(
                    positionOnX = (screenWidth * 5),
                    hourOfHRV = dpToPxValue(140.dp),
                    dateName = "21"
                ),
                HRVData(
                    positionOnX = (screenWidth * 6),
                    hourOfHRV = dpToPxValue(50.dp),
                    dateName = "22"
                )
            ),
            ListNumberData = listOf(
                ListNumberOfYForTableData("100"),
                ListNumberOfYForTableData("75"),
                ListNumberOfYForTableData("50"),
                ListNumberOfYForTableData("25"),
                ListNumberOfYForTableData("0"),
            )
        )
    }
}


@Composable
fun BarChartForHRV(
    HRVData: List<HRVData>,
    ListNumberData: List<ListNumberOfYForTableData>
) {
    var start by remember { mutableStateOf(false) }
    val heightPre by animateFloatAsState(
        targetValue = if (start) 1f else 0f,
        animationSpec = FloatTweenSpec(duration = 1000)
    )

    val listSize = HRVData.size - 1
    val heightForGraph = (ListNumberData.size * 35).dp
    val visible = remember { mutableStateOf(false) }
    val itemID = remember { mutableStateOf(1) }
    val positionOfX = remember { mutableStateOf(1) }
    val positionOfY = remember { mutableStateOf(1) }

    InfoDialogForBarChartOfHRV(
        visible = visible,
        itemID = itemID,
        xPosition = positionOfX,
        yPosition = positionOfY,
        HRVData = HRVData
    )

    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .height(heightForGraph)
            .pointerInput(Unit) {
                detectTapGestures(
                    onTap = {
                        itemID.value = identifyClickItem(HRVData, it.x, it.y)
                        ResetColorInsideDataClass(HRVData = HRVData)
                        positionOfX.value = it.x.toInt()
                        positionOfY.value = it.y.toInt()
                        if (itemID.value != -1) {
                            visible.value = true
                            HRVData[itemID.value].colorFocus = Color.Red
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
                HRVData[listSize].positionOnX + 38.dp.toPx(),
                height.dp.toPx(),
                paint
            )

            height += 35
        }

        start = true
        for (p in HRVData) {
            drawRect(
                color = p.colorFocus,
                topLeft = Offset(
                    x = p.positionOnX,
                    y = (height - 35).dp.toPx() - ((height - 35).dp.toPx() - p.hourOfHRV) * heightPre
                ),
                size = Size(
                    width = 8.dp.toPx(),
                    height = ((height - 35).dp.toPx() - p.hourOfHRV) * heightPre
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

private fun identifyClickItem(dataList: List<HRVData>, x: Float, y: Float): Int {
    for ((index, dataList) in dataList.withIndex()) {
        if (x > dataList.positionOnX
            && x < dataList.positionOnX + 20
            && y > dataList.hourOfHRV
        ) {
            return index
        }
    }
    return -1
}

private fun ResetColorInsideDataClass(HRVData: List<HRVData>) {
    for (p in HRVData) {
        p.colorFocus = Gray50
    }
}

@Composable
fun InfoDialogForBarChartOfHRV(
    visible: MutableState<Boolean>,
    itemID: MutableState<Int>,
    xPosition: MutableState<Int>,
    yPosition: MutableState<Int>,
    HRVData: List<HRVData>,
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
                            text = "${itemID.value} | ${HRVData[itemID.value].hourOfHRV} | " +
                                    "${HRVData[itemID.value].dateName}",
                            style = MaterialTheme.typography.h5,
                            modifier = modifier
                                .align(alignment = Alignment.Center)
                                .clickable {
                                    visible.value = false
                                    ResetColorInsideDataClass(HRVData = HRVData)
                                }
                        )
                    }
                }
            }
        }
    }
}