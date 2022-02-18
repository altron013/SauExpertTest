package com.example.sauexpert.general_report

import android.graphics.Paint
import androidx.compose.animation.core.FloatTweenSpec
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sauexpert.R
import com.example.sauexpert.bracelet_indicator.dpToPxValue
import com.example.sauexpert.bracelet_indicator.identifyHeightForYPoint
import com.example.sauexpert.bracelet_indicator.identifyHeightForYPointForString
import com.example.sauexpert.model.ListNumberOfYForTableData
import com.example.sauexpert.model.ListStringOfYForTableData
import com.example.sauexpert.model.PrescriptionData
import com.example.sauexpert.model.WellBeingData
import com.example.sauexpert.ui.theme.Gray30
import com.example.sauexpert.ui.theme.Green15B83D
import com.example.sauexpert.widgets.compose.Toolbars.ActionToolBarWithSubtitle

@Composable
fun WellBeingReportScreen() {
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
                titleText = stringResource(R.string.well_being),
                subtitleText = "Декабрь 2021",
                iconBackClick = Icons.Default.ArrowBack,
                onBackClick = {},
                onRightClick = {}
            )

            Spacer(modifier = Modifier.height(16.dp))

            WellBeingReportWithBarChart()

            Spacer(modifier = Modifier.height(16.dp))

            PrescriptionStatisticsReportSection()

        }
    }
}


@Composable
fun WellBeingReportWithBarChart(
    modifier: Modifier = Modifier
) {
    val configuration = LocalConfiguration.current
    val screenWidth = dpToPxValue((configuration.screenWidthDp.dp - 70.dp) / 7)

    val listNumberData = listOf(
        ListStringOfYForTableData("Очень хорошо"),
        ListStringOfYForTableData("Хорошо"),
        ListStringOfYForTableData("Нормально"),
        ListStringOfYForTableData("Плохо"),
        ListStringOfYForTableData("Очень плохо"),
    )

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = Color.White,
                shape = RoundedCornerShape(10.dp)
            ).padding(16.dp)
    ) {
        Text(
            text = stringResource(id = R.string.well_being),
            style = MaterialTheme.typography.caption
        )


        Spacer(modifier = Modifier.height(12.dp))

        BarChartForWellBeing(
            WellBeingData = listOf(
                WellBeingData(
                    positionOnX = (screenWidth * 0),
                    wellBeing = "Хорошо",
                    positionOnY = identifyHeightForYPointForString(
                        dataList = listNumberData,
                        text = "Хорошо"
                    ),
                    dateName = "16"
                ),
                WellBeingData(
                    positionOnX = (screenWidth * 1),
                    wellBeing = "Плохо",
                    positionOnY = identifyHeightForYPointForString(
                        dataList = listNumberData,
                        text = "Плохо"
                    ),
                    dateName = "17",
                    colorFocus = Color.Red
                ),
                WellBeingData(
                    positionOnX = (screenWidth * 2),
                    wellBeing = "Нормально",
                    positionOnY = identifyHeightForYPointForString(
                        dataList = listNumberData,
                        text = "Нормально"
                    ),
                    dateName = "18",
                    colorFocus = Gray30
                ),
                WellBeingData(
                    positionOnX = (screenWidth * 3),
                    wellBeing = "Хорошо",
                    positionOnY = identifyHeightForYPointForString(
                        dataList = listNumberData,
                        text = "Хорошо"
                    ),
                    dateName = "19"
                ),
                WellBeingData(
                    positionOnX = (screenWidth * 4),
                    wellBeing = "Хорошо",
                    positionOnY = identifyHeightForYPointForString(
                        dataList = listNumberData,
                        text = "Хорошо"
                    ),
                    dateName = "20"
                ),
                WellBeingData(
                    positionOnX = (screenWidth * 5),
                    wellBeing = "Хорошо",
                    positionOnY = identifyHeightForYPointForString(
                        dataList = listNumberData,
                        text = "Хорошо"
                    ),
                    dateName = "21"
                )
            ),
            ListNumberData = listNumberData
        )
    }
}


@Composable
fun BarChartForWellBeing(
    WellBeingData: List<WellBeingData>,
    ListNumberData: List<ListStringOfYForTableData>
) {
    var start by remember { mutableStateOf(false) }
    val heightPre by animateFloatAsState(
        targetValue = if (start) 1f else 0f,
        animationSpec = FloatTweenSpec(duration = 1000)
    )

    val listSize = WellBeingData.size - 1
    val heightForGraph = (ListNumberData.size * 35).dp


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

        val paint2 = Paint().apply {
            textAlign = Paint.Align.RIGHT
            textSize = 13.sp.toPx()
            color = Gray30.toArgb()
        }

        for (i in ListNumberData) {
            drawContext.canvas.nativeCanvas.drawText(
                i.text,
                WellBeingData[listSize].positionOnX + 100.dp.toPx(),
                (height + 4).dp.toPx(),
                paint2
            )

            height += 35
        }

        start = true
        for (p in WellBeingData) {
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