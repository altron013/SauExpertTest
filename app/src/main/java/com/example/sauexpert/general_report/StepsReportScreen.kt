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
import com.example.sauexpert.bracelet_indicator.dpToPxValue
import com.example.sauexpert.bracelet_indicator.identifyHeightForYPoint
import com.example.sauexpert.indicator_with_chart.BarChartForSteps
import com.example.sauexpert.model.ListNumberOfYForTableData
import com.example.sauexpert.model.StepsData
import com.example.sauexpert.ui.theme.Gray30
import com.example.sauexpert.widgets.compose.Toolbars.ActionToolBarWithSubtitle

@Composable
fun StepsReportScreen() {
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
                titleText = stringResource(R.string.steps),
                subtitleText = "Декабрь 2021",
                iconBackClick = Icons.Default.ArrowBack,
                onBackClick = {},
                onRightClick = {}
            )

            Spacer(modifier = Modifier.height(16.dp))

            StepsReportWithBarChart()

        }
    }
}


@Composable
fun StepsReportWithBarChart(
    modifier: Modifier = Modifier
) {
    val configuration = LocalConfiguration.current
    val screenWidth = dpToPxValue((configuration.screenWidthDp.dp - 70.dp) / 7)

    val listNumberData = listOf(
        ListNumberOfYForTableData(4500),
        ListNumberOfYForTableData(4000),
        ListNumberOfYForTableData(3500),
        ListNumberOfYForTableData(3000),
        ListNumberOfYForTableData(2500),
        ListNumberOfYForTableData(2000),
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
            text = stringResource(id = R.string.steps),
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
                    append("3 500 ")
                }

                append(stringResource(R.string.steps_per_day))
            },
            style = MaterialTheme.typography.body1,
            color = Gray30
        )

        Spacer(modifier = Modifier.height(12.dp))
        BarChartForSteps(
            StepsData = listOf(
                StepsData(
                    positionOnX = (screenWidth * 0),
                    stepsPerDay = 4500,
                    positionOnY = identifyHeightForYPoint(dataList = listNumberData, number = 4500),
                    dateName = "16"
                ),
                StepsData(
                    positionOnX = (screenWidth * 1),
                    stepsPerDay = 4100,
                    positionOnY = identifyHeightForYPoint(dataList = listNumberData, number = 4100),
                    dateName = "17"
                ),
                StepsData(
                    positionOnX = (screenWidth * 2),
                    stepsPerDay = 5000,
                    positionOnY = identifyHeightForYPoint(dataList = listNumberData, number = 5000),
                    dateName = "18"
                ),
                StepsData(
                    positionOnX = (screenWidth * 3),
                    stepsPerDay = 2500,
                    positionOnY = identifyHeightForYPoint(dataList = listNumberData, number = 2500),
                    dateName = "19"
                ),
                StepsData(
                    positionOnX = (screenWidth * 4),
                    stepsPerDay = 2000,
                    positionOnY = identifyHeightForYPoint(dataList = listNumberData, number = 2000),
                    dateName = "20"
                ),
                StepsData(
                    positionOnX = (screenWidth * 5),
                    stepsPerDay = 3700,
                    positionOnY = identifyHeightForYPoint(dataList = listNumberData, number = 3700),
                    dateName = "21"
                ),
                StepsData(
                    positionOnX = (screenWidth * 6),
                    stepsPerDay = 3000,
                    positionOnY = identifyHeightForYPoint(dataList = listNumberData, number = 3000),
                    dateName = "22"
                )
            ),
            ListNumberData = listNumberData
        )
    }
}