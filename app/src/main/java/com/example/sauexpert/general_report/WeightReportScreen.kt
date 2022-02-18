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
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sauexpert.R
import com.example.sauexpert.bracelet_indicator.dpToPxValue
import com.example.sauexpert.bracelet_indicator.identifyHeightForYPoint
import com.example.sauexpert.indicator_with_chart.BarChartForSteps
import com.example.sauexpert.indicator_with_chart.InfoDialogForBarChartOfSteps
import com.example.sauexpert.model.ListNumberOfYForTableData
import com.example.sauexpert.model.StepsData
import com.example.sauexpert.model.WeightData
import com.example.sauexpert.ui.theme.Gray30
import com.example.sauexpert.widgets.compose.Toolbars.ActionToolBarWithSubtitle

@Composable
fun WeightReportScreen() {
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
                titleText = stringResource(R.string.weight),
                subtitleText = "Декабрь 2021",
                iconBackClick = Icons.Default.ArrowBack,
                onBackClick = {},
                onRightClick = {}
            )

            Spacer(modifier = Modifier.height(16.dp))

            WeightReportWithBarChart()

        }
    }
}


@Composable
fun WeightReportWithBarChart(
    modifier: Modifier = Modifier
) {
    val configuration = LocalConfiguration.current
    val screenWidth = dpToPxValue((configuration.screenWidthDp.dp - 70.dp) / 7)

    val listNumberData = listOf(
        ListNumberOfYForTableData(80),
        ListNumberOfYForTableData(78),
        ListNumberOfYForTableData(77),
        ListNumberOfYForTableData(76),
        ListNumberOfYForTableData(75),
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
            text = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        color = Color.Black,
                        fontSize = 22.sp
                    )
                ) {
                    append(stringResource(R.string.weight))
                }

                append(", ${stringResource(R.string.kg)}")
            },
            style = MaterialTheme.typography.overline,
            color = Gray30
        )

        Spacer(modifier = Modifier.height(12.dp))
        BarChartForWeight(
            WeightData = listOf(
                WeightData(
                    positionOnX = (screenWidth * 0),
                    weight = 80,
                    positionOnY = identifyHeightForYPoint(dataList = listNumberData, number = 80),
                    dateName = "16"
                ),
                WeightData(
                    positionOnX = (screenWidth * 1),
                    weight = 78,
                    positionOnY = identifyHeightForYPoint(dataList = listNumberData, number = 78),
                    dateName = "17"
                ),
                WeightData(
                    positionOnX = (screenWidth * 2),
                    weight = 79,
                    positionOnY = identifyHeightForYPoint(dataList = listNumberData, number = 79),
                    dateName = "18",
                    colorFocus = Gray30
                ),
                WeightData(
                    positionOnX = (screenWidth * 3),
                    weight = 76,
                    positionOnY = identifyHeightForYPoint(dataList = listNumberData, number = 76),
                    dateName = "19",
                    colorFocus = Color.Red
                ),
                WeightData(
                    positionOnX = (screenWidth * 4),
                    weight = 77,
                    positionOnY = identifyHeightForYPoint(dataList = listNumberData, number = 77),
                    dateName = "20"
                ),
                WeightData(
                    positionOnX = (screenWidth * 5),
                    weight = 78,
                    positionOnY = identifyHeightForYPoint(dataList = listNumberData, number = 78),
                    dateName = "21"
                ),
                WeightData(
                    positionOnX = (screenWidth * 6),
                    weight = 76,
                    positionOnY = identifyHeightForYPoint(dataList = listNumberData, number = 76),
                    dateName = "22"
                )
            ),
            ListNumberData = listNumberData
        )
    }
}


@Composable
fun BarChartForWeight(
    WeightData: List<WeightData>,
    ListNumberData: List<ListNumberOfYForTableData>
) {
    var start by remember { mutableStateOf(false) }
    val heightPre by animateFloatAsState(
        targetValue = if (start) 1f else 0f,
        animationSpec = FloatTweenSpec(duration = 1000)
    )

    val listSize = WeightData.size - 1
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

        for (i in ListNumberData) {
            drawContext.canvas.nativeCanvas.drawText(
                i.number.toString(),
                WeightData[listSize].positionOnX + 38.dp.toPx(),
                (height + 4).dp.toPx(),
                paint
            )

            height += 35
        }

        start = true
        for (p in WeightData) {
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
