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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sauexpert.R
import com.example.sauexpert.bracelet_indicator.BarChartForHRV
import com.example.sauexpert.bracelet_indicator.dpToPxValue
import com.example.sauexpert.bracelet_indicator.identifyHeightForYPoint
import com.example.sauexpert.dimensions.Dimensions
import com.example.sauexpert.dimensions.smallDimensions
import com.example.sauexpert.dimensions.sw360Dimensions
import com.example.sauexpert.model.HRVData
import com.example.sauexpert.model.ListNumberOfYForTableData
import com.example.sauexpert.ui.theme.Gray30
import com.example.sauexpert.widgets.compose.Toolbars.ActionToolBarWithSubtitle

@Composable
fun HRVReportScreen() {
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
                titleText = stringResource(R.string.hrv),
                subtitleText = "Декабрь 2021",
                iconBackClick = Icons.Default.ArrowBack,
                onBackClick = {},
                onRightClick = {}
            )

            Spacer(modifier = Modifier.height(16.dp))

            HRVReportWithBarChart(dimensions = dimensions)

        }
    }
}


@Composable
fun HRVReportWithBarChart(
    dimensions: Dimensions,
    modifier: Modifier = Modifier
) {
    val configuration = LocalConfiguration.current
    val screenWidth = dpToPxValue((configuration.screenWidthDp.dp - 70.dp) / 7)

    val listNumberData = listOf(
        ListNumberOfYForTableData(100),
        ListNumberOfYForTableData(75),
        ListNumberOfYForTableData(50),
        ListNumberOfYForTableData(25),
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
            text = stringResource(id = R.string.hrv),
            style = MaterialTheme.typography.caption
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        color = Color.Black,
                        fontSize = 34.sp
                    )
                ) {
                    append("80 ")
                }

                append(stringResource(R.string.milliseconds))
            },
            style = MaterialTheme.typography.h4,
            color = Gray30
        )

        Spacer(modifier = Modifier.height(12.dp))
        BarChartForHRV(
            HRVData = listOf(
                HRVData(
                    positionOnX = (screenWidth * 0),
                    positionOnY = identifyHeightForYPoint(dataList = listNumberData, number = 90),
                    hourOfHRV = 90,
                    dateName = "16"
                ),
                HRVData(
                    positionOnX = (screenWidth * 1),
                    positionOnY = identifyHeightForYPoint(dataList = listNumberData, number = 100),
                    hourOfHRV = 100,
                    dateName = "17"
                ),
                HRVData(
                    positionOnX = (screenWidth * 2),
                    positionOnY = identifyHeightForYPoint(dataList = listNumberData, number = 75),
                    hourOfHRV = 75,
                    dateName = "18"
                ),
                HRVData(
                    positionOnX = (screenWidth * 3),
                    positionOnY = identifyHeightForYPoint(dataList = listNumberData, number = 50),
                    hourOfHRV = 50,
                    dateName = "19"
                ),
                HRVData(
                    positionOnX = (screenWidth * 4),
                    positionOnY = identifyHeightForYPoint(dataList = listNumberData, number = 60),
                    hourOfHRV = 60,
                    dateName = "20"
                ),
                HRVData(
                    positionOnX = (screenWidth * 5),
                    positionOnY = identifyHeightForYPoint(dataList = listNumberData, number = 25),
                    hourOfHRV = 25,
                    dateName = "21"
                ),
                HRVData(
                    positionOnX = (screenWidth * 6),
                    positionOnY = identifyHeightForYPoint(dataList = listNumberData, number = 80),
                    hourOfHRV = 80,
                    dateName = "22"
                )
            ),
            ListNumberData = listNumberData,
            dimensions = dimensions
        )
    }
}