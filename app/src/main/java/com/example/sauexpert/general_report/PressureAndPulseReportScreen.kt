package com.example.sauexpert.general_report

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.unit.dp
import com.example.sauexpert.R
import com.example.sauexpert.bracelet_indicator.TextWithIconForGraph
import com.example.sauexpert.bracelet_indicator.dpToPxValue
import com.example.sauexpert.bracelet_indicator.identifyHeightForYPoint
import com.example.sauexpert.dimensions.Dimensions
import com.example.sauexpert.dimensions.smallDimensions
import com.example.sauexpert.dimensions.sw360Dimensions
import com.example.sauexpert.indicator_with_chart.BarChartForPressureAndPulse
import com.example.sauexpert.model.ListNumberOfYForTableData
import com.example.sauexpert.model.PressureData
import com.example.sauexpert.model.PulseData
import com.example.sauexpert.ui.theme.Blue4285
import com.example.sauexpert.ui.theme.Gray30
import com.example.sauexpert.ui.theme.Gray50
import com.example.sauexpert.widgets.compose.Toolbars.ActionToolBarWithSubtitle

@Composable
fun PressureAndPulseReportScreen() {
    val configuration = LocalConfiguration.current
    val dimensions = if (configuration.screenWidthDp <= 360) smallDimensions else sw360Dimensions

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
                titleText = stringResource(R.string.pressure_pulse),
                subtitleText = "Декабрь 2021",
                iconBackClick = Icons.Default.ArrowBack,
                onBackClick = {},
                onRightClick = {}
            )

            Spacer(modifier = Modifier.height(16.dp))

            PressureAndPulseReportWithBarChart(dimensions = dimensions)

            Spacer(modifier = Modifier.height(16.dp))

            ReferenceIndicatorSection(
                textValue = "120/80",
                dimensions = dimensions
            )

            Spacer(modifier = Modifier.height(16.dp))

            IndicatorForMonthSection(dimensions = dimensions)

            Spacer(modifier = Modifier.height(16.dp))

            DeviationsFromGeneralSection(dimensions = dimensions)
        }
    }
}


@Composable
fun PressureAndPulseReportWithBarChart(
    dimensions: Dimensions,
    modifier: Modifier = Modifier
) {
    val visible = remember { mutableStateOf(false) }

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
        Text(
            text = stringResource(id = R.string.pressure_pulse),
            style = MaterialTheme.typography.caption
        )

        Spacer(modifier = Modifier.height(12.dp))
        BarChartForPressureAndPulse(
            PressureData = listOf(
                PressureData(
                    positionOnX = (screenWidth * 0),
                    pressureInAverage = "120/80",
                    dateName = "16",
                    startPoint = identifyHeightForYPoint(dataList = listNumberData, number = 120),
                    endPoint = identifyHeightForYPoint(dataList = listNumberData, number = 80),
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
                    pressureInAverage = "120/80",
                    dateName = "18",
                    startPoint = identifyHeightForYPoint(dataList = listNumberData, number = 120),
                    endPoint = identifyHeightForYPoint(dataList = listNumberData, number = 80),
                ),
                PressureData(
                    positionOnX = (screenWidth * 3),
                    pressureInAverage = "120/80",
                    dateName = "19",
                    startPoint = identifyHeightForYPoint(dataList = listNumberData, number = 120),
                    endPoint = identifyHeightForYPoint(dataList = listNumberData, number = 80),
                ),
                PressureData(
                    positionOnX = (screenWidth * 4),
                    pressureInAverage = "120/80",
                    dateName = "20",
                    startPoint = identifyHeightForYPoint(dataList = listNumberData, number = 120),
                    endPoint = identifyHeightForYPoint(dataList = listNumberData, number = 80),
                ),
                PressureData(
                    positionOnX = (screenWidth * 5),
                    pressureInAverage = "120/80",
                    dateName = "21",
                    startPoint = identifyHeightForYPoint(dataList = listNumberData, number = 120),
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

            PulseData = listOf(
                PulseData(
                    positionOnX = 10f,
                    pulseInMinuteAverage = 200,
                    positionOnY = identifyHeightForYPoint(dataList = listNumberData, number = 200),
                ),
                PulseData(
                    positionOnX = (screenWidth * 1 + 5f),
                    pulseInMinuteAverage = 240,
                    positionOnY = identifyHeightForYPoint(dataList = listNumberData, number = 240),
                ),
                PulseData(
                    positionOnX = (screenWidth * 2 + 5f),
                    pulseInMinuteAverage = 170,
                    positionOnY = identifyHeightForYPoint(dataList = listNumberData, number = 170),
                ),
                PulseData(
                    positionOnX = (screenWidth * 3 + 5f),
                    pulseInMinuteAverage = 160,
                    positionOnY = identifyHeightForYPoint(dataList = listNumberData, number = 160),
                ),
                PulseData(
                    positionOnX = (screenWidth * 4 + 5f),
                    pulseInMinuteAverage = 120,
                    positionOnY = identifyHeightForYPoint(dataList = listNumberData, number = 120),
                ),
                PulseData(
                    positionOnX = (screenWidth * 5 + 5f),
                    pulseInMinuteAverage = 90,
                    positionOnY = identifyHeightForYPoint(dataList = listNumberData, number = 90),
                ),
                PulseData(
                    positionOnX = (screenWidth * 6 + 5f),
                    pulseInMinuteAverage = 10,
                    positionOnY = identifyHeightForYPoint(dataList = listNumberData, number = 10),
                ),
            ),
            dimensions = dimensions,
            ListNumberData = listNumberData,
            visible = visible


        )
        Spacer(modifier = Modifier.height(20.dp))
        TextWithIconForGraph(
            color = if (visible.value) Color.Red else Gray50,
            text = stringResource(id = R.string.pressure).toUpperCase(Locale.current),
            dimensions = dimensions
        )
        TextWithIconForGraph(
            color = Blue4285,
            text = stringResource(id = R.string.pulse).toUpperCase(Locale.current),
            dimensions = dimensions
        )
    }
}