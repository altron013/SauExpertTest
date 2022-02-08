package com.example.sauexpert.bracelet_indicator

import android.app.DatePickerDialog
import android.graphics.Paint
import android.widget.DatePicker
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
import java.util.*


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
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = Color.White,
                shape = RoundedCornerShape(10.dp)
            ).padding(16.dp)
    ) {
        HRVTitle()
        Spacer(modifier = Modifier.height(12.dp))
        BarChartForHRV(
            HRVData = listOf(
                HRVData(positionOnX = 10f, hourOfHRV = 200f, dateName = "16"),
                HRVData(positionOnX = 110f, hourOfHRV = 30f, dateName = "17"),
                HRVData(positionOnX = 210f, hourOfHRV = 190f, dateName = "18"),
                HRVData(positionOnX = 310f, hourOfHRV = 180f, dateName = "19"),
                HRVData(positionOnX = 410f, hourOfHRV = 220f, dateName = "20"),
                HRVData(positionOnX = 510f, hourOfHRV = 240f, dateName = "21"),
                HRVData(positionOnX = 610f, hourOfHRV = 30f, dateName = "22")
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
fun HRVTitle(
    modifier: Modifier = Modifier
) {
    var selectedTabIndex by remember {
        mutableStateOf(0)
    }

    val context = LocalContext.current

    val year: Int
    val month: Int
    val day: Int

    val calendar = Calendar.getInstance()
    year = calendar.get(Calendar.YEAR)
    month = calendar.get(Calendar.MONTH) + 1
    day = calendar.get(Calendar.DAY_OF_MONTH)
    calendar.time = Date()

    val date = remember { mutableStateOf("") }
    val datePickerDialog = DatePickerDialog(
        context,
        { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
            date.value = "$dayOfMonth/${month}/$year"
        }, year, month, day
    )


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
                text = stringResource(id = R.string.hrv),
                style = MaterialTheme.typography.caption
            )


            CustomTextRadioGroup(
                TextOfTab = listOf(
                    TextOfTabData(stringResource(R.string.week)),
                    TextOfTabData(stringResource(R.string.month)),
                    TextOfTabData(
                        stringResource(R.string.choose),
                        painter = painterResource(R.drawable.ic_calendar_icon)
                    )
                )
            ) {
                selectedTabIndex = it
            }
            when (selectedTabIndex) {
                0 -> date.value = "18-20 ноября 2021"
                1 -> date.value = "Ноября 2021"
                2 -> datePickerDialog.show()

            }
        }

        Spacer(modifier = Modifier.height(16.dp))


        Text(
            text = "${date.value}",
            style = MaterialTheme.typography.h6,
            fontSize = 15.sp,
            color = Gray30
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
                p.positionOnX + 8,
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