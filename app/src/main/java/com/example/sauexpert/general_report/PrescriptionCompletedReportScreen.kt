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
import com.example.sauexpert.model.ListNumberOfYForTableData
import com.example.sauexpert.model.PrescriptionData
import com.example.sauexpert.ui.theme.Gray30
import com.example.sauexpert.ui.theme.Green15B83D
import com.example.sauexpert.widgets.compose.Toolbars.ActionToolBarWithSubtitle

@Composable
fun PrescriptionCompletedReportScreen() {
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
                titleText = stringResource(R.string.fulfillment_prescription),
                subtitleText = "Декабрь 2021",
                iconBackClick = Icons.Default.ArrowBack,
                onBackClick = {},
                onRightClick = {}
            )

            Spacer(modifier = Modifier.height(16.dp))

            PrescriptionReportWithBarChart()

        }
    }
}

@Composable
fun PrescriptionReportWithBarChart(
    modifier: Modifier = Modifier
) {
    val configuration = LocalConfiguration.current
    val screenWidth = dpToPxValue((configuration.screenWidthDp.dp - 70.dp) / 7)

    val listNumberData = listOf(
        ListNumberOfYForTableData(100),
        ListNumberOfYForTableData(80),
        ListNumberOfYForTableData(40),
        ListNumberOfYForTableData(20),
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
        Text(
            text = stringResource(id = R.string.prescription_completed),
            style = MaterialTheme.typography.caption
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "75%",
            style = MaterialTheme.typography.h4,
            color = Green15B83D
        )

        Spacer(modifier = Modifier.height(12.dp))

        BarChartForPrescription(
            PrescriptionData = listOf(
                PrescriptionData(
                    positionOnX = (screenWidth * 0),
                    prescriptionPer = 80,
                    positionOnY = identifyHeightForYPoint(dataList = listNumberData, number = 80),
                    dateName = "16"
                ),
                PrescriptionData(
                    positionOnX = (screenWidth * 1),
                    prescriptionPer = 78,
                    positionOnY = identifyHeightForYPoint(dataList = listNumberData, number = 78),
                    dateName = "17"
                ),
                PrescriptionData(
                    positionOnX = (screenWidth * 2),
                    prescriptionPer = 90,
                    positionOnY = identifyHeightForYPoint(dataList = listNumberData, number = 90),
                    dateName = "18"
                ),
                PrescriptionData(
                    positionOnX = (screenWidth * 3),
                    prescriptionPer = 50,
                    positionOnY = identifyHeightForYPoint(dataList = listNumberData, number = 50),
                    dateName = "19"
                ),
                PrescriptionData(
                    positionOnX = (screenWidth * 4),
                    prescriptionPer = 40,
                    positionOnY = identifyHeightForYPoint(dataList = listNumberData, number = 40),
                    dateName = "20"
                ),
                PrescriptionData(
                    positionOnX = (screenWidth * 5),
                    prescriptionPer = 60,
                    positionOnY = identifyHeightForYPoint(dataList = listNumberData, number = 60),
                    dateName = "21"
                ),
                PrescriptionData(
                    positionOnX = (screenWidth * 6),
                    prescriptionPer = 70,
                    positionOnY = identifyHeightForYPoint(dataList = listNumberData, number = 70),
                    dateName = "22"
                )
            ),
            ListNumberData = listNumberData
        )
    }
}


@Composable
fun BarChartForPrescription(
    PrescriptionData: List<PrescriptionData>,
    ListNumberData: List<ListNumberOfYForTableData>
) {
    var start by remember { mutableStateOf(false) }
    val heightPre by animateFloatAsState(
        targetValue = if (start) 1f else 0f,
        animationSpec = FloatTweenSpec(duration = 1000)
    )

    val listSize = PrescriptionData.size - 1
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
                PrescriptionData[listSize].positionOnX + 38.dp.toPx(),
                (height + 4).dp.toPx(),
                paint
            )

            height += 35
        }

        start = true
        for (p in PrescriptionData) {
            drawRect(
                color = Gray30,
                topLeft = Offset(
                    x = p.positionOnX,
                    y = (height - 35).dp.toPx() - ((height - 35).dp.toPx()) * heightPre
                ),
                size = Size(
                    width = 8.dp.toPx(),
                    height = ((height - 35).dp.toPx()) * heightPre
                )
            )

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
