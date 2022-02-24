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
import androidx.compose.material.Divider
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.example.sauexpert.R
import com.example.sauexpert.bracelet_indicator.dpToPxValue
import com.example.sauexpert.bracelet_indicator.identifyHeightForYPoint
import com.example.sauexpert.dimensions.Dimensions
import com.example.sauexpert.dimensions.smallDimensions
import com.example.sauexpert.dimensions.sw360Dimensions
import com.example.sauexpert.model.ListNumberOfYForTableData
import com.example.sauexpert.model.PrescriptionData
import com.example.sauexpert.ui.theme.Gray30
import com.example.sauexpert.ui.theme.Green15B83D
import com.example.sauexpert.ui.theme.Orange4294
import com.example.sauexpert.widgets.compose.Toolbars.ActionToolBarWithSubtitle

@Composable
fun PrescriptionCompletedReportScreen() {
    val configuration = LocalConfiguration.current
    val dimensions = if (configuration.screenWidthDp <= 360) smallDimensions else sw360Dimensions
    val navigator = LocalNavigator.currentOrThrow

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
                sizeText = dimensions.fontSizeSubtitle_2,
                sizeSubtitleText = dimensions.fontSizeBody_2,
                sizeIcon = dimensions.iconSize_2,
                onBackClick = {navigator.pop()},
                onRightClick = {}
            )

            Spacer(modifier = Modifier.height(dimensions.grid_2))

            PrescriptionReportWithBarChart(dimensions = dimensions)

            Spacer(modifier = Modifier.height(dimensions.grid_2))

            PrescriptionStatisticsReportSection(dimensions = dimensions)

        }
    }
}

@Composable
fun PrescriptionReportWithBarChart(
    dimensions: Dimensions,
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
            style = MaterialTheme.typography.caption,
            fontSize = dimensions.fontSizeCaption
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "75%",
            style = MaterialTheme.typography.h4,
            color = Green15B83D,
            fontSize = dimensions.fontSizeH4
        )

        Spacer(modifier = Modifier.height(dimensions.grid_1_5))

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
            ListNumberData = listNumberData,
            dimensions = dimensions
        )
    }
}


@Composable
fun BarChartForPrescription(
    PrescriptionData: List<PrescriptionData>,
    ListNumberData: List<ListNumberOfYForTableData>,
    dimensions: Dimensions
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
            textSize = dimensions.fontSizeCustom_3.toPx()
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


@Composable
fun PrescriptionStatisticsReportSection(
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
    ) {
        ProgressBarForPrescription(
            greenSector = 40,
            orangeSector = 35,
            redSector = 25,
            greenSectorDay = 12,
            orangeSectorDay = 10,
            redSectorDay = 8,
            textSize = dimensions.fontSizeCustom_1
        )

        Divider(
            color = Gray30.copy(alpha = 0.19f),
            thickness = 1.dp,
            modifier = modifier
                .padding(horizontal = 16.dp)
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = stringResource(R.string.missed_measurements),
                style = MaterialTheme.typography.body1,
                color = Color.Red,
                fontSize = dimensions.fontSizeBody_1
            )

            Text(
                text = "4 ${stringResource(R.string.day)}",
                style = MaterialTheme.typography.body1,
                color = Color.Red,
                fontSize = dimensions.fontSizeBody_1
            )

        }

    }
}


@Composable
fun ProgressBarForPrescription(
    modifier: Modifier = Modifier,
    greenSector: Int = 0,
    orangeSector: Int = 0,
    redSector: Int = 0,
    greenSectorDay: Int,
    orangeSectorDay: Int,
    redSectorDay: Int,
    textSize: TextUnit = 15.sp
) {

    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp

    val widthForProgressBar = (screenWidth - 64.dp) / 100.dp

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Column() {
            LinearProgressIndicator(
                progress = 1f,
                color = Green15B83D,
                modifier = Modifier
                    .size(
                        height = 6.dp,
                        width = (greenSector.dp * widthForProgressBar)
                    )
            )

            Spacer(modifier = Modifier.height(7.dp))

            Text(
                text = "$greenSectorDay ${stringResource(R.string.days)}",
                style = MaterialTheme.typography.button,
                fontSize = textSize,
            )

            Text(
                text = "75-100%",
                style = MaterialTheme.typography.button,
                fontSize = textSize,
                color = Green15B83D
            )

        }

        Column() {
            LinearProgressIndicator(
                progress = 1f,
                color = Orange4294,
                modifier = Modifier
                    .size(
                        height = 6.dp,
                        width = (orangeSector.dp * widthForProgressBar)
                    )
            )

            Spacer(modifier = Modifier.height(7.dp))

            Text(
                text = "$orangeSectorDay ${stringResource(R.string.days)}",
                style = MaterialTheme.typography.button,
                fontSize = textSize,
            )

            Text(
                text = "50-75%",
                style = MaterialTheme.typography.button,
                fontSize = textSize,
                color = Orange4294
            )

        }

        Column() {
            LinearProgressIndicator(
                progress = 1f,
                color = Color.Red,
                modifier = Modifier
                    .size(
                        height = 6.dp,
                        width = (redSector.dp * widthForProgressBar)
                    )
            )

            Spacer(modifier = Modifier.height(7.dp))

            Text(
                text = "$redSectorDay ${stringResource(R.string.days)}",
                style = MaterialTheme.typography.button,
                fontSize = textSize,
            )

            Text(
                text = "0-50%",
                style = MaterialTheme.typography.button,
                fontSize = textSize,
                color = Color.Red
            )
        }
    }
}
