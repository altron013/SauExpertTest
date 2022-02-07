package com.example.sauexpert.bracelet_indicator

import android.graphics.Paint
import androidx.compose.animation.core.FloatTweenSpec
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Circle
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sauexpert.R
import com.example.sauexpert.model.HRVData
import com.example.sauexpert.model.ListNumberOfYForTableData
import com.example.sauexpert.model.SleepData
import com.example.sauexpert.model.TextOfTabData
import com.example.sauexpert.ui.theme.Gray30


@Composable
fun SleepScreen() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .padding(top = 24.dp, bottom = 10.dp)
    ) {

        SleepwithBarChart()
        Spacer(modifier = Modifier.height(20.dp))
        SleepWakeUpStatistics(textValue = 3)
        Spacer(modifier = Modifier.height(16.dp))
        ProgressBarForSleep(
            deepSleepValue = 120,
            deepSleepPercent = 40,
            lightSleepValue = 115,
            lightSleepPercent = 30
        )

        Spacer(modifier = Modifier.height(16.dp))
        RangeCustomizeSection()

    }


}


@Composable
fun SleepwithBarChart(
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
        SleepTitle()
        Spacer(modifier = Modifier.height(12.dp))
        BarChartForSleep(
            SleepData = listOf(
                SleepData(positionOnX = 10f, hourOfSleep = 140f, dateName = "16"),
                SleepData(positionOnX = 110f, hourOfSleep = 200f, dateName = "17"),
                SleepData(positionOnX = 210f, hourOfSleep = 190f, dateName = "18"),
                SleepData(positionOnX = 310f, hourOfSleep = 180f, dateName = "19"),
                SleepData(positionOnX = 410f, hourOfSleep = 220f, dateName = "20"),
                SleepData(positionOnX = 510f, hourOfSleep = 400f, dateName = "21"),
                SleepData(positionOnX = 610f, hourOfSleep = 370f, dateName = "22")
            ),
            ListNumberData = listOf(
                ListNumberOfYForTableData("10"),
                ListNumberOfYForTableData("8"),
                ListNumberOfYForTableData("6"),
                ListNumberOfYForTableData("4"),
                ListNumberOfYForTableData("2"),
                ListNumberOfYForTableData("0"),
            )

        )
    }
}

@Composable
fun SleepTitle(
    modifier: Modifier = Modifier
) {
    var selectedTabIndex by remember {
        mutableStateOf(0)
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
                text = stringResource(id = R.string.sleep),
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
fun BarChartForSleep(
    SleepData: List<SleepData>,
    ListNumberData: List<ListNumberOfYForTableData>
) {
    var start by remember { mutableStateOf(false) }
    val listSize = SleepData.size - 1
    val heightForGraph = (ListNumberData.size * 35).dp

    val heightPre by animateFloatAsState(
        targetValue = if (start) 1f else 0f,
        animationSpec = FloatTweenSpec(duration = 1000)
    )

    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .height(heightForGraph)
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
                SleepData[listSize].positionOnX + 38.dp.toPx(),
                height.dp.toPx(),
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
                    y = (height - 35).dp.toPx() - ((height - 35).dp.toPx() - p.hourOfSleep) * heightPre
                ),
                size = Size(
                    width = 8.dp.toPx(),
                    height = ((height - 35).dp.toPx() - p.hourOfSleep) * heightPre
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

@Composable
fun SleepWakeUpStatistics(
    textValue: Int,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = Gray30.copy(alpha = 0.19f),
                shape = RoundedCornerShape(10.dp)
            )
            .padding(16.dp)
    ) {
        Text(
            text = stringResource(R.string.woke_up_in_middle),
            style = MaterialTheme.typography.button,
            fontSize = 15.sp,
        )

        Text(
            text = "$textValue раза",
            style = MaterialTheme.typography.subtitle2,
            fontSize = 15.sp,
        )
    }
}

@Composable
fun ProgressBarForSleep(
    modifier: Modifier = Modifier,
    deepSleepValue: Int = 0,
    deepSleepPercent: Int = 0,
    lightSleepValue: Int = 0,
    lightSleepPercent: Int = 0,

    ) {

    Row(
        modifier = modifier.fillMaxWidth()
    ) {
        Column() {
            LinearProgressIndicator(
                progress = 1f,
                color = Color.Blue,
                modifier = modifier.size(height = 6.dp, width = deepSleepValue.dp)
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
                modifier = modifier.size(height = 6.dp, width = lightSleepValue.dp)
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
                modifier = modifier.size(height = 6.dp, width = 115.dp)
            )

            Spacer(modifier = Modifier.height(7.dp))

            Text(
                text = stringResource(R.string.light_sleep),
                style = MaterialTheme.typography.button,
                fontSize = 15.sp,
                color = Color.Gray
            )

            Text(
                text = "30%",
                style = MaterialTheme.typography.button,
                fontSize = 15.sp,
            )
        }
    }
}